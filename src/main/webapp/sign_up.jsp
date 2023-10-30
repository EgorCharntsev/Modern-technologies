<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>>

<div class="index">
    <h1 class="index_title">Registration page</h1>
    <form action = "registration" method="post">
        <div class="index_content">
            Enter your name:
            <input class="field" type = "text" name = "name"/><br><br>
        </div>
        <div class="index_content">
            Enter your lastname:
            <input class="field" type = "text" name = "lastname"/><br><br>
        </div>
        <div class="index_content">
            Enter your number :
            <input class="field" type = "text" name = "login" /><br>
        </div>
        <div class="index_desc_small">
            <small>(your number will be used as login)</small><br><br>
        </div>
        <div class="index_content">
            Create password:
            <input class="field" type = "password" name = "password"/><br><br>
        </div>
        <div class="index_content">
            Repeat password:
            <input class="field" type = "password" name = "password2"/><br><br>
        </div>
        <div class="index_content">
            <input class="sign_btn" type = "submit" value="Complete registration!"/><br>
        </div>
        <div class="index_content">
            <a href="sign_in.jsp" class="index_desc"><em>Already registered? Sign in.</em></a>
        </div>
    </form>
</div>

<%@ include file="footer.jsp"%>