<%--
  Created by IntelliJ IDEA.
  User: 98baz
  Date: 2021. 05. 03.
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jelszo & Email beallito</title>
    <link rel="stylesheet" href="/resources/css/global.css">
</head>
<body>
<h1>Aliccsa be jelszavat es emailjet</h1>
<form method="post" action="/setemailpassword">
    <label for="azon">id:</label><br>
    <%
        //System.out.println(request.getParameter("id"));
        if(request.getParameter("id") == null){
            response.sendRedirect("/index.jsp");
        }
        out.print("<input type='hidden' id='azon' name='azon' value='"+request.getParameter("id") +"'><br>");
    %>
    <label for="email">Email: </label><br>
    <input type="email" name="email" id="email"><br>
    <label for="password">Jelszó: </label><br>
    <input type="password" autocomplete="on" name="password" id="password"><br>
    <button type="submit">Beállítás</button>
</form>
</body>
</html>
