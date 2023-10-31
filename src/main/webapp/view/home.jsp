<%@ page import="ru.kpfu.itis.charntsev.net.dto.PostDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/pattern/header.jsp"%>>

<%--<div class="index">--%>
<%--    <div class="home_container">--%>
<%--        <div class="home_section">--%>
<%--            <form class="search_form">--%>
<%--                <label for="categories">Select category:</label>--%>
<%--                <select class="wrapper-dropdown" id="categories" name="categories" onchange="handleFruitChange()">--%>
<%--                    <option value="home">Home</option>--%>
<%--                    <option value="work">Work</option>--%>
<%--                    <option value="study">Study</option>--%>
<%--                    <option value="special">Special</option>--%>
<%--                    <option value="decoration">Decoration</option>--%>
<%--                    <option value="others">Others</option>--%>
<%--                </select>--%>
<%--            </form>--%>

<%--            <script>--%>
<%--                function handleFruitChange() {--%>
<%--                    const selectElement = document.getElementById('categories');--%>
<%--                    const selectedCat = selectElement.value;--%>
<%--                    alert('You peak category: ' + selectedCat);--%>
<%--                }--%>
<%--            </script>--%>

<%--            <form class="search_form">--%>
<%--                <input class="search_input" type="text" placeholder="Поиск по сайту">--%>
<%--                <button class="search_button" type="submit" value="Найти"></button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--        <div class="home_section">--%>
<%--            <div class="post">--%>
<%--                <img class="post_image" src="/image/error_404.jpg" alt="Описание изображения">--%>
<%--                <p>Описание поста</p>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="home_section">--%>
<%--            <div class="post">--%>
<%--                <img class="post_image" src="/image/error_404.jpg" alt="Описание изображения">--%>
<%--                <p>Описание поста</p>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="home_section">--%>
<%--            <div class="post">--%>
<%--                <img class="post_image" src="/image/error_404.jpg" alt="Описание изображения">--%>
<%--                <p>Описание поста</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<div class="index">
    <div class="index_content">
        <div class="index_section">
            <% for (PostDto postDto : (List<PostDto>) request.getAttribute("posts")) {
                request.setAttribute("post", postDto);%>
            <form >
                <div class="post" >
                    <img class="post_image" src=<%=postDto.getImage()%>>
                    <div class="post_item">
                        <br>
                        <div>
                            <a href="/post_info?id=<%=postDto.getId()%>" class="logo_text_h2" onclick="">Title: </a><%=postDto.getTitle()%>
                        </div>
                        <br>
                        <div>
                            <a class="logo_text_h2">Description: </a><%=postDto.getDescription()%>
                        </div>
                        <br>
                        <div>
                            <a class="logo_text_h2">Product price: </a><%=postDto.getPrice()%>  rub
                        </div>
                        <br>
                        <div>
                            <a class="logo_text_h2">Product Info:</a><%=postDto.getText()%>
                        </div>
                    </div>
                </div>
            </form>
            <%}%>
        </div>
    </div>
</div>

<%@include file="/pattern/footer.jsp"%>
