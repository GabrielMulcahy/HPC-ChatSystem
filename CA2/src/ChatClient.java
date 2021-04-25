import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ChatClient {

    private static Chat aChat;

    public static void main(String args[]){

        System.setSecurityManager(new SecurityManager());
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String username = sc.nextLine();
        System.out.println("What chatroom would you like to join?");
        String topic = sc.nextLine();
        System.out.println("Enter the password for " + topic);
        String password = sc.nextLine();
        String message = username + " has joined the chat.";
        try {
            // create an instance of the chat room specified
            aChat = (Chat) Naming.lookup("//localhost/" + topic);
            // loop until the user enters the correct password
            while (!aChat.checkPassword(password)){
                System.out.println("Password incorrect. Please enter the correct password:");
                password = sc.nextLine();
            }
            // send the message stating that the user has joined the chat
            aChat.newMessage(username + ": " + message);
            // start the version checker
            Thread vc = new Thread(new versionChecker());
            vc.start();
            // prompt the user to send new messages until they exit the chat
            while (true) {
                message = sc.nextLine();
                if (message.equals("exit")) break;
                aChat.newMessage(username + ": " + message);
            }
            // kill the version checker
            vc.interrupt();
            aChat.newMessage(username + " left the chat.");
            System.exit(0);
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            System.out.println("Client: " + e.getMessage());
        }
    }

    /**
     * nested class that constantly checks if the chat has a new version
     * prints the last message from the chat's history if a new version is detected
     */
    static class versionChecker implements Runnable {
        @Override
        public void run() {
            int version = 0;
            while (true){
                try {
                    if (aChat.getVersion() > version) {
                        version = aChat.getVersion();
                        System.out.println(aChat.getHistory().lastElement());
                    }
                } catch (RemoteException e) {
                    System.out.println("Client's version checker: " + e.getMessage());
                }
            }
        }
    }
}
