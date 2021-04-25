import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CompServer extends Remote {
    public int comp (int a, int b) throws RemoteException;

    public String getTime() throws RemoteException;
}
