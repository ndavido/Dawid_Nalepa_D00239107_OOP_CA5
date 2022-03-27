package project.deliverable1;

import project.deliverable1.Menu;
import project.deliverable1.SortType;

import java.util.Comparator;

public class dishNameComparator implements Comparator<Menu>{

    private SortType sortType;

    public dishNameComparator(SortType sortType){
        this.sortType = sortType;
    }

    @Override
    public int compare(Menu menu1, Menu menu2){
        return menu1.getName().compareToIgnoreCase(menu2.getName());
    }
}
