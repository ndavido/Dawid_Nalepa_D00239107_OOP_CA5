package project.Part3;

import project.Part3.DAO.*;
import project.Part3.Exceptions.DAOException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            String message;
            try
            {
                MenuDAOInterface IMenuDao = new MySqlMenuDAO();
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                    if (message.startsWith("1") || message.startsWith("5"))
                    {
                        String showMenu = IMenuDao.findAllMenuJson();
                        socketWriter.println(showMenu);  // sends current time to client
                    }
                    else if (message.startsWith("2"))
                    {
                        String menu_id = socketReader.readLine();
                        String menu = IMenuDao.findMenuByIDJson(Integer.parseInt(menu_id));
                        if (menu != null) // null returned if userid and password not valid
                            socketWriter.println("Menu item found: " + menu);
                        else
                            socketWriter.println("Menu item with such ID not found");
                    }
                    else if (message.startsWith("3")){
                        String dishName = socketReader.readLine();
                        String dishSize = socketReader.readLine();
                        String quantity = socketReader.readLine();
                        String price = socketReader.readLine();

                        IMenuDao.addMenuDish(dishName, dishSize, Integer.parseInt(quantity), Double.parseDouble(price));
                        socketWriter.println("Order has been added");
                    }
                    else if (message.startsWith("4")){
                        String delete = socketReader.readLine();
                        IMenuDao.deleteMenuDishByID(Integer.parseInt(delete));
                        socketWriter.println("Order has been deleted");
                    }
                    else
                    {
                        socketWriter.println("I'm sorry I don't understand :(");
                    }
                }

                socket.close();

            } catch (IOException ex)
            {
                ex.printStackTrace();
            } catch (DAOException e) {
                e.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

}
