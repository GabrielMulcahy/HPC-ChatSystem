import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;

    Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in  = new DataInputStream  (clientSocket.getInputStream());
            out = new DataOutputStream (clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public void run() {
        try{
            while (true) {
                String data = in.readUTF();
                data = (data + " - appended text").toUpperCase();
                out.writeUTF(data);
                System.out.println(data);
            }
        } catch (IOException e) {
            System.out.println("EOF: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Connection closed.");
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
        }
    }
}

