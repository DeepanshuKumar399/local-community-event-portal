import java.io.*;
import java.net.*;

public class Ex35_TCPChat {

    static class Server {
        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server listening on port 5000...");
            Socket client = serverSocket.accept();
            System.out.println("Client connected: " + client.getInetAddress());

            BufferedReader in  = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter    out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Client: " + msg);
                System.out.print("Server: ");
                out.println(kbd.readLine());
            }
            serverSocket.close();
        }
    }

    static class Client {
        public static void main(String[] args) throws IOException {
            Socket         socket = new Socket("localhost", 5000);
            PrintWriter    out    = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in     = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader kbd    = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Client: ");
                String msg = kbd.readLine();
                out.println(msg);
                System.out.println("Server: " + in.readLine());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Run Ex35_TCPChat$Server in one terminal and Ex35_TCPChat$Client in another.");
    }
}
