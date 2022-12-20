package org.example.database;

import org.example.Initializer;
import org.example.entities.User;
import org.example.services.UserService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private DatabaseConnection databaseConnection;

    public UserDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;


    }

    private static final String SQL_LOGIN = "SELECT * FROM new_test.users WHERE login = ? AND password = ?;";
    private static final String ID = "SELECT id FROM new_test.users WHERE login = ? AND password = ?;";
    private static final String SQL_SHOW_INFO = "SELECT * FROM new_test.users WHERE id = ?;";
    private static final String MONEY = "SELECT money FROM new_test.users WHERE id = ?;";
    private static final String SQL_CHANGE_USER_MONEY = "UPDATE new_test.users SET money = ? WHERE id = ?";
    private static final String SQL_SIGN_UP = "INSERT INTO new_test.users (user_id, city, country) VALUES (?,?,?)";
    private static final String SQL_SIGN_UP_WITHOUT_ADDITIONAL_INFO = "INSERT INTO new_test.users (login, password, money) VALUES (?,?,?)";
    private static final String SQL_SHOW_ADDITIONAL_INFO = "SELECT  new_test.users.id, new_test.users.login, new_test.users.password, new_test.users.money, new_test.additional_info.city, new_test.additional_info.country" + "\n" +
            "FROM new_test.users" + "\n" +
            "INNER JOIN new_test.additional_info ON new_test.users.id=new_test.additional_info.user_id" + "\n" +
            "where new_test.users.id = ?;";

    public User login(String login, String password) {
        ResultSet result = databaseConnection.getResultSet(SQL_LOGIN, login, password);
        try {
            if (result.next()) {
                return new User(result.getInt("id"), result.getString("login"), result.getString("password"));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public int id(String login, String password) {
        int id = 0;
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(ID);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id += resultSet.getInt("id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void changeMoney(Integer userMoney, Integer id) {
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(SQL_CHANGE_USER_MONEY);
            preparedStatement.setInt(1, userMoney);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getUserInfo(Integer id) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(SQL_SHOW_ADDITIONAL_INFO);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            PreparedStatement preparedStatement1 = databaseConnection.getDbConnection().prepareStatement(SQL_SHOW_INFO);
            preparedStatement1.setInt(1, id);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            if (resultSet.next()) {
                while (true) {
                    users.add(new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getInt("money"), resultSet.getString("city"), resultSet.getString("country")));
                    break;
                }
            } else if (resultSet1.next()) {
                while (true) {
                    users.add(new User(resultSet1.getInt("id"), resultSet1.getString("login"), resultSet1.getString("password"), resultSet1.getInt("money")));
                    break;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public int money(Integer id) {
        int money = 0;
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(MONEY);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                money = resultSet.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return money;
    }

    public void signUpUser(Integer id, String city, String country) {
        try {
            PreparedStatement prSt = databaseConnection.getDbConnection().prepareStatement(SQL_SIGN_UP);
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
            PreparedStatement prSt = databaseConnection.getDbConnection().prepareStatement(SQL_SIGN_UP_WITHOUT_ADDITIONAL_INFO);
            prSt.setString(1, login);
            prSt.setString(2, password);
            prSt.setInt(3, money);


            prSt.executeUpdate();
            System.out.println("successful registration");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
