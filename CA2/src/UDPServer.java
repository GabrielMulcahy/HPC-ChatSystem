import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main (String args[]){
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];
            System.out.println("Listening on port 6789...");
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(),
                                                          request.getAddress(), request.getPort());
                aSocket.send(reply);
                System.out.println(new String(request.getData()));
            }
        } catch (IOException e) {
            System.out.println("UDPServer: " + e.getMessage());
        }
    }
}

