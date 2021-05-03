<%@ page import="model.Team" %>
<%@ page import="dao.TeamDAOImpl" %>
<%@ page import="model.Player" %>
<%@ page import="dao.PlayerDAOImpl" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: 98baz
  Date: 2021. 05. 03.
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>További beállítások</title>
    <link rel="stylesheet" href="/resources/css/global.css">
    <%
        if(session.getAttribute("user")==null){
            response.sendRedirect("/login.jsp");
            return;
        }
    %>
</head>
<body>


<form name="match_details" method="post" action="/creatematch">
    <% /*ez baszott ronda, de remelem jo*/
        if
        ( request.getParameter("t_date")==null ||
            request.getParameter("m_map")==null ||
            request.getParameter("tournament") == null ||
            request.getParameter("winner")==null ||
            request.getParameter("w_score")==null ||
            request.getParameter("looser") == null ||
            request.getParameter("l_score")==null ||
            request.getParameter("w_score").equals(request.getParameter("l_score")) ||
            request.getParameter("winner").equals(request.getParameter("looser"))
        )
        {
            response.sendRedirect("/index.jsp");
        }

        String dateS = request.getParameter("t_date");
        out.print("<input type='hidden' id='t_date' name='t_date' value='"+ dateS +"'><br>");

        String mapS = request.getParameter("m_map");
        out.print("<input type='hidden' id='m_map' name='m_map' value='"+ mapS +"'><br>");

        String tournamentS = request.getParameter("tournament");
        out.print("<input type='hidden' id='tournament' name='tournament' value='"+ tournamentS +"'><br>");

        int w_score = Integer.parseInt(request.getParameter("w_score"));
        out.print("<input type='hidden' id='w_score' name='w_score' value='"+ w_score +"'><br>");

        int l_score = Integer.parseInt(request.getParameter("l_score"));
        out.print("<input type='hidden' id='l_score' name='l_score' value='"+ l_score +"'><br>");


        int id = Integer.parseInt(request.getParameter("winner"));
        Team winner = TeamDAOImpl.getInstance().getById(id);
        out.print("<input type='hidden' id='winner' name='winner' value='"+ winner.getId() +"'><br>");
        id = Integer.parseInt(request.getParameter("looser"));
        Team looser = TeamDAOImpl.getInstance().getById(id);
        out.print("<input type='hidden' id='looser' name='looser' value='"+ looser.getId() +"'><br>");
        List<Player> winner_players = PlayerDAOImpl.getInstance().findByTeam(winner.getId());
        request.setAttribute("winner_player",winner_players);
    %>
        <h2>Nyertes statisztikája:</h2>
<%--    <c:forEach items="${winner_players}" var="element">--%>

<%--            <p>${element.getId[]}</p>--%>

<%--    </c:forEach>--%>
    <%

        for(Player p : PlayerDAOImpl.getInstance().findByTeam(winner.getId())){
            out.print("<label>" + p.getName() + "</label><br>");
            out.print("<label for='w_kills_"+p.getId()+"' >Kills:</label><br>");
            out.print("<input type='number' id='w_kills_"+p.getId()+"' name='w_kills_"+p.getId()+"' required><br>");
            out.print("<label for='w_assist_"+p.getId()+"' >Assists:</label><br>");
            out.print("<input type='number' id='w_assist_"+p.getId()+"' name='w_assist_"+p.getId()+"' required><br>");
            out.print("<label for='w_death_"+p.getId()+"' >Deaths:</label><br>");
            out.print("<input type='number' id='w_death_"+p.getId()+"' name='w_death_"+p.getId()+"' required><br>");
            out.print("<label for='w_mvp_"+p.getId()+"' >MVPs:</label><br>");
            out.print("<input type='number' id='w_mvp_"+p.getId()+"' name='w_mvp_"+p.getId()+"' required><br>");
            out.print("<label for='w_adr_"+p.getId()+"' >Avg damage per round:</label><br>");
            out.print("<input type='number' id='w_adr_"+p.getId()+"' name='w_adr_"+p.getId()+"' required><br>");
            out.print("<label for='w_hs_"+p.getId()+"' >Headsot Percentage:</label><br>");
            out.print("<input type='number' id='w_hs_"+p.getId()+"' name='w_hs_"+p.getId()+"' required><br><br><br>");
        }

        out.print("<h2>Vesztes statisztikája:</h2>");

        for(Player p : PlayerDAOImpl.getInstance().findByTeam(looser.getId())){
            out.print("<label>" + p.getName() + "</label><br>");
            out.print("<label for='l_kills_"+p.getId()+"' >Kills:</label><br>");
            out.print("<input type='number' id='l_kills_"+p.getId()+"' name='l_kills_"+p.getId()+"' required><br>");
            out.print("<label for='l_assist_"+p.getId()+"' >Assists:</label><br>");
            out.print("<input type='number' id='l_assist_"+p.getId()+"' name='l_assist_"+p.getId()+"' required><br>");
            out.print("<label for='l_death_"+p.getId()+"' >Deaths:</label><br>");
            out.print("<input type='number' id='l_death_"+p.getId()+"' name='l_death_"+p.getId()+"' required><br>");
            out.print("<label for='l_mvp_"+p.getId()+"' >MVPs:</label><br>");
            out.print("<input type='number' id='l_mvp_"+p.getId()+"' name='l_mvp_"+p.getId()+"' required><br>");
            out.print("<label for='l_adr_"+p.getId()+"' >Avg damage per round:</label><br>");
            out.print("<input type='number' id='l_adr_"+p.getId()+"' name='l_adr_"+p.getId()+"' required><br>");
            out.print("<label for='l_hs_"+p.getId()+"' >Headsot Percentage:</label><br>");
            out.print("<input type='number' id='l_hs_"+p.getId()+"' name='l_hs_"+p.getId()+"' required><br><br><br>");
        }

%>

    <button type="submit">Küldés</button>

</form>

</body>
</html>
