package ru.kpfu.itis.charntsev.net.servlet;

import ru.kpfu.itis.charntsev.net.dao.impl.PostDaoImpl;
import ru.kpfu.itis.charntsev.net.dto.PostDto;
import ru.kpfu.itis.charntsev.net.util.DecorationPagesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="homeServlet", urlPatterns="/estore_home")
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DecorationPagesUtil.setTitle(req, "Home page");
        PostDaoImpl postDao = new PostDaoImpl();
        try {
            List<PostDto> posts = postDao.getAllDto();
            req.setAttribute("posts", posts);
            getServletContext().getRequestDispatcher("/view/home.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
