package ru.kpfu.itis.charntsev.net.util;

import javax.servlet.http.HttpServletRequest;

public class DecorationPagesUtil {
    public static void setTitle(HttpServletRequest request, String title) {
        request.setAttribute("title_page", title);
    }
}
