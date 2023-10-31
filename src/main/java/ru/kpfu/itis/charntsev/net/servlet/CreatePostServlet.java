package ru.kpfu.itis.charntsev.net.servlet;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.charntsev.net.dao.impl.PostDaoImpl;
import ru.kpfu.itis.charntsev.net.dao.impl.UserDaoImpl;
import ru.kpfu.itis.charntsev.net.model.Post;
import ru.kpfu.itis.charntsev.net.model.User;
import ru.kpfu.itis.charntsev.net.util.CloudinaryUtil;
import ru.kpfu.itis.charntsev.net.util.DatabaseConnectionUtil;
import ru.kpfu.itis.charntsev.net.util.DecorationPagesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "createPostServlet", urlPatterns = "/create_post")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class CreatePostServlet extends HttpServlet {
    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    public final Connection connection = DatabaseConnectionUtil.getConnection();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DecorationPagesUtil.setTitle(req, "Create post");

       req.getRequestDispatcher("/view/create_post.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        InputStream content = part.getInputStream();

        File f = File.createTempFile(filename, null);
        FileOutputStream outputStream = new FileOutputStream(f);
        byte[] buffer = new byte[content.available()];
        Path p = f.toPath();

        content.read(buffer);

        outputStream.write(buffer);
        outputStream.close();

        Map map = cloudinary.uploader().upload(f, new HashMap<>());
        f.deleteOnExit();

        String post_photo = map.get("url").toString();
        req.setAttribute("photo", post_photo);
        if (post_photo.equals(null)) {
            post_photo = "https://res.cloudinary.com/charntsev/image/upload/v1698685988/ezjs4jv7fbpihqpba4fe.jpg";
        }
        try {
            Post post = new Post(
                    Integer.parseInt(req.getSession().getAttribute("user_id").toString()),
                    req.getParameter("title"),
                    req.getParameter("description"),
                    req.getParameter("text"),
                    req.getParameter("category"),
                    Integer.parseInt(req.getParameter("price")),
                    post_photo);

            PostDaoImpl postDao = new PostDaoImpl();
            postDao.save(post);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/posts");
    }
}
