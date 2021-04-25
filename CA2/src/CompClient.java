import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CompClient {
    public static void main (String[] args) {
        // set the security manager for the client
        System.setSecurityManager(new SecurityManager());
        //get the remote object from the registry
        try{
            String url = "//localhost/COMP-SERVER";
            CompServer remoteObject = (CompServer) Naming.lookup(url);
            System.out.println("The larger number is... " + remoteObject.comp(3,5));
            System.out.println("The time is..." + remoteObject.getTime());
        } catch (RemoteException e) {
            System.out.println("Remote: " + e.getMessage());
        } catch (NotBoundException e) {
            System.out.println("Not Bound: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
    }
}

