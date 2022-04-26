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

                        socketWriter.println(menu);
                    }
                    else if (message.startsWith("3")){
                        String addToDatabase = socketReader.readLine();

                        String[] tokens = addToDatabase.split(";");

                        String dishName = tokens[0];
                        String dishSize = tokens[1];
                        int quantity = Integer.parseInt(tokens[2]);
                        double price = Double.parseDouble(tokens[3]);

                        IMenuDao.addMenuDish(dishName, dishSize, quantity,price);
                        socketWriter.println("Order has been added");
                    }
                    else if (message.startsWith("4")){
                        String delete = socketReader.readLine();
                        IMenuDao.deleteMenuDishByID(Integer.parseInt(delete));
                        socketWriter.println("Order has been deleted");
                    }
                    else if (message.startsWith("4")){
                        String delete = socketReader.readLine();
                        IMenuDao.deleteMenuDishByID(Integer.parseInt(delete));
                        socketWriter.println("Order has been deleted");
                    }
                    else if (message.startsWith("6")){
                        String highestQuantity = IMenuDao.findQuantityGreaterThanJson();
                        socketWriter.println(highestQuantity);
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
