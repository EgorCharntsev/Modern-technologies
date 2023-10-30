package ru.kpfu.itis.charntsev.net.servlet;

import ru.kpfu.itis.charntsev.net.dao.impl.UserDaoImpl;
import ru.kpfu.itis.charntsev.net.model.User;
import ru.kpfu.itis.charntsev.net.util.DatabaseConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "registrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserDaoImpl userDao = new UserDaoImpl();

    public final Connection connection = DatabaseConnectionUtil.getConnection();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/sign_up.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> userInfo = new ArrayList<>();
        userInfo.add(req.getParameter("name"));
        userInfo.add(req.getParameter("lastname"));
        userInfo.add(req.getParameter("login"));
        userInfo.add(req.getParameter("password"));
        userInfo.add(req.getParameter("password2"));

        if (isValid(userInfo)) {
            User user = new User(userInfo.get(0), userInfo.get(1), userInfo.get(2), userInfo.get(3));

            try {
                userDao.save(user);
            } catch (SQLException e) {
                getServletContext().getRequestDispatcher("/sign_up.jsp").forward(req,resp);
            }
            resp.sendRedirect("/evivo_home");
        } else {
            getServletContext().getRequestDispatcher("/sign_up.jsp").forward(req,resp);
        }
    }

    private boolean isValid(List<String> userInfo) {
        boolean flag = true;
        for (String info: userInfo) {
            flag = !info.isEmpty() && flag;
        }
        return userInfo.get(3).equals(userInfo.get(4)) && flag;
    }
}
