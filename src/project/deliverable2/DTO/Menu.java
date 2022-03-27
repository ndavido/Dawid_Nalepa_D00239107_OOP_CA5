package project.deliverable2.DTO;

import java.util.Objects;

public class Menu {

    private int iD;
    private String name;
    private String dishSize;
    private int quantity;
    private double price;

    public Menu(int id, String name, String dishSize, int quantity, double price)
    {
        this.iD = id;
        this.name = name;
        this.dishSize = dishSize;
        this.quantity = quantity;
        this.price = price;
    }

    //Getters
    public int getID(){ return iD; }
    public String getName() { return name; }
    public String getDishSize() { return dishSize; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    //Setters

    public void setID(int iD) { this.iD = iD; }
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
        return iD == menu.iD && quantity == menu.quantity && Double.compare(menu.price, price) == 0 && name.equals(menu.name) && dishSize.equals(menu.dishSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, name, dishSize, quantity, price);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", dishSize='" + dishSize + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
