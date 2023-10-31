<%@ page import="ru.kpfu.itis.charntsev.net.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../pattern/header.jsp"%>

<%User user = (User) request.getAttribute("user");%>
    <div class="index">
        <div class="index_content">
            <div class="index_section">
                <div class="index_item">
                    <img class="circle_big" src="<%=user.getPhoto()%>" alt="profile_image">
                </div>
                <div class="index_item">
                    <p class="logo_text_h2">Upload file: </p>
                </div>
                <div class="index_item">
                    <form action="/profile" method="post" enctype="multipart/form-data">
                        <br>
                        <div>
                            <label for="uploads">Choose file to upload:</label>
                            <input id="uploads" type="file" name="file" <%--style="display: none"--%>>
                        </div>
                        <br>
                        <div style="align-content: center">
                            <input class="btn" type="submit" value="Upload">
                        </div>
                    </form>
                </div>
            </div>
            <div class="index_section">
                <div class="index_field">
                    <a class="logo_text_h1">Name:</a>
                    <span class="field_small">
                        <b><%=user.getName()%></b>
                    </span>
                </div>
                <div class="index_field">
                    <a class="logo_text_h1">Lastname:</a>
                    <span class="field_small">
                        <b><%=user.getLastname()%></b>
                    </span>
                </div>
                <div class="index_field">
                    <a class="logo_text_h1">Phone number:</a>
                    <span class="field_small">
                        <b><%=user.getLogin()%></b>
                    </span>
                </div>
                <br><br><br><br><br><br>
                <div class="index_field">
                    <a href="/edit_profile" class="btn">Edit profile</a>
<%--                    <form method="post" action="/profile" name="go_to_edit">--%>
<%--                        <input class="btn" type="submit" >--%>
<%--                    </form>--%>
                </div>
            </div>
        </div>
    </div>

<%@ include file="../pattern/footer.jsp"%>