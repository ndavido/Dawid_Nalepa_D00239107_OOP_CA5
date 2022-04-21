package project.Part3.DAO;

import com.google.gson.Gson;
import project.Part3.DTO.Menu3;
import project.Part3.Exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlMenuDAO extends MySqlDAO implements MenuDAOInterface {

    @Override
    public String findAllMenuJson() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Menu3> orderList = new ArrayList<>();
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

                Menu3 m = new Menu3(menuID, name, dishSize, quantity, price);
                orderList.add(m);
            }
            Gson gsonParser = new Gson();

            menusJsonString = gsonParser.toJson(orderList);

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
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Menu3> orderList = new ArrayList<>();
        String menusJsonString = "";
        try {
            connection = this.getConnection();

            String query = "SELECT * FROM MENU WHERE MENU_ID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,menu_id);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int menuID = resultSet.getInt("MENU_ID");
                String name = resultSet.getString("NAME");
                String dishSize = resultSet.getString("DISH_SIZE");
                int quantity = resultSet.getInt("QUANTITY");
                double price = resultSet.getDouble("PRICE");

                Menu3 m = new Menu3(menuID, name, dishSize, quantity, price);
                orderList.add(m);
            }
            Gson gsonParser = new Gson();

            menusJsonString = gsonParser.toJson(orderList);

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
        return menusJsonString;
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
    public String findQuantityGreaterThanJson() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Menu3> usersList = new ArrayList<>();
        String menusJsonString = "";
        try {
            connection = this.getConnection();

            String query = "SELECT * FROM MENU WHERE QUANTITY = (SELECT MAX(QUANTITY) FROM MENU);";
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int menuID = resultSet.getInt("MENU_ID");
                String name = resultSet.getString("NAME");
                String dishSize = resultSet.getString("DISH_SIZE");
                int quantity = resultSet.getInt("QUANTITY");
                double price = resultSet.getDouble("PRICE");

                Menu3 m = new Menu3(menuID, name, dishSize, quantity, price);
                usersList.add(m);
            }
            Gson gsonParser = new Gson();

            menusJsonString = gsonParser.toJson(usersList);

        } catch (SQLException e) {
            throw new DAOException("findQuantityGreaterThanJson() " + e.getMessage());
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
                throw new DAOException("findQuantityGreaterThanJson() " + e.getMessage());
            }
        }
        return menusJsonString;     // may be empty
    }
}
