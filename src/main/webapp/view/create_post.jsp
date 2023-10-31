<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/pattern/header.jsp"%>

<div class="index">
    <h1 class="index_title">Create new post</h1>
    <form action = "/create_post" method="post" enctype="multipart/form-data">
        <div class="index_content">
            <div class="index_section">
                <div class="index_item">
                    <img src="/image/new_post_image.jpg" alt="post_image">
                </div>
                <div class="index_item">
                    <p class="logo_text_h2">Upload file: </p>
                </div>
                <div class="index_item">
                    <br>
                    <div>
                        <label for="uploads">Choose file to upload:</label>
                        <input id="uploads" type="file" name="file">
                    </div>
                </div>
            </div>

            <div class="index_section">
                <div class="index_field">
                    <a class="logo_text_h2">Enter title:</a>
                    <input class="field" type = "text" name = "title"/>
                </div>
                <div class="index_field">
                    <a class="logo_text_h2">Enter description:</a>
                    <input class="field" type = "text" name = "description"/>
                </div>
                <div class="index_field">
                    <a class="logo_text_h2">Enter post's text:</a>
                    <input class="field" type = "text" name = "text" />
                </div>
                <div class="index_field">
                    <a class="logo_text_h2">Select category:</a>
                    <form class="search_form">
                        <select class="wrapper-dropdown" id="categories" name="category" >
                            <option disabled selected>-</option>
                            <option value="home">Home</option>
                            <option value="work">Work</option>
                            <option value="study">Study</option>
                            <option value="special">Special</option>
                            <option value="decoration">Decoration</option>
                            <option value="others">Others</option>
                        </select>
                    </form>
                </div>
                <div class="index_field">
                    <a class="logo_text_h2">Enter price:</a>
                    <input class="field" type = "number" name = "price"/>
                </div>
            </div>
            <input class="btn" type = "submit" value="Create post!"/>
        </div>
    </form>
</div>

<%@include file="/pattern/footer.jsp"%>
