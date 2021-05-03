<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.MatchDAOImpl" %>
<%@ page import="model.Match" %>
<%@ page import="model.PlayerStatistics" %>
<%@ page import="dao.StatDAOImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Team" %>
<%@ page import="dao.TeamDAOImpl" %>
<%@ page import="dao.PlayerDAOImpl" %>
<%@ page import="model.Player" %><%--
  Created by IntelliJ IDEA.
  User: 98baz
  Date: 2021. 05. 03.
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meccs</title>
    <%
        int matchId = Integer.parseInt(request.getParameter("id"));

        Match match = MatchDAOImpl.getInstance().findByID(matchId);

        if(match.getId()==0){
            response.sendRedirect("/index.jsp");
            return;
        }

        List<PlayerStatistics> statistics = StatDAOImpl.getInstance().findByMatch(match);

        Team winner = TeamDAOImpl.getInstance().getById(match.getWinner());

        Team looser = TeamDAOImpl.getInstance().getById(match.getLooser());

    %>
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/match.css">
</head>
<body>
<nav>
    <a href="/index.jsp">Főoldal</a>
</nav>
<h1>
<%
    out.print(winner.getName() + " (" + match.getWinnerScore() + ") VS " + looser.getName() + "(" + match.getLooserScore()+")");
%>

</h1>
<table>
    <tr>
        <th>Név</th>
        <th>Csapat</th>
        <th>Ölések</th>
        <th>Halálok</th>
        <th>Aszisztálások</th>
        <th>MVP körök</th>
        <th>Átlagos sebzés</th>
        <th>Fejlövés százaléka</th>
    </tr>
    <%
        for(PlayerStatistics s : statistics){
            out.print("<tr><td>" + s.getPlayer().getName() +"</td>");
            Team t = TeamDAOImpl.getInstance().getByPlayer(s.getPlayer());
            out.print("<td>" + t.getName() +"</td>");
            out.print("<td>"+s.getKills()+"</td>");
            out.print("<td>"+s.getDeaths()+"</td>");
            out.print("<td>"+s.getAssists()+"</td>");
            out.print("<td>"+s.getMvp() + "</td>");
            out.print("<td>"+s.getAdr() + "</td>");
            out.print("<td>"+s.getHeadshotPercentage()+"</td></tr>");
        }
    %>

</table>

</body>
</html>
