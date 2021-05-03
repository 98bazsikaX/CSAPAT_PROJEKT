import dao.*;
import model.Match;
import model.Player;
import model.PlayerStatistics;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;


@WebServlet("/creatematch")
public class CreateMatch extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        //TODO: tovabbiranyitas a getMatch-re
        //System.out.println(req.getParameter("t_date"));
        //System.out.println(req.getParameter("m_map"));

        Match toSave = new Match();
        toSave.setTournament(TournamentDAOImpl.getInstance().findById(Integer.parseInt(req.getParameter("tournament"))));
        toSave.setMap(MapDAOImpl.getInstance().findByDbId(Integer.parseInt(req.getParameter("m_map"))));
        Date asd = Date.valueOf(req.getParameter("t_date"));
        toSave.setDate(asd.toLocalDate());
        toSave.setLooser(TeamDAOImpl.getInstance().getById(Integer.parseInt(req.getParameter("looser"))));
        toSave.setLooserScore(Integer.parseInt(req.getParameter("l_score")));
        toSave.setWinner(TeamDAOImpl.getInstance().getById(Integer.parseInt(req.getParameter("winner"))));
        toSave.setWinnerScore(Integer.parseInt(req.getParameter("w_score")));

        toSave = MatchDAOImpl.getInstance().save(toSave);
        //MatchDAOImpl.getInstance().

        //Save winning stats
        for(Player p : PlayerDAOImpl.getInstance().findByTeam(Integer.parseInt(req.getParameter("winner")))){
            //System.out.println(req.getParameter("w_kills_"+p.getId()));
            PlayerStatistics stat = new PlayerStatistics();
            stat.setMatch(toSave);
            stat.setPlayer(p);
            stat.setKills(Integer.parseInt(req.getParameter("w_kills_"+p.getId())));
            stat.setDeaths(Integer.parseInt(req.getParameter("w_assist_"+p.getId())));
            stat.setMvp(Integer.parseInt(req.getParameter("w_mvp_"+p.getId())));
            stat.setAdr(Integer.parseInt(req.getParameter("w_adr_"+p.getId())));
            stat.setHeadshotPercentage(Integer.parseInt(req.getParameter("w_hs_"+p.getId())));
            StatDAOImpl.getInstance().save(stat);
        }
        for(Player p : PlayerDAOImpl.getInstance().findByTeam(Integer.parseInt(req.getParameter("looser")))){
            //System.out.println(req.getParameter("w_kills_"+p.getId()));
            PlayerStatistics stat = new PlayerStatistics();
            stat.setMatch(toSave);
            stat.setPlayer(p);
            stat.setKills(Integer.parseInt(req.getParameter("l_kills_"+p.getId())));
            stat.setDeaths(Integer.parseInt(req.getParameter("l_assist_"+p.getId())));
            stat.setMvp(Integer.parseInt(req.getParameter("l_mvp_"+p.getId())));
            stat.setAdr(Integer.parseInt(req.getParameter("l_adr_"+p.getId())));
            stat.setHeadshotPercentage(Integer.parseInt(req.getParameter("l_hs_"+p.getId())));
            StatDAOImpl.getInstance().save(stat);
        }
        resp.sendRedirect("/getMatch.jsp?id="+toSave.getId());
    }
}
