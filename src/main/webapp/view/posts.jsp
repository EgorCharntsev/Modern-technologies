<%@ page import="ru.kpfu.itis.charntsev.net.dto.PostDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pattern/header.jsp"%>

<div class="index">
    <div class="index_content">
        <div class="index_section">
            <% for (PostDto postDto : (List<PostDto>) request.getAttribute("posts")) {
                System.out.println(postDto.getName());%>
            <form>
                <div class="post" >
                    <img class="post_image" src=<%=postDto.getImage()%>>
                    <div class="post_item">
                        <br>
                        <div>
                            <a href="/post_info" class="logo_text_h2">Title: </a><%=postDto.getTitle()%>
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
                            <a class="logo_text_h2">Product Info: </a><%=postDto.getText()%>
                        </div>
                    </div>
                </div>
            </form>

            <%}%>
        </div>
    </div>
</div>

<%@ include file="/pattern/footer.jsp"%>