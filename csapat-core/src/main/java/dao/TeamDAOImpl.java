package dao;

import configuration.ProjectConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Player;
import model.Team;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeamDAOImpl implements TeamDAO{

    private static TeamDAOImpl instance;

    public static TeamDAOImpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            instance = new TeamDAOImpl();
        }
        return instance;
    }

    private static final String SELECT_ALL = "SELECT * FROM TEAMS";
    private static final String INSERT_INTO_TEAMS = "INSERT INTO TEAMS(name, nationality, founded) VALUES (?,?,?)";
    private static final String UPDATE_TEAM = "UPDATE TEAMS SET name=?,nationality=?,founded=? WHERE id=?";
    private static final String DELETE_TEAM = "DELETE FROM TEAMS WHERE id=?";
    private static final String GET_BY_ID = "SELECT * FROM TEAMS WHERE id=?";
    private static final String OLD_TEAMS = "SELECT * FROM TEAMS INNER JOIN was_in_table wit on TEAMS.id = wit.team_id WHERE player_id=?";
    private String CONN_URL;
    private PlayerDAO player = new PlayerDAOImpl();


    public TeamDAOImpl(){
        this.CONN_URL = ProjectConfig.getValue("db.url");
    }

    @Override
    public List<Team> findAll() {
        List<Team> retval = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
        ){
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Team toadd = new Team();

                toadd.setId(set.getInt("id"));
                toadd.setName(set.getString("name"));
                toadd.setNationality(set.getString("nationality"));
               // ObservableList<Player> asd = FXCollections.observableArrayList(player.findByTeam(toadd.getId()));
               // toadd.setPlayers(asd);

                toadd.setFounded(LocalDate.parse(set.getString("founded")));

                retval.add(toadd);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retval;
    }

    @Override
    public Team getByPlayer(Player player) {
        return this.getByPlayer(player.getId());
    }

    @Override
    public Team getByPlayer(int id) {
        for(Player p : player.findAll()){
            if(p.getId()==id){
                return p.getTeam();
            }
        }
        return null;
    }

    @Override
    public Team getById(Team team) {
        return getById(team.getId());
    }

    @Override
    public Team getById(int id) {
        for(Team t : findAll()){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }


    @Override
    public List<Team> oldTeams(Player player) {
        return oldTeams(player.getId());
    }

    @Override
    public List<Team> oldTeams(int id) {
        List<Team> teams = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = c.prepareStatement(OLD_TEAMS)
        ){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Team toAdd = new Team();
                toAdd.setId(set.getInt("id"));
                toAdd.setName(set.getString("name"));
                toAdd.setNationality(set.getString("nationality"));
                Date f = Date.valueOf(set.getString("founded"));
                toAdd.setFounded(f.toLocalDate());

                teams.add(toAdd);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return teams;
    }

    @Override
    public Team save(Team team) {
        try(Connection connection = DriverManager.getConnection(CONN_URL)){
            PreparedStatement statement;
            if(team.getId()<=0){
                statement = connection.prepareStatement(INSERT_INTO_TEAMS,Statement.RETURN_GENERATED_KEYS);
            }else{
                statement = connection.prepareStatement(UPDATE_TEAM);
                statement.setInt(4,team.getId());
            }
            statement.setString(1,team.getName());
            statement.setString(2,team.getNationality());
            statement.setString(3,team.getFounded().toString());

            int affectedRows = statement.executeUpdate();

            if(affectedRows==0){
                return null;
            }

            if(team.getId()<=0){
                ResultSet s = statement.getGeneratedKeys();
                if(s.next()){
                    team.setId(s.getInt(1));
                }
            }
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return team;
    }

    @Override
    public void delete(Team team) {

        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(DELETE_TEAM);
        ){
            statement.setInt(1,team.getId());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
