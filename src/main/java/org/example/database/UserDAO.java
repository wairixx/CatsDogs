package org.example.database;

import org.example.entities.Configs;
import org.example.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private final Database databaseConnection;

    public UserDAO(Database databaseConnection) {
        this.databaseConnection = databaseConnection;


    }



    public User login(String login, String password) {
        ResultSet result = databaseConnection.getResultSet(Configs.SQL_LOGIN, login, password);
        try {
            if (result.next()) {
                return new User(result.getInt("user_id"), result.getString("login"), result.getString("password"));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public int id(String login, String password) {
        int id = 0;
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(Configs.ID);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id += resultSet.getInt("user_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public int money(Integer id) {
        int money = 0;
        try {
            PreparedStatement preparedStatement = databaseConnection.dbConnection.prepareStatement(Configs.MONEY);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                money = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return money;
    }

    public void changeMoney(Integer userMoney, Integer user_id) {
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(Configs.SQL_CHANGE_USER_MONEY);
            preparedStatement.setInt(1, userMoney);
            preparedStatement.setInt(2, user_id);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getUserInfo(Integer user_id) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(Configs.SQL_SHOW_ADDITIONAL_INFO);
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            PreparedStatement preparedStatement1 = databaseConnection.getDbConnection().prepareStatement(Configs.SQL_SHOW_INFO);
            preparedStatement1.setInt(1, user_id);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            if (resultSet.next()) {
                while (true) {
                    users.add(new User(resultSet.getInt("user_id"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getInt("money"), resultSet.getString("city"), resultSet.getString("country")));
                    break;
                }
            } else if (resultSet1.next()) {
                while (true) {
                    users.add(new User(resultSet1.getInt("user_id"), resultSet1.getString("login"), resultSet1.getString("password"), resultSet1.getInt("money")));
                    break;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean checkAccount(String login) {
        boolean isCreated;
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(Configs.SQL_CHECK_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("This account is already exist, please logIn");
                isCreated = true;
            } else {
                isCreated = false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return isCreated;
    }

    public void signUpUser(Integer id, String city, String country) {

        try {
            PreparedStatement prSt = databaseConnection.getDbConnection().prepareStatement(Configs.SQL_SIGN_UP_WITH_ADDITIONAL_INFO);
            prSt.setInt(1, id);
            prSt.setString(2, city);
            prSt.setString(3, country);


            prSt.executeUpdate();
            System.out.println("successful connection");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void signUpUserWithoutAdditional(String login, String password, Integer money) {

        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(Configs.SQL_SIGN_UP);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, money);


            preparedStatement.executeUpdate();
            System.out.println("successful registration");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
