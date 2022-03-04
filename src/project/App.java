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
            displayMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMainMenu() throws IOException{
        final int ARRAYLIST = 1;
        final int HASHMAP = 2;
        final int TREEMAP = 3;
        final int PRIORITYQUEUE = 4;
        final int EXIT = 5;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n(1)ArrayList\n(2)HashMap\n(3)TreeMap\n(4)Priority Queue\n(5)Exit");
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
                    case PRIORITYQUEUE:
                        System.out.println("Displaying the populated Priority Queue\n");
                        displayPriorityQueue();
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
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");
    }

    public void displayArrayList(){
        ArrayList<Menu> menuArray = new ArrayList<>();

        menuArray.add(new Menu("Mushroom Soup","Medium",1,15.99));
        menuArray.add(new Menu("Spaghetti","Small",2,18.50));
        menuArray.add(new Menu("Mashed Potatoes","Large",1,20.99));
        menuArray.add(new Menu("Cranberry Sorbet","Medium",1,9.99));
        menuArray.add(new Menu("Warm Ice-Cream","Large",3,14.99));
        menuArray.add(new Menu("Lasagna","Small",1,17.99));
        menuArray.add(new Menu("Caesar Salad","Small",1,8.99));
        menuArray.add(new Menu("Chicken Tika Masala","Medium",1,15.99));
        menuArray.add(new Menu("Smoked Salmon","Small",1,25.99));
        menuArray.add(new Menu("Steak","Large",1,59.99));

        for(Menu i : menuArray){
            System.out.println(i);
        }
    }

    public void displayHashMap() {
        Scanner kb = new Scanner(System.in);
        Map<Integer, Menu> menuMap = new HashMap<>();

        menuMap.put(1,new Menu("Mushroom Soup","Medium",1,15.99));
        menuMap.put(2,new Menu("Spaghetti","Small",2,18.50));
        menuMap.put(3,new Menu("Mashed Potatoes","Large",1,20.99));
        menuMap.put(4,new Menu("Cranberry Sorbet","Medium",1,9.99));
        menuMap.put(5,new Menu("Warm Ice-Cream","Large",3,14.99));
        menuMap.put(6,new Menu("Lasagna","Small",1,17.99));
        menuMap.put(7,new Menu("Caesar Salad","Small",1,8.99));
        menuMap.put(8,new Menu("Chicken Tika Masala","Medium",1,15.99));
        menuMap.put(9,new Menu("Smoked Salmon","Small",1,25.99));
        menuMap.put(10,new Menu("Steak","Large",1,59.99));

        System.out.println("Please enter a valid key\nValid key range (1 - 10)");
        System.out.print("Your choice: ");
        int key = kb.nextInt();

        if(menuMap.containsKey(key)) {
            System.out.println(menuMap.get(key));
        }
        else{
            System.out.println("menuMap does not contain the key " + key);
        }
    }

    public void displayTreeMap() {
        TreeMap<Integer, Menu> menuTreeMap = new TreeMap<>();

        menuTreeMap.put(3,new Menu("Mushroom Soup","Medium",1,15.99));
        menuTreeMap.put(10,new Menu("Spaghetti","Small",2,18.50));
        menuTreeMap.put(1,new Menu("Mashed Potatoes","Large",1,20.99));
        menuTreeMap.put(4,new Menu("Cranberry Sorbet","Medium",1,9.99));
        menuTreeMap.put(5,new Menu("Warm Ice-Cream","Large",3,14.99));
        menuTreeMap.put(8,new Menu("Lasagna","Small",1,17.99));
        menuTreeMap.put(7,new Menu("Caesar Salad","Small",1,8.99));
        menuTreeMap.put(6,new Menu("Chicken Tika Masala","Medium",1,15.99));
        menuTreeMap.put(9,new Menu("Smoked Salmon","Small",1,25.99));
        menuTreeMap.put(2,new Menu("Steak","Large",1,59.99));

        Set<Integer> keySet = menuTreeMap.keySet();

        for (int key : keySet) {
            Menu menu = menuTreeMap.get(key);
            System.out.println("Key: " + key +
                               " {Name = " + menu.getName() +
                               ", Dish Size = " + menu.getDishSize() +
                               ", Quantity = " + menu.getQuantity() +
                               ", Price = " + menu.getPrice() + "}");
        }
    }

    public void displayPriorityQueue(){
        PriorityQueue<Menu> menuPriorityQueue = new PriorityQueue<>();

        menuPriorityQueue.add(new Menu("Mushroom Soup", "Medium", 1, 15.99));
        menuPriorityQueue.add(new Menu("Spaghetti","Small",2,18.50));
        menuPriorityQueue.add(new Menu("Mashed Potatoes","Large",1,20.99));
        menuPriorityQueue.add(new Menu("Cranberry Sorbet","Medium",1,9.99));
        menuPriorityQueue.add(new Menu("Warm Ice-Cream","Large",3,14.99));
        menuPriorityQueue.add(new Menu("Lasagna","Small",1,17.99));
        menuPriorityQueue.add(new Menu("Caesar Salad","Small",1,8.99));
        menuPriorityQueue.add(new Menu("Chicken Tika Masala","Medium",1,15.99));
        menuPriorityQueue.add(new Menu("Smoked Salmon","Small",1,25.99));
        menuPriorityQueue.add(new Menu("Steak","Large",1,59.99));

        while(!menuPriorityQueue.isEmpty()){
            System.out.println(menuPriorityQueue.remove());
        }
    }

}
