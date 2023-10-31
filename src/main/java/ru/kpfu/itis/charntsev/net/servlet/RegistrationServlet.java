package ru.kpfu.itis.charntsev.net.servlet;

import ru.kpfu.itis.charntsev.net.dao.impl.UserDaoImpl;
import ru.kpfu.itis.charntsev.net.model.User;
import ru.kpfu.itis.charntsev.net.util.DatabaseConnectionUtil;
import ru.kpfu.itis.charntsev.net.util.DecorationPagesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
        getServletContext().getRequestDispatcher("/view/sign_up.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DecorationPagesUtil.setTitle(req, "Registration page");
        List<String> userInfo = new ArrayList<>();
        userInfo.add(req.getParameter("name"));
        userInfo.add(req.getParameter("lastname"));
        userInfo.add(req.getParameter("login"));
        userInfo.add(req.getParameter("password"));
        userInfo.add(req.getParameter("password2"));

        if (isValid(userInfo)) {
            User user = new User(userInfo.get(0), userInfo.get(1), userInfo.get(2), userInfo.get(3),
                    "https://res.cloudinary.com/charntsev/image/upload/v1698683557/j3ngebxn0xjtychgle5b.jpg");

            try {
                userDao.save(user);

                user = userDao.get(user.getLogin(), user.getPassword());

                // session
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("user_id", String.valueOf(user.getId()));
                httpSession.setAttribute("username", user.getName());
                httpSession.setAttribute("user_lastname", user.getLastname());
                httpSession.setAttribute("phone", user.getLogin());
                httpSession.setAttribute("password", user.getPassword());
                httpSession.setAttribute("photo", user.getPhoto());

                // cookie
                List<Cookie> cookies = new ArrayList<>();
                cookies.add(new Cookie("user_id", String.valueOf(user.getId())));
                cookies.add(new Cookie("username", user.getName()));
                cookies.add(new Cookie("user_lastname", user.getLastname()));
                cookies.add(new Cookie("phone", user.getLogin()));
                cookies.add(new Cookie("password", user.getPassword()));
                cookies.add(new Cookie("photo", user.getPhoto()));
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(24 * 60 * 60);
                    resp.addCookie(cookie);
                }

            } catch (SQLException e) {
                getServletContext().getRequestDispatcher("/view/sign_up.jsp").forward(req,resp);
            }

            resp.sendRedirect("/estore_home");
        } else {
            getServletContext().getRequestDispatcher("/view/sign_up.jsp").forward(req,resp);
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
