<%@ page import="ru.kpfu.itis.charntsev.net.dto.PostDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/pattern/header.jsp"%>>

<%PostDto postDto = (PostDto) request.getAttribute("post");%>
<div class="index">
    <div class="index_content">
        <div class="index_section">
            <form>
                <div class="post_big">
                    <img class="post_image_big" src=<%=postDto.getImage()%>>
                    <div class="post_item">
                        <br>
                        <div>
                            <a class="logo_text_h2">Title: </a><%=postDto.getTitle()%>
                        </div>
                        <br>
                        <div>
                            <a  class="logo_text_h2">Category: </a><%=postDto.getCategory()%>
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
                        <br>
                        <div>
                            <a  class="logo_text_h2">Seller: </a><%=postDto.getName()%>
                        </div>
                        <br>
                        <div>
                            <a class="logo_text_h2">Phone number: </a><%=postDto.getLogin()%>
                        </div>

                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/pattern/footer.jsp"%>>
