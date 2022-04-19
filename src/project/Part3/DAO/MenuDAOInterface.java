package project.Part3.DAO;

import project.Part3.Exceptions.DAOException;
import project.Part3.DTO.Menu3;
import java.util.List;

public interface MenuDAOInterface {

    public void addMenuDish( String name, String dishSize, int quantity, double price) throws DAOException;

    public void deleteMenuDishByID(int menu_id) throws DAOException;

    public String findAllMenuJson() throws DAOException;

    public String findMenuByIDJson(int menu_id) throws DAOException;
}
