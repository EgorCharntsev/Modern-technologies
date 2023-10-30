package ru.kpfu.itis.charntsev.net.dao;

import java.sql.SQLException;
import java.util.List;

public interface UserDao<T> {

    T get (int id);

    T get (String login, String password) throws SQLException;

    List<T> getAll();

    void save(T user) throws SQLException;

}
