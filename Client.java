package RevisedGamestore;

import java.net.Socket;             // Used to connect to the server
import java.io.ObjectInputStream;   // Used to read objects sent from the server
import java.io.ObjectOutputStream;  // Used to write objects to the server
import java.io.BufferedReader;      // Needed to read from the console
import java.io.InputStreamReader;   // Needed to read from the console

/**
 * Simple client class. This class connects to an EchoServer to send text back
 * and forth. Java message serialization is used to pass Message objects around.
 *
 *
 */
public class Client {

    /**
     * Main method.
     *
     *
     */
    public static void main(String[] args) {
        try {
            String serverName;
            //Prompt the user to enter the Server to connect to
            System.out.println("Enter Server name or IP > \n");
            System.out.print(" > ");

            //Read user input for the server name or IP address 
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            serverName = in.readLine();

            // Connect to the specified server
            final Socket sock = new Socket(serverName, Server.SERVER_PORT);
            System.out.println("Connected to " + serverName + " on port " + Server.SERVER_PORT);

            // Set up I/O streams with the server
            final ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
            final ObjectInputStream input = new ObjectInputStream(sock.getInputStream());

            // loop to send messages
            Message msg = null, resp = null;
            do {
                // Read and send message.  Since the Message class
                // implements the Serializable interface, the
                // ObjectOutputStream "output" object automatically
                // encodes the Message object into a format that can
                // be transmitted over the socket to the server.
        //        msg = new Message(readSomeText());
        //        output.writeObject(msg);

                // Get ACK and print.  Since Message implements
                // Serializable, the ObjectInputStream can
                // automatically read this object off of the wire and
                // encode it as a Message.  Note that we need to
                // explicitly cast the return from readObject() to the
                // type Message.
                resp = (Message) input.readObject();
                System.out.println(resp.theMessage + "\n");
                
                msg = new Message(readSomeText());
                output.writeObject(msg);

            } while (!msg.theMessage.equalsIgnoreCase("0"));

            // shut things down
            sock.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }

    } //-- end main(String[])

    /**
     * Simple method to print a prompt and read a line of text.
     *
     * @return A line of text read from the console
     */
    private static String readSomeText() {
        try {
            //System.out.println("Enter a number, type y or n when asked about cart, or type \"0\" to quit.");
            System.out.print(" > ");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            return in.readLine();
        } catch (Exception e) {
            // Uh oh...
            return "";
        }

    } //-- end readSomeText()
} //-- end class EchoClient