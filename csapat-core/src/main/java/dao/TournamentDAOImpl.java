package dao;

import configuration.ProjectConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Match;
import model.Player;
import model.Team;
import model.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentDAOImpl implements TournamentDAO{
    private static final String SELECT_ALL = "SELECT * FROM TOURNAMENTS";
    private static final String INSERT = "INSERT INTO TOURNAMENTS( name, date, location, orgId, winner_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE TOURNAMENTS SET name=?,date=?,location=?,orgId=?,winner_id=? where id=?";
    private static final String DEL = "DELETE FROM TOURNAMENTS WHERE id=?";
    private  String CONN_URL ;
    private OrgDAO orgDAO = new OrgDAOImpl();
    private TeamDAO teamDAO = new TeamDAOImpl();
    private MatchDAO matchDAO = new MatchDAOImpl();


    public TournamentDAOImpl(){
        this.CONN_URL = ProjectConfig.getValue("db.url");
    }

    @Override
    public List<Tournament> findAll() {
       List<Tournament> retval = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(CONN_URL);
             PreparedStatement statement = c.prepareStatement(SELECT_ALL)
        ){
            ResultSet set = statement.executeQuery();

            while(set.next()){
                Tournament t = new Tournament();

                t.setId(set.getInt("id"));
                t.setName(set.getString("name"));
                Date d = Date.valueOf(set.getString("date"));
                t.setDate(d.toLocalDate());
                t.setLocation(set.getString("location"));
                t.setOrganizer(orgDAO.findByID(set.getInt("orgId")));
                t.setWinner(teamDAO.getById(set.getInt("winner_id")));
                ObservableList<Match> matches = FXCollections.observableList(matchDAO.findByTournament(t.getId()));
                t.setMatches(matches);

                retval.add(t);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retval;
    }

    @Override
    public Tournament findByMatchID(Match match) {
        return findByMatchID(match.getId());
    }

    @Override
    public Tournament findByMatchID(int id) {
        for(Tournament t : findAll()){
            if(t.getId() == id){
                return t;
            }
        }
        return  null;
    }

    @Override
    public List<Tournament> findByWinner(Team team) {
        return findByWinner(team.getId());
    }

    @Override
    public List<Tournament> findByWinner(int id) {
        List<Tournament> retval = new ArrayList<>();
        for(Tournament t : findAll()){
            if(t.getWinner().getId()==id){
                retval.add(t);
            }
        }
        return retval;
    }

    @Override
    public Tournament save(Tournament tournament) {
        try (Connection c = DriverManager.getConnection(CONN_URL)){
            PreparedStatement statement;
            if(tournament.getId()<=0){
                statement = c.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            }else{
                statement = c.prepareStatement(UPDATE);
                statement.setInt(6,tournament.getId());
            }
            statement.setString(1,tournament.getName());
            statement.setString(2,tournament.getDate().toString());
            statement.setString(3,tournament.getLocation());
            statement.setInt(4,tournament.getOrganizer().getId());
            statement.setInt(5,tournament.getWinner().getId());

            int aff = statement.executeUpdate();

            if(aff<=0){
                return null;
            }

            if(tournament.getId()<=0){
                ResultSet set = statement.getGeneratedKeys();
                if(set.next()){
                    tournament.setId(set.getInt(1));
                }else{
                    return null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tournament;
    }

    @Override
    public void delete(Tournament tournament) {
        try(Connection c = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = c.prepareStatement(DEL);
        ){
            statement.setInt(1,tournament.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
