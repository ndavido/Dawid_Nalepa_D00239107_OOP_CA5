package project.Part3;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import project.Part3.DAO.*;
import project.Part3.DTO.*;
import project.Part3.Exceptions.DAOException;

import java.util.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Collections;
import java.util.InputMismatchException;
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
        Gson gsonParser = new Gson();
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );
            System.out.println("Client message: The Client is running and has connected to the server");

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers
            final String DISPLAY_MENU = "1";
            final String DISPLAY_BY_ID = "2";
            final String ADD_MENU_DISH = "3";
            final String DELETE_MENU_DISH = "4";
            final String SHOW_FILTERED_MENU = "5";
            final String EXIT = "6";

            System.out.println("\n=================================================");
            System.out.println("===============  Restaurant Menu  ===============");
            System.out.println("=================================================");


            boolean keep_looping = true;
            while(keep_looping == true) {
                System.out.println("\n=================================================");
                System.out.println("(1)Display Menu\n" +
                        "(2)Find Menu Item by ID\n" +
                        "(3)Add Dish to Menu\n" +
                        "(4)Delete Dish from Menu by ID\n" +
                        "(5)Filter Menu Orders According to Name and Quantity\n" +
                        "(6)Exit");
                System.out.print("\nYour Choice: ");
                String command = in.nextLine();

                socketWriter.println(command);
                try {
                    System.out.println("=================================================\n");
                    switch (command) {
                        case DISPLAY_MENU:
                            System.out.println("Showing all Menu options\n");

                            String displayMenu = socketReader.nextLine();
                            Type menuListType = new TypeToken<ArrayList<Menu3>>(){}.getType();
                            List<Menu3> allMenu = gsonParser.fromJson(displayMenu, menuListType);
                            for (Menu3 menu : allMenu) {
                                System.out.println(menu);
                            }

                            break;
                        case DISPLAY_BY_ID:
                            System.out.println("Showing Menu option by ID\n");

                            System.out.print("Please select an item searching by ID: ");
                            int menu_id = in.nextInt();
                            socketWriter.println(menu_id);
                            String displayByID = socketReader.nextLine();
                            System.out.println("Client message: Response from server: \"" + displayByID + "\"");

                            break;
                        case ADD_MENU_DISH:
                            System.out.println("Showing Add Option\n");

                            System.out.print("Dishes name: ");
                            String dishName = in.nextLine();
                            socketWriter.println(dishName);

                            System.out.print("\nDish Size: ");
                            String dishSize = in.nextLine();
                            socketWriter.println(dishSize);

                            System.out.print("\nQuantity: ");
                            int quantity = in.nextInt();
                            socketWriter.println(quantity);

                            System.out.print("\nPrice: ");
                            double price = in.nextDouble();
                            socketWriter.println(price);

                            String orderAdded = socketReader.nextLine();
                            System.out.println("Client message: Response from server: \"" + orderAdded + "\"");

                            break;
                        case DELETE_MENU_DISH:
                            System.out.println("Showing Delete Option\n");

                            System.out.print("Enter ID of Item you want to delete: ");
                            int delete = in.nextInt();
                            socketWriter.println(delete);
                            String deletedID = socketReader.nextLine();
                            System.out.println("Client message: Response from server: \"" + deletedID + "\"");

                            break;
                        case SHOW_FILTERED_MENU:
                            System.out.println("Showing Menu Orders filtered by price\n");

                            String filteredMenu = socketReader.nextLine();
                            Type filteredMenuListType = new TypeToken<ArrayList<Menu3>>(){}.getType();
                            List<Menu3> allFilteredMenu = gsonParser.fromJson(filteredMenu, filteredMenuListType);
                            Collections.sort(allFilteredMenu, new CompareDishNameQuantity());
//
                            for (Menu3 menu : allFilteredMenu) {
                                System.out.println(menu);
                            }
//                            displayMenu(menus);
                            break;
                        case EXIT:
                            keep_looping = false;
                            socketWriter.close();
                            socketReader.close();
                            socket.close();
                            break;
                        default:
                            System.out.print("Invalid option - please enter number in range");
                            break;
                    }

                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("=================================================");
                    System.out.println("Invalid option - please enter number in range");
                }
            }
        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}