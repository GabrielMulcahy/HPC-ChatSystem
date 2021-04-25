import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Chat extends Remote {
    void newMessage(String message) throws RemoteException;
    Vector getHistory() throws RemoteException;
    int getVersion() throws RemoteException;
    boolean checkPassword(String pw) throws RemoteException;
    String getTopic() throws RemoteException;
}

