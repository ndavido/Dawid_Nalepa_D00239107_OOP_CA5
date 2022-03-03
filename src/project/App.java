package project;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {
	    App app = new App();
        app.start();
    }

    public void start(){
        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMainMenu() throws IOException{
        final int ARRAYLIST = 1;
        final int HASHMAP = 2;
        final int TREEMAP = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n(1)ArrayList\n(2)HashMap\n(3)TreeMap\n(4)Exit");
            System.out.print("\nYour Choice: ");

            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                System.out.println("=================================================\n");
                switch (option) {
                    case ARRAYLIST:
                        System.out.println("Displaying the populated ArrayList\n");
                        displayArrayList();
                        break;
                    case HASHMAP:
                        System.out.println("Displaying the populated HashMap\n");
                        displayHashMap();
                        break;
                    case TREEMAP:
                        System.out.println("Displaying the populated TreeMap\n");
                        displayTreeMap();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("=================================================");
                System.out.println("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");
    }

    public void displayArrayList(){
        ArrayList<Menu> order = new ArrayList<>();

        order.add(new Menu("Mushroom Soup","Medium",1,15.99));
        order.add(new Menu("Spaghetti","Small",2,18.50));
        order.add(new Menu("Mashed Potatoes","Large",1,20.99));
        order.add(new Menu("Cranberry Sorbet","Medium",1,9.99));
        order.add(new Menu("Warm Ice-Cream","Large",3,14.99));
        order.add(new Menu("Lasagna","Small",1,17.99));
        order.add(new Menu("Caesar Salad","Small",1,8.99));
        order.add(new Menu("Chicken Tika Masala","Medium",1,15.99));
        order.add(new Menu("Smoked Salmon","Small",1,25.99));
        order.add(new Menu("Steak","Large",1,59.99));

        for(Menu i : order){
            System.out.println(i);
        }
    }

    public void displayHashMap() {
        Map<Long, Menu> menuMap = new HashMap<>();

        menuMap.put(100001L,new Menu("Mushroom Soup","Medium",1,15.99));
        menuMap.put(100002L,new Menu("Spaghetti","Small",2,18.50));
        menuMap.put(100003L,new Menu("Mashed Potatoes","Large",1,20.99));
        menuMap.put(100004L,new Menu("Cranberry Sorbet","Medium",1,9.99));
        menuMap.put(100005L,new Menu("Warm Ice-Cream","Large",3,14.99));
        menuMap.put(100006L,new Menu("Lasagna","Small",1,17.99));
        menuMap.put(100007L,new Menu("Caesar Salad","Small",1,8.99));
        menuMap.put(100008L,new Menu("Chicken Tika Masala","Medium",1,15.99));
        menuMap.put(100009L,new Menu("Smoked Salmon","Small",1,25.99));
        menuMap.put(100010L,new Menu("Steak","Large",1,59.99));

        Set<Long> keySet = menuMap.keySet();

        for (Long key : keySet) {
            Menu menu = menuMap.get(key);
            System.out.println(key + " Name: " + menu.getName()
                                   + ", Dish Size: " + menu.getDishSize()
                                   + ", Quantity: " + menu.getQuantity()
                                   + ", Price: " + menu.getPrice());
        }
    }

    public void displayTreeMap() {

    }

}
