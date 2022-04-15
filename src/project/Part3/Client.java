package project.Part3;

import project.Part3.DTO.CompareDishNameQuantity;
import project.Part3.Exceptions.DAOException;
import project.Part3.DTO.Menu3;
import project.Part3.DAO.MenuDAOInterface;
import project.Part3.DAO.MySqlMenuDAO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
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


            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
            MenuDAOInterface IMenuDao = new MySqlMenuDAO();
            final int DISPLAY_MENU = 1;
            final int DISPLAY_BY_ID = 2;
            final int ADD_MENU_DISH = 3;
            final int DELETE_MENU_DISH = 4;
            final int SHOW_FILTERED_MENU = 5;
            final int SHOW_MENU_AS_JSON = 6;
            final int FIND_MENU_ORDER_BY_ID_FROM_JSON = 7;
            final int EXIT = 8;

            System.out.println("\n=================================================");
            System.out.println("===============  Restaurant Menu  ===============");
            System.out.println("=================================================");

            int option = 0;
            Scanner keyboard = new Scanner(System.in);
            boolean keep_looping = true;
            while(keep_looping == true) {

                System.out.println("\n=================================================");
                System.out.println("(1)Display Menu\n" +
                        "(2)Find Menu Item by ID\n" +
                        "(3)Add Dish to Menu\n" +
                        "(4)Delete Dish from Menu by ID\n" +
                        "(5)Filter Menu Orders According to Name and Quantity\n" +
                        "(6)Show Menu as a JSON string\n" +
                        "(7)Find Menu Item by ID from JSON string\n" +
                        "(8)Exit");

                System.out.print("\nYour Choice: ");

                try {
                    String usersInput = keyboard.nextLine();
                    option = Integer.parseInt(usersInput);
                    List<Menu3> menus = IMenuDao.findAllMenu();
                    System.out.println("=================================================\n");
                    switch (option) {
                        case DISPLAY_MENU:
                            System.out.println("Showing all Menu options\n");
                            displayMenu(menus);
                            break;
                        case DISPLAY_BY_ID:
                            System.out.println("Showing Menu option by ID\n");

                            displayMenuByID(IMenuDao);
                            break;
                        case ADD_MENU_DISH:
                            System.out.println("Showing Add Option\n");

                            addDishToMenu(IMenuDao);
                            break;
                        case DELETE_MENU_DISH:
                            System.out.println("Showing Delete Option\n");

                            deleteDishByID(IMenuDao);
                            break;
                        case SHOW_FILTERED_MENU:
                            System.out.println("Showing Menu Orders filtered by price\n");

                            Collections.sort(menus, new CompareDishNameQuantity());

                            displayMenu(menus);
                            break;
                        case SHOW_MENU_AS_JSON:
                            System.out.println("Showing Menu as a JSON string\n");

                            String menusJsonString = IMenuDao.findAllMenuJson();

                            System.out.println(menusJsonString);

                            break;
                        case FIND_MENU_ORDER_BY_ID_FROM_JSON:
                            System.out.println("Finding Menu Item by ID from a JSON string\n");

                            Scanner kb = new Scanner(System.in);
                            System.out.print("Please select an item searching by ID: ");
                            int menu_id = kb.nextInt();

                            String menu = IMenuDao.findMenuByIDJson(menu_id);

                            if (menu != null) {
                                System.out.println("Menu item found: " + menu);
                            }
                            else {
                                System.out.println("Menu item with such ID not found");
                            }
                            break;
                        case EXIT:
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
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }

    public List<Menu3> displayMenu(List<Menu3> menus) {
        if (menus.isEmpty())
            System.out.println("There is no Menu");
        else {
            for (Menu3 menu : menus)
                System.out.println(menu.toString());
        }
        return menus;
    }

    public Menu3 displayMenuByID(MenuDAOInterface IMenuDao) throws DAOException {
        Scanner kb = new Scanner(System.in);
        System.out.print("Please select an item searching by ID: ");
        int menu_id = kb.nextInt();

        Menu3 menu = IMenuDao.findMenuByID(menu_id);

        if (menu != null) // null returned if userid and password not valid
            System.out.println("Menu item found: " + menu);
        else
            System.out.println("Menu item with such ID not found");

        return menu;
    }

    public void addDishToMenu(MenuDAOInterface IMenuDao) throws DAOException {
        Scanner kb = new Scanner(System.in);

        System.out.print("Dishes name: ");
        String dishName = kb.nextLine();

        System.out.print("\nDish Size: ");
        String dishSize = kb.nextLine();

        System.out.print("\nQuantity: ");
        int quantity = kb.nextInt();

        System.out.print("\nPrice: ");
        double price = kb.nextDouble();

        IMenuDao.addMenuDish(dishName, dishSize, quantity, price);
    }

    public void deleteDishByID(MenuDAOInterface IMenuDao) throws DAOException {
        Scanner kb = new Scanner(System.in);

        System.out.print("Enter ID of Item you want to delete: ");
        int menu_id = kb.nextInt();

        IMenuDao.deleteMenuDishByID(menu_id);
    }
}