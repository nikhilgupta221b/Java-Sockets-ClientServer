import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 12345;

        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server Started. Waiting for clients...");

            try(Socket clientSocket = serverSocket.accept()){
                System.out.println("Client connected");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;

                // Read message from client and echo them back
                while ((message = in.readLine()) != null){
                    System.out.println("Received message: " + message);
                    out.println("Echo: " + message);

                    if ("exit".equalsIgnoreCase(message)){
                        System.out.println("Client disconnected");
                        break;
                    }
                }
            } catch (IOException e){
                System.err.println("Error communicating with client: " + e.getMessage());
            }
        } catch (IOException e){
            System.err.println("Error communicating with client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
