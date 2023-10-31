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

@WebServlet(name = "postInfoServlet", urlPatterns = "/post_info")
public class PostInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DecorationPagesUtil.setTitle(req, "Post info");
        PostDaoImpl postDao = new PostDaoImpl();
        try {
            PostDto postDto = postDao.getDto(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("post", postDto);
            req.getRequestDispatcher("/view/post_info.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
