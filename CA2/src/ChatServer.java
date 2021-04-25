import java.rmi.Naming;

public class ChatServer {
    public static void main(String args[]){
        System.setSecurityManager(new SecurityManager());
        try {
            // establish two separate chat rooms to connect to
            Chat aChat = new ChatServant("Food","ilovepizza");
            Naming.rebind(aChat.getTopic(), aChat);
            System.out.println("Food is ready...");

            Chat anotherChat = new ChatServant("Cars","iloveford");
            Naming.rebind(anotherChat.getTopic(), anotherChat);
            System.out.println("Cars is ready...");
        } catch (Exception e) {
            System.out.println("Chat Server " + e.getMessage());
        }
    }
}
