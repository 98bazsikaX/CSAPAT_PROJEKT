<%@ page import="model.Match" %>
<%@ page import="dao.MatchDAOImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="/resources/css/global.css">
</head>
<body>
<div id="center_me_if_you_dare">
    <h1>Hello <%= session.getAttribute("user")==null?("Idegen"):(session.getAttribute("username")) %></h1>
<a href="login.jsp">Belépés</a>
    <%= session.getAttribute("username")==null?(""):("<a href='SetMatch.jsp'>Meccs hozzáadása</a>") %>
</div>
<div id="meccsek">
    <h2>Meccsek </h2>
    <%
        for(Match m : MatchDAOImpl.getInstance().findAll()){
            String main = m.getWinner().getName() + " ("+m.getWinnerScore()+") VS "+m.getLooser().getName() + " ("+m.getLooserScore()+")";
            out.print("<a href='"+m.getId()+"'>"+main+"</a>");
        }
    %>
</div>
</body>
</html>