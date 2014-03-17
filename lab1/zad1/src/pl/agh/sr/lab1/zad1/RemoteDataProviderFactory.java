package pl.agh.sr.lab1.zad1;

/**
 * Author: Piotr Turek
 */
public class RemoteDataProviderFactory implements IRemoteDataProviderFactory {
    private int messageSize;

    public RemoteDataProviderFactory(int messageSize) {
        this.messageSize = messageSize;
    }

    @Override
    public IRemoteDataProvider create() {
        switch (messageSize) {
            case 1:
                return new RemoteChar();
            case 2:
                return new RemoteShort();
            case 4:
                return new RemoteInt();
            case 8:
                return new RemoteLong();
            default:
                throw new NoSuchDataTypePresentException();
        }
    }
}
