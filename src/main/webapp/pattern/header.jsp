<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>{$title_page}</title>
  <link href="../css/style.css" rel="stylesheet">
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<%String value = "";
List<String> urls = new ArrayList<>();
urls.add("");
urls.add("");
urls.add("");
urls.add("");
urls.add("");
String username = (String) request.getSession().getAttribute("username");
if (username != null) {
  urls.set(0,"/logout");
  urls.set(1,"/estore_home");
  urls.set(2,"/profile");
  urls.set(3,"/posts");
  urls.set(4,"/create_post");
  value = "Log Out";
} else {
  urls.set(0,"/login");
  value = "Log In";
}%>
<header>
  <div class="header">
    <div class="header_content">
      <div class="header_logo">
        <a href="/estore_home">EStore</a>
      </div>
      <a href=<%=urls.get(2)%>><h1>My account</h1></a>
      <a href=<%=urls.get(3)%>><h1>My posts</h1></a>
      <a href=<%=urls.get(4)%>><h1>Create post</h1></a>
    </div>
      <div class="header_content">
        <a href=<%=urls.get(0)%> ><%=value%></a>
      </div>
  </div>
</header>


