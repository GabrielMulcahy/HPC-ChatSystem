import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ChatServant extends UnicastRemoteObject implements Chat {
    private Vector<String> history = new Vector<String>();
    private int version;
    private String password;
    private String topic;

    ChatServant(String topic, String password) throws RemoteException{
        this.topic    = topic;
        this.password = password;
    }

    @Override
    public void newMessage(String message) throws RemoteException {
        version++;
        history.add(message);
    }
    @Override
    public Vector getHistory() throws RemoteException {
        return history;
    }
    @Override
    public int getVersion() throws RemoteException {
        return version;
    }
    @Override
    public boolean checkPassword(String pw) throws RemoteException {
        return pw.equals(password);
    }
    @Override
    public String getTopic(){
        return topic;
    }
}
