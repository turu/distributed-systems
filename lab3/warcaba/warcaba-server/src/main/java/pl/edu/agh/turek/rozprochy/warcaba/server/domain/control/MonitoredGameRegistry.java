package pl.edu.agh.turek.rozprochy.warcaba.server.domain.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.IGameRegistry;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.concurrent.*;

/**
 * Author: Piotr Turek
 */
public class MonitoredGameRegistry implements IGameRegistry {
    private static final Logger LOG = LoggerFactory.getLogger(MonitoredGameRegistry.class);
    private static final ThreadFactory THREAD_FACTORY = new DaemonThreadFactory();

    private final Registry rmiRegistry;
    private final ConcurrentMap<IWarGameToken, IWarGame> activeGames = new ConcurrentHashMap<>();
    private final CompletionService<GameTaskStats> completionService;
    private final ExecutorService completionDaemonExecutor;
    private final int gameStartDelay;

    public MonitoredGameRegistry(Registry rmiRegistry, int gameStartDelay) {
        this.rmiRegistry = rmiRegistry;
        this.gameStartDelay = gameStartDelay;

        ExecutorService executorService = Executors.newCachedThreadPool();
        completionService = new ExecutorCompletionService<>(executorService);
        completionDaemonExecutor = Executors.newSingleThreadExecutor(THREAD_FACTORY);
    }

    @Override
    public void add(IWarGameToken token, IWarGame game) {
        final IWarGame previous = activeGames.putIfAbsent(token, game);
        if (gameNotStartedYet(previous)) {
            startGame(token, game);
        }
    }

    @Override
    public boolean hasGameFor(IWarGameToken gameToken) {
        final boolean hasGameForToken = activeGames.containsKey(gameToken);
        LOG.trace("HasGameForToken {} equals {}", gameToken.id(), hasGameForToken);
        return hasGameForToken;
    }

    private void startGame(final IWarGameToken token, final IWarGame game) {
        completionService.submit(new Callable<GameTaskStats>() {
            @Override
            public GameTaskStats call() throws Exception {
                TimeUnit.SECONDS.sleep(gameStartDelay);
                final long startDate = System.currentTimeMillis();
                game.start();
                final long endDate = System.currentTimeMillis();
                return new GameTaskStats(startDate, endDate, token, game);
            }
        });
    }

    private boolean gameNotStartedYet(IWarGame previous) {
        return previous == null;
    }

    public void initCompletionDaemon() {
        LOG.info("GameCompletionDaemon is being initialized");
        completionDaemonExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.interrupted()) {
                        try {
                            final GameTaskStats result = completionService.take().get();
                            final long timeElapsed = result.getEndDate() - result.getStartDate();
                            LOG.info("Game completed. Start: " + result.getStartDate() +
                                    " End: " + result.getEndDate() + ". Time elapsed: " + timeElapsed);
                            final IWarGameToken token = result.getToken();
                            final IWarGame game = result.getGame();
                            cleanUpGame(token, game);
                        } catch (ExecutionException ee) {
                            LOG.info("Execution of game has failed", ee.getCause());
                        } catch (RemoteException e) {
                            LOG.info("Communication error while cleaning up: {}", e.getMessage());
                        } catch (NotBoundException e) {
                            LOG.info("Game already unregistered. Cannot cleanup! {}", e.getMessage());
                        }
                    }
                } catch (InterruptedException e) {
                    LOG.info("Completion daemon of GameRegistry has been interrupted. " +
                            "Closing now...");
                }
            }
        });
        LOG.info("Completion daemon has been initialized");
    }

    private void cleanUpGame(IWarGameToken token, IWarGame game) throws RemoteException, NotBoundException {
        activeGames.remove(token);
        final String exportedName = token.id().toString();
        rmiRegistry.unbind(exportedName);
        LOG.info("Game {} unregistered from rmi registry", exportedName);
    }

    private static class GameTaskStats {
        private final long startDate;
        private final long endDate;
        private final IWarGameToken token;
        private final IWarGame game;

        private GameTaskStats(long startDate, long endDate, IWarGameToken token, IWarGame game) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.token = token;
            this.game = game;
        }

        private long getStartDate() {
            return startDate;
        }

        private long getEndDate() {
            return endDate;
        }

        public IWarGameToken getToken() {
            return token;
        }

        public IWarGame getGame() {
            return game;
        }
    }

    private static class DaemonThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = Executors.defaultThreadFactory().newThread(runnable);
            thread.setDaemon(true);
            return thread;
        }
    }
}
