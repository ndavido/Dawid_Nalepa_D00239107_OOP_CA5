package project.deliverable2.DAO;

import project.deliverable2.DTO.Menu;
import project.deliverable2.Exceptions.DAOException;
import java.util.List;

public interface MenuDAOInterface {

    public List<Menu> findAllUsers() throws DAOException;

    public Menu findMenuByID(int menu_id) throws DAOException;
}
