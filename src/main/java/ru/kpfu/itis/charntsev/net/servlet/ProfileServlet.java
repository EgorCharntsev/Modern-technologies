package ru.kpfu.itis.charntsev.net.servlet;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import ru.kpfu.itis.charntsev.net.dao.impl.UserDaoImpl;
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

@WebServlet(name="profileServlet", urlPatterns = "/profile")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class ProfileServlet extends HttpServlet {
    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    public final Connection connection = DatabaseConnectionUtil.getConnection();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DecorationPagesUtil.setTitle(req, "My profile");
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.get(Integer.parseInt(req.getSession().getAttribute("user_id").toString()));
            req.setAttribute("user", user);
            req.getRequestDispatcher("/view/profile.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
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

        String photo = map.get("url").toString();
        req.setAttribute("photo", photo);

        req.getSession().setAttribute("photo", photo);

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("photo")) {
                cookie.setValue(photo);
            }
        }

        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.get(Integer.parseInt(req.getSession().getAttribute("user_id").toString()));

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET photo = ? WHERE id = ?");

            preparedStatement.setString(1, photo);
            preparedStatement.setInt(2, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        user = userDao.get(user.getId());
        req.setAttribute("user", user);
        resp.sendRedirect("/profile");
    }
}
