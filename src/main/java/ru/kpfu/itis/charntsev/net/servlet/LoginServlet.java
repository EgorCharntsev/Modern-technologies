package ru.kpfu.itis.charntsev.net.servlet;

import ru.kpfu.itis.charntsev.net.dao.impl.UserDaoImpl;
import ru.kpfu.itis.charntsev.net.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserDaoImpl userDao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/sign_in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (!(login.isEmpty() || password.isEmpty())) {
            try {
                User user = userDao.get(login, password);

                // session
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("username", user.getName());
                httpSession.setMaxInactiveInterval(60 * 60);

                // cookie
                Cookie cookie = new Cookie("username", user.getName());
                cookie.setMaxAge(24 * 60 * 60);
                resp.addCookie(cookie);

                resp.sendRedirect("/evivo_home");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            getServletContext().getRequestDispatcher("/sign_in.jsp").forward(req, resp);
        }
    }
}
