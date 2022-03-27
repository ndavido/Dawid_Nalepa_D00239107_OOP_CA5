package project.deliverable2;

import project.deliverable2.Exceptions.DAOException;
import project.deliverable2.DTO.Menu;
import project.deliverable2.DAO.MenuDAOInterface;
import project.deliverable2.DAO.MySqlMenuDAO;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        App app = new App();
        app.start();
    }

    public void start(){
        try {
            displayMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMainMenu() throws IOException{
        final int DISPLAY_MENU = 1;
        final int DISPLAY_BY_ID = 2;
        final int EXIT = 3;

        MenuDAOInterface IMenuDao = new MySqlMenuDAO();

        System.out.println("\n=================================================");
        System.out.println("Restaurant Menu");

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n=================================================");
            System.out.println("(1)Display Menu\n(2)Find Menu Item by ID\n(3)Exit");
            System.out.print("\nYour Choice: ");

            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                System.out.println("=================================================\n");
                switch (option) {
                    case DISPLAY_MENU:
                        System.out.println("Showing all Menu options\n");
                        List<Menu> menus = null;
                        displayMenu(IMenuDao, menus);
                        break;
                    case DISPLAY_BY_ID:
                        System.out.println("Showing Menu option by ID\n");

                        displayMenuByID(IMenuDao);
                        break;
                    case EXIT:
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
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");
    }

    public List<Menu> displayMenu(MenuDAOInterface IMenuDao, List<Menu> menus) throws DAOException {
        try {
            menus = IMenuDao.findAllUsers();

            if (menus.isEmpty())
                System.out.println("There is no Menu");
            else {
                for (Menu menu : menus)
                    System.out.println(menu.toString());
            }
        }catch (DAOException e) {
            e.printStackTrace();
        }
        return menus;
    }

    public Menu displayMenuByID(MenuDAOInterface IMenuDao) throws DAOException {
        Scanner kb = new Scanner(System.in);
        System.out.print("Please select an item searching by ID: ");
        int menu_id = kb.nextInt();

        Menu menu = IMenuDao.findMenuByID(menu_id);

        if (menu != null) // null returned if userid and password not valid
            System.out.println("Menu item found: " + menu);
        else
            System.out.println("Menu item with such ID not found");

        return menu;
    }
}
