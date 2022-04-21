package project.Part3;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import project.Part3.DTO.*;

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
        final String DISPLAY_MENU = "1";
        final String DISPLAY_BY_ID = "2";
        final String ADD_MENU_DISH = "3";
        final String DELETE_MENU_DISH = "4";
        final String SHOW_FILTERED_MENU = "5";
        final String EXIT = "6";

        System.out.println("\n=================================================");
        System.out.println("===============  Restaurant Menu  ===============");
        System.out.println("=================================================");

        Gson gsonParser = new Gson();
        Scanner in = new Scanner(System.in);
        boolean keep_looping = true;

        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );
            System.out.println("Client message: The Client is running and has connected to the server");

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            while(keep_looping == true) {
                System.out.println("\n=================================================\n");
                System.out.println("(1)Display Menu\n" +
                                   "(2)Find Menu Item by ID\n" +
                                   "(3)Add Dish to Menu\n" +
                                   "(4)Delete Dish from Menu by ID\n" +
                                   "(5)Filter Menu Orders According to Name and Quantity\n" +
                                   "(6)Exit");
                System.out.print("\nYour Choice: ");
                String command = in.next();
                System.out.println("\n=================================================\n");
                socketWriter.println(command);
                try {
                    switch (command) {

                        case DISPLAY_MENU:
                            System.out.println("Showing all Menu options\n");

                            List<Menu3> displayAll = display(socketReader, gsonParser);
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("| ID |  \t\tName\t\t  | Dish Size | Quantity |  Price   |");
                            System.out.println("-----------------------------------------------------------------");
                            for (Menu3 menu : displayAll) {
                                System.out.printf("| %-2d | %-22s | %-9s | %-8d | € %-6.2f |\n",menu.getID(),menu.getName(),menu.getDishSize(),menu.getQuantity(),menu.getPrice());
                            }
                            System.out.println("-----------------------------------------------------------------");

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
                            Scanner kb = new Scanner(System.in);
                            System.out.println("Showing Add Option\n");

                            System.out.print("Dishes name: ");
                            String dishName = kb.nextLine();
                            socketWriter.println(dishName);

                            System.out.print("\nDish Size: ");
                            String dishSize = kb.nextLine();
                            socketWriter.println(dishSize);

                            System.out.print("\nQuantity: ");
                            int quantity = kb.nextInt();
                            socketWriter.println(quantity);

                            System.out.print("\nPrice: ");
                            double price = kb.nextDouble();
                            socketWriter.println(price);

                            String orderAdded = socketReader.nextLine();
                            System.out.println("Response from server: \"" + orderAdded + "\"");

                            break;
                        case DELETE_MENU_DISH:
                            System.out.println("Showing Delete Option\n");

                            System.out.print("Enter ID of Item you want to delete: ");
                            int delete = in.nextInt();
                            socketWriter.println(delete);
                            String deletedID = socketReader.nextLine();
                            System.out.println("Response from server: \"" + deletedID + "\"");

                            break;
                        case SHOW_FILTERED_MENU:
                            System.out.println("Showing Menu Orders filtered by price\n");

                            List<Menu3> displayAllFiltered = display(socketReader, gsonParser);

                            Collections.sort(displayAllFiltered, new CompareDishNameQuantity());

                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("| ID |  \t\tName\t\t  | Dish Size | Quantity |  Price   |");
                            System.out.println("-----------------------------------------------------------------");
                            for (Menu3 menu : displayAllFiltered) {
                                System.out.printf("| %-2d | %-22s | %-9s | %-8d | € %-6.2f |\n",menu.getID(),menu.getName(),menu.getDishSize(),menu.getQuantity(),menu.getPrice());
                            }
                            System.out.println("-----------------------------------------------------------------");

                            break;
                        case EXIT:
                            keep_looping = false;
                            socketWriter.close();
                            socketReader.close();
                            socket.close();
                            break;
                        default:
                            System.out.println("Invalid option - please enter number in range");
                            break;
                    }

                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("=================================================");
                    System.out.println("Invalid option - please enter number in range");
                }
            }
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Client message: IOException: " + e);
        }
    }

    public List<Menu3> display(Scanner socketReader, Gson gsonParser){

        String displayMenu = socketReader.nextLine();
        Type menuListType = new TypeToken<ArrayList<Menu3>>(){}.getType();
        List<Menu3> allMenu = gsonParser.fromJson(displayMenu, menuListType);

        return allMenu;
    }
}