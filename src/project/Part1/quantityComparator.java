package project.Part1;

import java.util.Comparator;

public class quantityComparator implements Comparator<Menu> {

    private SortType sortType;

    public quantityComparator(SortType sortType){
        this.sortType = sortType;
    }

    @Override
    public int compare(Menu menu1, Menu menu2){
        int direction = sortType.getValue();
        return direction * (menu1.getQuantity() - menu2.getQuantity());
    }

}
