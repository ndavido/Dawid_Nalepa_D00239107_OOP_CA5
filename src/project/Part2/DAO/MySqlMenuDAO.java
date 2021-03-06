package project.Part2.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import project.Part2.Exceptions.DAOException;
import project.Part2.DTO.Menu2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlMenuDAO extends MySqlDAO implements MenuDAOInterface{
    @Override
    public List<Menu2> findAllMenu() throws DAOException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Menu2> usersList = new ArrayList<>();

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

                Menu2 m = new Menu2(menuID, name, dishSize, quantity, price);
                usersList.add(m);
            }
        } catch (SQLException e)
        {
            throw new DAOException("findAllMenuResultSet() " + e.getMessage());
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
                throw new DAOException("findAllMenu() " + e.getMessage());
            }
        }
        return usersList;     // may be empty
    }

    @Override
    public Menu2 findMenuByID(int menu_id) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Menu2 menu = null;
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
                menu = new Menu2(menuId, name, dishSize, quantity, price);
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

    @Override
    public void addMenuDish( String name, String dishSize, int quantity, double price) throws DAOException{

        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            connection = this.getConnection();

            String query = "INSERT INTO oop_ca4.menu VALUES (null, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, dishSize);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);

            ps.executeUpdate();  // will INSERT a new row

        } catch (SQLException ex) {
            System.out.println("Failed to connect to database - check MySQL is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteMenuDishByID(int menu_id) throws DAOException{

        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            connection = this.getConnection();

            String query = "DELETE FROM oop_ca4.menu WHERE MENU_ID = ?";
            ps = connection.prepareStatement(query);

            ps.setInt(1, menu_id);

            ps.executeUpdate();  // will DELETE a new row

        } catch (SQLException ex) {
            System.out.println("Failed to connect to database - check MySQL is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }

    @Override
    public String findAllMenuJson() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Menu2> usersList = new ArrayList<>();
        String menusJsonString = "";
        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM MENU";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int menuID = resultSet.getInt("MENU_ID");
                String name = resultSet.getString("NAME");
                String dishSize = resultSet.getString("DISH_SIZE");
                int quantity = resultSet.getInt("QUANTITY");
                double price = resultSet.getDouble("PRICE");

                Menu2 m = new Menu2(menuID, name, dishSize, quantity, price);
                usersList.add(m);
            }
            Gson gsonParser = new GsonBuilder().setPrettyPrinting().create();

            menusJsonString = gsonParser.toJson(usersList);

        } catch (SQLException e) {
            throw new DAOException("findAllMenuResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DAOException("findAllMenu() " + e.getMessage());
            }
        }
        return menusJsonString;     // may be empty
    }

    @Override
    public String findMenuByIDJson(int menu_id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Menu2 menu = null;
        String menusJsonString = "";
        try {
            connection = this.getConnection();

            String query = "SELECT * FROM MENU WHERE MENU_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, menu_id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int menuId = resultSet.getInt("MENU_ID");
                String name = resultSet.getString("NAME");
                String dishSize = resultSet.getString("DISH_SIZE");
                int quantity = resultSet.getInt("QUANTITY");
                double price = resultSet.getDouble("PRICE");
                menu = new Menu2(menuId, name, dishSize, quantity, price);
            }
            Gson gsonParser = new GsonBuilder().setPrettyPrinting().create();
            menusJsonString = gsonParser.toJson(menu);
        } catch (SQLException e) {
            throw new DAOException("findMenuByID() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DAOException("findMenuByID() " + e.getMessage());
            }
        }

        return menusJsonString;
    }
}