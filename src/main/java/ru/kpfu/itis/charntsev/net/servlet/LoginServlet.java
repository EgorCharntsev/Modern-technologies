package ru.kpfu.itis.charntsev.net.servlet;

import ru.kpfu.itis.charntsev.net.dao.impl.UserDaoImpl;
import ru.kpfu.itis.charntsev.net.model.User;
import ru.kpfu.itis.charntsev.net.util.DecorationPagesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserDaoImpl userDao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DecorationPagesUtil.setTitle(req, "Login page");

        getServletContext().getRequestDispatcher("/view/sign_in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (!(login.isEmpty() || password.isEmpty())) {
            try {
                User user = userDao.get(login, password);

                if (user != null) {

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
                    resp.sendRedirect("/estore_home");
                } else {
                    resp.sendRedirect("/error_404");
                }

//                // session
//                HttpSession httpSession = req.getSession();
//                httpSession.setAttribute("username", user.getName());
//                httpSession.setMaxInactiveInterval(60 * 60);
//
//                // cookie
//                Cookie cookie = new Cookie("username", user.getName());
//                cookie.setMaxAge(24 * 60 * 60);
//                resp.addCookie(cookie);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            getServletContext().getRequestDispatcher("/login").forward(req, resp);
        }
    }
}
