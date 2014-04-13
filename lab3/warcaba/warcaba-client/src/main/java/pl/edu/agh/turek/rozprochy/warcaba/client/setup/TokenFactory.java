package pl.edu.agh.turek.rozprochy.warcaba.client.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.IWarManager;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.PlayerAlreadyExistsException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarGameException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Author: Piotr Turek
 */
public class TokenFactory {
    private static final Logger LOG = LoggerFactory.getLogger(TokenFactory.class);
    private IWarManager warManager;
    private Integer maxRetries;

    public TokenFactory(IWarManager warManager, Integer maxRetries) {
        this.warManager = warManager;
        this.maxRetries = maxRetries;
    }

    public IWarPlayerToken create(String name) {
        int retryCount = 0;
        IWarPlayerToken token = null;
        Scanner scanner = new Scanner(System.in);
        while (token == null && retryCount < maxRetries) {
            try {
                token = warManager.register(name);
            } catch (RemoteException e) {
                LOG.error("Communication error while retrieving user token! RetryCount={}", retryCount, e);
            } catch (PlayerAlreadyExistsException e) {
                LOG.error("Player of a given nick already exists in the system");
            } catch (WarGameException e) {
                LOG.info("Details: ", e);
            } catch (Exception e) {
                System.out.println(">>> Enter alternative nick: ");
                name = scanner.next();
            } finally {
                retryCount++;
            }
        }
        if (token != null) {
            LOG.info("User token retrieved after {} tries. Token: {}", retryCount, token);
            return token;
        } else {
            throw new BeanCreationException("Could not create user token!");
        }
    }
}
