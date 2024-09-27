import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args){
        String hostname = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to server.");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            System.out.println("Type your message ('exit' to quit): ");

            while((message = userInput.readLine()) != null) {
                out.println(message);
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnected from server.");
                    break;
                }

                String response = in.readLine();
                System.out.println(response);
            }
        } catch (
                UnknownHostException e) {
            System.err.println("Couldn't find hostname: " + hostname);
        } catch (IOException e) {
            System.out.println("I/O error with the connection to " + hostname);
        }
    }
}
