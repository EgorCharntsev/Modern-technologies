package ru.kpfu.itis.charntsev.net.servlet;

import ru.kpfu.itis.charntsev.net.dao.impl.UserDaoImpl;
import ru.kpfu.itis.charntsev.net.model.User;
import ru.kpfu.itis.charntsev.net.util.DecorationPagesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "editProfileServlet", urlPatterns = "/edit_profile")
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DecorationPagesUtil.setTitle(req, "Edit profile");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.get(Integer.parseInt(req.getSession().getAttribute("user_id").toString()));
        req.setAttribute("user", user);

        req.getRequestDispatcher("/view/edit_profile.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> userInfo = new ArrayList<>();
        userInfo.add(req.getParameter("name"));
        userInfo.add(req.getParameter("lastname"));
        userInfo.add(req.getParameter("login"));
        userInfo.add(req.getParameter("old_password"));
        userInfo.add(req.getParameter("new_password"));
        userInfo.add(req.getParameter("new_password2"));

        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.get(Integer.parseInt(req.getSession().getAttribute("user_id").toString()));

        HttpSession httpSession = req.getSession();

        for(Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("username") && userInfo.get(0) != null && !(user.getName().equals(userInfo.get(0)))) {
                user.setName(userInfo.get(0));
                httpSession.setAttribute("username", user.getName());
                cookie.setValue(user.getName());
            }

            if (cookie.getName().equals("user_lastname") && userInfo.get(1) != null && !(user.getLastname().equals(userInfo.get(1)))) {
                user.setLastname(userInfo.get(1));
                httpSession.setAttribute("user_lastname", user.getLastname());
                cookie.setValue(user.getLastname());
            }

            if (cookie.getName().equals("phone") && userInfo.get(2) != null && !(user.getLogin().equals(userInfo.get(2)))) {
                user.setLogin(userInfo.get(2));
                httpSession.setAttribute("phone", user.getLogin());
                cookie.setValue(user.getLogin());
            }

            if (cookie.getName().equals("password") && userInfo.get(3) != null && userInfo.get(4) != null &&
                    user.getPassword().equals(userInfo.get(3)) && userInfo.get(4).equals(userInfo.get(5))) {
                user.setPassword(userInfo.get(4));
                httpSession.setAttribute("password", user.getPassword());
                cookie.setValue(user.getPassword());
            }
            cookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(cookie);
        }

        userDao.update(user.getId(), user);

        resp.sendRedirect("/profile");
    }
}
