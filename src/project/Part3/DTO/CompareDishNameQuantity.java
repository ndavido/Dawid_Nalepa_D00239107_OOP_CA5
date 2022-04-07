package project.Part3.DTO;

import java.util.Comparator;

public class CompareDishNameQuantity implements Comparator<Menu3>{

    @Override
    public int compare(Menu3 m1, Menu3 m2){
        if(m1.getName().equalsIgnoreCase(m2.getName())){
            return m1.getQuantity() - m2.getQuantity();
        }
        else{
            return m1.getName().compareToIgnoreCase(m2.getName());
        }
    }
}
