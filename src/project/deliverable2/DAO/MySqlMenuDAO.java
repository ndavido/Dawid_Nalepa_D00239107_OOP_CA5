package project.deliverable2.DAO;

import project.deliverable2.Exceptions.DAOException;
import project.deliverable2.DTO.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlMenuDAO extends MySqlDAO implements MenuDAOInterface{
    @Override
    public List<Menu> findAllUsers() throws DAOException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Menu> usersList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM MENU";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int menuID = resultSet.getInt("MENU_ID");
                String name = resultSet.getString("NAME");
                String dishSize = resultSet.getString("DISH_SIZE");
                int quantity = resultSet.getInt("QUANTITY");
                double price = resultSet.getDouble("PRICE");

                Menu m = new Menu(menuID, name, dishSize, quantity, price);
                usersList.add(m);
            }
        } catch (SQLException e)
        {
            throw new DAOException("findAllUseresultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DAOException("findAllUsers() " + e.getMessage());
            }
        }
        return usersList;     // may be empty
    }

    @Override
    public Menu findMenuByID(int menu_id) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Menu menu = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM MENU WHERE MENU_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, menu_id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                int menuId = resultSet.getInt("MENU_ID");
                String name = resultSet.getString("NAME");
                String dishSize = resultSet.getString("DISH_SIZE");
                int quantity = resultSet.getInt("QUANTITY");
                double price = resultSet.getDouble("PRICE");
                menu = new Menu(menuId, name, dishSize, quantity, price);
            }
        } catch (SQLException e)
        {
            throw new DAOException("findMenuByID() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DAOException("findMenuByID() " + e.getMessage());
            }
        }
        return menu;
    }
}