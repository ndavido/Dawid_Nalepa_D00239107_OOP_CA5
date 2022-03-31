package project.deliverable2.DTO;

import java.util.Comparator;

public class CompareDishNameQuantity implements Comparator<Menu2> {

    @Override
    public int compare(Menu2 m1, Menu2 m2){
        if(m1.getName().equalsIgnoreCase(m2.getName())){
            return m1.getQuantity() - m2.getQuantity();
        }
        else{
            return m1.getName().compareToIgnoreCase(m2.getName());
        }
    }
}
