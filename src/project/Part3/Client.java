package project.Part3;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");

            System.out.println("Please enter a command:  (\"Time\" to get time, or \"Echo message\" to get echo) \n>");
            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
            final String COMMAND_REQUEST_TIME = "Time";
            boolean keep_looping = true;
            while(keep_looping == true) {

                if (command.equalsIgnoreCase(COMMAND_REQUEST_TIME))   //we expect the server to return a time
                {
                    String timeString = socketReader.nextLine();
                    System.out.println("Client message: Response from server Time: " + timeString);
                } else if(command.startsWith("Echo"))                            // the user has entered the Echo command or an invalid command
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }
                else if(command.startsWith("Triple")){
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }
                else if(command.startsWith("Add")){
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }
                else if(command.startsWith("Subtract")){
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }
                else if(command.startsWith("Multiply")){
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }
                else if(command.startsWith("Divide")){
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }

                System.out.println("Enter next command: ");
                command = in.nextLine();
                socketWriter.println(command);

            }
            socketWriter.close();
            socketReader.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}