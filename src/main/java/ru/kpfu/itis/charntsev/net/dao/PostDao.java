package ru.kpfu.itis.charntsev.net.dao;

import java.sql.SQLException;
import java.util.List;

public interface PostDao<T> {
    T get (int id);

    T get (int authorId, String name) throws SQLException;

    List<T> getAll();

    void save(T user) throws SQLException;
}
