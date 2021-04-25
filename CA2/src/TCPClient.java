
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
    public static void main (String args[]) {
        // arguments supply message and hostname of destination
        Socket s = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your message:");
        String input = sc.nextLine();
        try {
            if (!input.equals("exit")) {
                int serverPort = 7896;
                s = new Socket(args[0], serverPort);
            }
            DataInputStream  in  = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            while(!input.equals("exit")) {
                out.writeUTF(input);
                String data = in.readUTF();
                System.out.println("Received: " + data);
                System.out.println("Enter your message:");
                input = sc.nextLine();
            }
        System.out.println("Exiting...");
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null) try {
                s.close();
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
        }
    }
}
