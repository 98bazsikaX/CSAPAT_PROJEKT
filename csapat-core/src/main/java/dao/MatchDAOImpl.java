package dao;

import configuration.ProjectConfig;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAOImpl implements MatchDAO{

    private static final String INSERT = "INSERT INTO MATCH(tournament_id, map_id, date, looser, winner, looser_score, winner_score) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE MATCH SET tournament_id=? , map_id=? , date=?, looser=?,winner=?,looser_score=?,winner=? WHERE id=?";
    private static final String DEL = "DELETE FROM MATCH WHERE id=?";
    private MapDAO mapGetter = new MapDAOImpl();
    private TeamDAO teamGetter = new TeamDAOImpl();

    private static final String ALL = "SELECT * FROM MATCH";
    private String CONN_URL;

    public MatchDAOImpl(){
        CONN_URL = ProjectConfig.getValue("db.url");
    }


    @Override
    public List<Match> findAll() {
        List<Match> matches = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(ALL);
        ){
            ResultSet set = statement.executeQuery();

            while(set.next()){
                Match toAdd = new Match();
                toAdd.setId(set.getInt("id"));
                //TODO: TOURNAMENT DAO
                Date d = Date.valueOf(set.getString("date"));
                toAdd.setDate(d.toLocalDate());
                toAdd.setMap(mapGetter.findByDbId(set.getInt("map_id")));
                toAdd.setLooser(teamGetter.getById(set.getInt("looser")));
                toAdd.setWinner(teamGetter.getById(set.getInt("winner")));
                toAdd.setLooserScore(set.getInt("looser_score"));
                toAdd.setWinnerScore(set.getInt("winner_score"));
                //TODO: csak ennyi? jon a vihar gec
                //toAdd.setStats();
                matches.add(toAdd);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return matches;
    }

    @Override
    public List<Match> findByTeam(Team team) {
        return findByTeam(team.getId());
    }

    @Override
    public List<Match> findByTeam(int id) {
        List<Match> retval = new ArrayList<>();
        for(Match m : findAll()){
            if(m.getLooser().getId() == id || m.getWinner().getId() == id){
                retval.add(m);
            }
        }
        return retval;
    }

    @Override
    public List<Match> findByTournament(Tournament tournament) {
        return findByTournament(tournament.getId());
    }

    @Override
    public List<Match> findByTournament(int id) {
        List<Match> matches = new ArrayList<>();
        for(Match m : findAll()){
            if(m.getTournament().getId() == id){
                matches.add(m);
            }
        }
        return matches;
    }

    @Override
    public Match findByID(Match match) {
        return findByID(match.getId());
    }

    @Override
    public Match findByID(int id) {
        for(Match m : findAll()){
            if(m.getId() == id) return m;
        }
        return null;
    }

    @Override
    public Match save(Match match) {
        try (Connection c = DriverManager.getConnection(CONN_URL)){
            PreparedStatement statement;
            if(match.getId()<=0){
                statement = c.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            }else{
                statement = c.prepareStatement(UPDATE);
                statement.setInt(8,match.getId());
            }

            //TODO: IMPLEMENT


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return match;
    }

    @Override
    public void delete(Match match) {
        try (Connection c = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = c.prepareStatement(DEL)
        ){
            statement.setInt(1,match.getId());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
