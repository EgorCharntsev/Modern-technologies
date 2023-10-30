<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>>

<div class="index">
    <h1 class = "index_title">Login page</h1>
    <form action = "login" method="post" >
        <div class="index_content">
            Login:<input class = "field" type = "text" name = "login" />
        </div>
        <div class="index_content">
            Password:
            <input class = "field" type = "password" name = "password" />
        </div>
        <div class="index_content">
            <input class = "sign_btn" type = "submit" value="Sign in"/>
        </div>
        <div class = index_content>
            <a href="sign_up.jsp">
                <em class = "index_desc">First time on our website? Sign up.</em>
            </a>
        </div>
    </form>
</div>

<%@include file="footer.jsp"%>
