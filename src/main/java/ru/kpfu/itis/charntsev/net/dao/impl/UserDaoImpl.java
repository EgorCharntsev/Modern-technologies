package ru.kpfu.itis.charntsev.net.dao.impl;

import ru.kpfu.itis.charntsev.net.dao.UserDao;
import ru.kpfu.itis.charntsev.net.model.User;
import ru.kpfu.itis.charntsev.net.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao<User> {

    public final Connection connection = DatabaseConnectionUtil.getConnection();
    private final String SQL_SAVE = "INSERT INTO users(name, lastname, login, password) VALUES (?,?,?,?)";
    private final String SQL_GET_BY_ID = "SELECT * FROM users WHERE id = ?";
    private final String SQL_GET_BY_LOGIN_AND_PASS = "SELECT * FROM users WHERE login = ? AND password = ?";
    private final String SQL_GET_ALL = "SELECT * FROM users";

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User get(String login, String password) throws SQLException{
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_LOGIN_AND_PASS);

            preparedStatement.setString(1,login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));

            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from users";
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    users.add(
                            new User(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("lastname"),
                                    resultSet.getString("login"),
                                    resultSet.getString("password")));
                }
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(User user) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();

            ResultSet keygen = preparedStatement.getGeneratedKeys();

            if (keygen.next()) {
                user.setId(keygen.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
