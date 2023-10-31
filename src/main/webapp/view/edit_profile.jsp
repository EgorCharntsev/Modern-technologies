<%@ page import="ru.kpfu.itis.charntsev.net.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/pattern/header.jsp"%>>

<%User user = (User) request.getAttribute("user");
String userPassword = user.getPassword();%>

<div class="index">
    <h1 class="index_title">Edit Profile</h1>
        <form action = "/edit_profile" method="post">
            <div class="index_content">
                <div class="index_section">
                    <div class="index_item">
                        <img class="circle_medium" src="<%=user.getPhoto()%>" alt="profile_image">
                    </div>
                </div>

                <div class="index_section">
                    <div class="index_field">
                        <a class="logo_text_h2">Enter new name:</a>
                        <input class="field" type = "text" name = "name"/>
                    </div>
                    <div class="index_field">
                        <a class="logo_text_h2">Enter new lastname:</a>
                        <input class="field" type = "text" name = "lastname"/>
                    </div>
                    <div class="index_field">
                        <a class="logo_text_h2">Enter new number :</a>
                        <input class="field" type = "text" name = "login" />
                    </div>
                    <div class="index_field">
                        <a class="logo_text_h2">Enter your old password:</a>
                        <input id="old_password" class="field" type = "password" name = "old_password"/>
                    </div>
                    <div class="index_field">
                        <a class="logo_text_h2">Create new password:</a>
                        <input class="field" type = "password" name = "new_password"/>
                    </div>
                    <div class="index_field">
                        <a class="logo_text_h2">Repeat new password:</a>
                        <input class="field" type = "password" name = "new_password2"/>
                    </div>
                </div>
            </div>
            <input class="btn" type = "submit" value="Edit!"/><br>
        </form>
    </div>
</div>

<%@include file="/pattern/footer.jsp"%>>
