package project.deliverable1;

import java.util.Objects;

public class Menu {

    private String name;
    private String dishSize;
    private int quantity;
    private double price;

    public Menu(String name, String dishSize, int quantity, double price)
    {
        this.name = name;
        this.dishSize = dishSize;
        this.quantity = quantity;
        this.price = price;
    }

    //Getters
    public String getName() { return name; }
    public String getDishSize() { return dishSize; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    //Setters
    public void setName(String name)
    {
        this.name = name;
    }
    public void setDishSize(String dishSize) { this.dishSize = dishSize; }
    public void setQuantity(int quantity){ this.quantity = quantity; }
    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return quantity == menu.quantity && Double.compare(menu.price, price) == 0 && Objects.equals(name, menu.name) && Objects.equals(dishSize, menu.dishSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dishSize, quantity, price);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "Name = " + name +
                ", Dish Size = " + dishSize +
                ", Quantity = " + quantity +
                ", Price = " + price +
                '}';
    }
}
