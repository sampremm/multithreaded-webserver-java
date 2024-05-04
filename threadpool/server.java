
import java.net.ServerSocket;



class server {
 
    public static void main(String[] args) {
        int port=8010;
        int poolsize=10;
        Server server=new server();

        try {
            ServerSocket serverSocket=new ServerSocket();
            serverSocket.setSoTimeout(70000);
            System.out.println("Server is listening "+ port);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
    
}