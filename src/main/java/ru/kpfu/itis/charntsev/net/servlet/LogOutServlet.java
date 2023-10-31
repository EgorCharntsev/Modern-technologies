package ru.kpfu.itis.charntsev.net.servlet;

import ru.kpfu.itis.charntsev.net.util.DecorationPagesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name="logOutServlet", urlPatterns = "/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DecorationPagesUtil.setTitle(req, "Logout page");
        clear(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clear(req,resp);
    }

    private void clear(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(1);
                response.addCookie(cookie);
            }
        }

        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect("/estore_home");
    }
}
