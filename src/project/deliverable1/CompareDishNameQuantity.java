package project.deliverable1;

import project.deliverable1.Menu;

import java.util.Comparator;

public class CompareDishNameQuantity implements Comparator<Menu> {

    @Override
    public int compare(Menu m1, Menu m2){
        if(m1.getName().equalsIgnoreCase(m2.getName())){
            return m1.getQuantity() - m2.getQuantity();
        }
        else{
            return m1.getName().compareToIgnoreCase(m2.getName());
        }
    }
}
