import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {

    public void run() throws UnknownHostException, IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        try (Socket socket = new Socket(address, port);
             PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            toSocket.println("Hello World from socket " + socket.getLocalSocketAddress());
            toSocket.println("Hello from the client");
            // Ensure data is sent
            toSocket.flush();

            String line = fromSocket.readLine();
            System.out.println("Response from the server is: " + line);
        }
    }

    public static void main(String[] args) {
        try {
            client client = new client();
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
