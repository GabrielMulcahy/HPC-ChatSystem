import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CompServerImpl extends UnicastRemoteObject implements CompServer {

    CompServerImpl() throws RemoteException {}

    public int comp (int a, int b) throws RemoteException {
        // if both are the same, it doesn't matter which one is returned but will be b
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public String getTime () throws RemoteException{
        SimpleDateFormat date = new SimpleDateFormat("hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        return date.format(calendar.getTime());
    }

    public static void main (String args[]){
        try {
            //set the security manager
            System.setSecurityManager(new SecurityManager());
            //create a local instance of the object SumServerImpl
            CompServerImpl aServer = new CompServerImpl();
            //put the local instance in the registry
            Naming.rebind("COMP-SERVER", aServer);
            System.out.println("Ready to compare...");
        } catch (RemoteException e) {
            System.out.println("Remote: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
    }
}
