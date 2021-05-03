<%--
  Created by IntelliJ IDEA.
  User: 98baz
  Date: 2021. 05. 03.
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bejelentkezés</title>
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/login.css">
</head>
<body>
<div id="center_me_if_you_dare">
    <h1>Bejelentkezés</h1>
    <form action="/login_backend" method="post">

            <label for="username">Felhasználó név</label><br>
            <input type="text" id="username" name="username"><br>


            <label for="password">Jelszó</label><br>
            <input type="password" autocomplete="on" id="password" name="password"><br><br>

        <button id="login" type="submit"<%=session.getAttribute("username")==null?(""):("disabled")%>>Bejelentkezés</button>
    </form>
    <%=session.getAttribute("id")==null?(""):("<a href='logout'>Kilépés</a>'")%><br>
    <a href="index.jsp">Főoldal</a><br>
</div>
</body>
</html>
