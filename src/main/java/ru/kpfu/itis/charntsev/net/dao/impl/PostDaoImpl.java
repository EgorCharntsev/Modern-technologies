package ru.kpfu.itis.charntsev.net.dao.impl;

import ru.kpfu.itis.charntsev.net.dao.PostDao;
import ru.kpfu.itis.charntsev.net.dao.UserDao;
import ru.kpfu.itis.charntsev.net.dto.PostDto;
import ru.kpfu.itis.charntsev.net.model.Post;
import ru.kpfu.itis.charntsev.net.model.User;
import ru.kpfu.itis.charntsev.net.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao<Post> {
    public final Connection connection = DatabaseConnectionUtil.getConnection();
    private final String SQL_SAVE = "INSERT INTO posts(author_id, title, description, text, category, price, photo) VALUES (?,?,?,?,?,?,?)";
    private final String SQL_GET_BY_ID = "SELECT * FROM posts WHERE id = ?";
    private final String SQL_GET_BY_AUTHORID_AND_NAME = "SELECT * FROM posts WHERE author_id = ? AND title = ?";
    private final String SQL_GET_ALL = "SELECT * FROM posts";

    private final String SQL_GET_DTO_BY_ID = "SELECT p.id, u.name, u.login, p.title, p.description, p.text," +
            " p.category, p.price, p.photo, p.author_id FROM posts p INNER JOIN users u ON u.id = p.author_id WHERE p.id = ?";
    private final String SQL_GET_ALL_DTO_BY_ID = "SELECT p.id, u.name, u.login, p.title, p.description, p.text," +
            " p.category, p.price, p.photo, p.author_id FROM posts p INNER JOIN users u ON u.id = p.author_id WHERE u.id = ?";
    private final String SQL_GET_ALL_DTO = "SELECT p.id, u.name, u.login, p.title, p.description, p.text," +
            " p.category, p.price, p.photo, p.author_id FROM posts p INNER JOIN users u ON u.id = p.author_id";

    @Override
    public Post get(int id) {
        return null;
    }

    @Override
    public Post get(int authorId, String title) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_AUTHORID_AND_NAME);

            preparedStatement.setInt(1,authorId);
            preparedStatement.setString(2, title);

            ResultSet resultSet = preparedStatement.executeQuery();

            Post post = null;
            if (resultSet.next()) {
                post = new Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("author_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("text"),
                        resultSet.getString("category"),
                        resultSet.getInt("price"),
                        resultSet.getString("photo"));

            }
            return post;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Post> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            List<Post> posts = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    posts.add(
                            new Post(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("author_id"),
                                    resultSet.getString("title"),
                                    resultSet.getString("description"),
                                    resultSet.getString("text"),
                                    resultSet.getString("category"),
                                    resultSet.getInt("price"),
                                    resultSet.getString("photo")));
                }
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<PostDto> getAllDto(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_DTO_BY_ID);

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<PostDto> postDto = new ArrayList<>();
        while (resultSet.next()) {
            postDto.add(
                    new PostDto(
                            resultSet.getInt("id"),
                            resultSet.getInt("author_id"),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("text"),
                            resultSet.getString("category"),
                            resultSet.getInt("price"),
                            resultSet.getString("photo")
                    )
            );
        }
        return postDto;
    }
    public List<PostDto> getAllDto() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_DTO);
        List<PostDto> postDto = new ArrayList<>();
        while (resultSet.next()) {
            postDto.add(
                    new PostDto(
                            resultSet.getInt("id"),
                            resultSet.getInt("author_id"),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("text"),
                            resultSet.getString("category"),
                            resultSet.getInt("price"),
                            resultSet.getString("photo")
                    )
            );
        }
        return postDto;
    }

    public PostDto getDto(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_DTO_BY_ID);

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        PostDto postDto = null;
        if (resultSet.next()) {
            postDto = new PostDto(
                            resultSet.getInt("id"),
                            resultSet.getInt("author_id"),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("text"),
                            resultSet.getString("category"),
                            resultSet.getInt("price"),
                            resultSet.getString("photo")
            );
        }
        return postDto;
    }

    @Override
    public void save(Post post) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setInt(1, post.getAuthorId());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getDescription());
            preparedStatement.setString(4, post.getText());
            preparedStatement.setString(5, post.getCategory());
            preparedStatement.setInt(6, post.getPrice());
            preparedStatement.setString(7,post.getPhoto());

            preparedStatement.executeUpdate();

            ResultSet keygen = preparedStatement.getGeneratedKeys();

            if (keygen.next()) {
                post.setId(keygen.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
