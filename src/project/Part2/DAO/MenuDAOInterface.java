package project.Part2.DAO;

import project.Part2.DTO.Menu2;
import project.Part2.Exceptions.DAOException;
import java.util.List;

public interface MenuDAOInterface {

    public List<Menu2> findAllMenu() throws DAOException;

    public Menu2 findMenuByID(int menu_id) throws DAOException;

    public void addMenuDish( String name, String dishSize, int quantity, double price) throws DAOException;

    public void deleteMenuDishByID(int menu_id) throws DAOException;

    public String findAllMenuJson() throws DAOException;

    public String findMenuByIDJson(int menu_id) throws DAOException;
}
