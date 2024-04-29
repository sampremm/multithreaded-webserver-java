import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    
    public void run() throws IOException, UnknownHostException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        
        try {
            while (true) {
                System.out.println("The server is listening on port " + port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());
                
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                
                toClient.println("Hello from the server");
                String line = fromClient.readLine();
                System.out.println("This is from the socket: " + line);
                
                // Close resources for this connection
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            }
        } finally {
            // Close the server socket outside the loop
            socket.close();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
       
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
