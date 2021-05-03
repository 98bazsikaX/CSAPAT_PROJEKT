<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Team" %>
<%@ page import="dao.TeamDAOImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Tournament" %>
<%@ page import="dao.TournamentDAOImpl" %>
<%@ page import="model.PlayableMap" %>
<%@ page import="dao.MapDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: 98baz
  Date: 2021. 05. 03.
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/global.css">
    <title>Meccs hozzáadása</title>
    <%
        if(session.getAttribute("user")==null){
            response.sendRedirect("/login.jsp");
            return;
        }
    %>


</head>
<body>

<h1>Csapatok kiválasztása</h1>

<form method="post" action="/setmatchdetails.jsp">
    <label for="t_date">Dátum: </label><br>
    <input type="date" name="t_date" id="t_date" required><br>
    <label for="tournament">Verseny: </label><br>
    <select name="tournament" id="tournament" required>

        <%
            List<Tournament> tournaments = TournamentDAOImpl.getInstance().findAll();
            for(Tournament t : tournaments){
                out.print("<option value='"+t.getId() + "'>" + t.getName() + "</option>");
            }

        %>
    </select><br>

    <label for="m_map">Térkép: </label>
    <select name="m_map" id="m_map" required>
        <%
            List<PlayableMap> maps = MapDAOImpl.getInstance().findAll();
            for(PlayableMap m : maps){
                out.print("<option value='"+m.getDbId() + "'>" + m.getName() + "</option>");
            }
        %>
    </select><br>

    <label for="winner">Nyertes</label><br>
    <select name="winner" id="winner">
        <%
            List<Team> teams = TeamDAOImpl.getInstance().findAll();
            for(Team t : teams){
                out.print("<option value='"+t.getId() + "'>" + t.getName() + "</option>");
            }
        %>
    </select>
    <label for="w_score"> Nyert körök: </label>
    <input type="number" maxlength="2" name="w_score" id="w_score" required>
    <br>
    <label for="looser">Vesztes</label><br>
    <select name="looser" id="looser" required>
        <%
            for(Team t : teams){
                out.print("<option value='"+t.getId() + "'>" + t.getName() + "</option>");
            }
        %>
    </select>
    <label for="l_score"> Nyert körök: </label>
    <input type="number" maxlength="2" name="l_score" id="l_score" required>
    <br>
    <button type="submit">Tovább</button>
</form>

</body>
</html>
