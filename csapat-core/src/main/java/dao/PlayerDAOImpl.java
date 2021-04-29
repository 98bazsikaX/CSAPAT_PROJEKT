package dao;

import configuration.ProjectConfig;
import javafx.collections.FXCollections;
import model.Player;
import model.Role;
import model.Team;
import model.Tournament;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO{

    private static final String SELECT_ALL = "SELECT * FROM USERS WHERE isAdmin=0";
    private static final String SELECT_BY_CURRENT_TEAM = "SELECT * FROM USERS INNER JOIN PLAYED_IN ON USERS.id=PLAYED_IN.player_id WHERE team_id=? AND isAdmin=0 AND to=null ";
    private static final String SELECT_BY_ROLE = "SELECT * FROM USERS WHERE isAdmin=0 AND role=?";
    private static final String SELECT_BY_NATIONALITY = "SELECT * FROM USERS WHERE isAdmin=0 AND nationality=?";
    private static final String SELECT_BY_DB_ID = "SELECT * FROM USERS WHERE isAdmin=0 AND id=?";
    private static final String DELETE_PLAYER = "DELETE FROM USERS WHERE id=?";
    private static final String INSERT_INTO_USERS = "INSERT INTO USERS(isAdmin,name,username,role,nationality,birthDate,active) VALUES(?,?,?,?,?,?,?)";
    private static final String UPDATE_USERS = "UPDATE USERS SET isAdmin=? name=? username=? role=? nationality=? birthDate=? active=? WHERE id=?";
    private String CONN_URL;

    public PlayerDAOImpl(){
        this.CONN_URL = ProjectConfig.getValue("db.url");
    }

    @Override
    public List<Player> findAll() {
        List<Player> retval = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL)
        ){
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Player toAdd = new Player();

                toAdd.setId(set.getInt("id"));
                toAdd.setName(set.getString("name"));
                toAdd.setUsername(set.getString("username"));
                toAdd.setRole(set.getString("role"));
                toAdd.setNationality(set.getString("nationality"));
                Date bd = Date.valueOf(set.getString("birthDate"));
                toAdd.setBirthDate(bd==null ? LocalDate.ofEpochDay(0) : bd.toLocalDate());
                TeamDAO teams = new TeamDAOImpl();
                toAdd.setTeams(FXCollections.observableList(teams.getByPlayerID(toAdd.getId())));
                toAdd.setActive(set.getInt("active") ==1);

                retval.add(toAdd);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retval;
    }


    @Override
    public List<Player> findByCurrentTeam(Team team) {
        return this.findByCurrentTeam(team.getId());
    }

    @Override
    public List<Player> findByCurrentTeam(int teamId) {
        List<Player> retval = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_CURRENT_TEAM)
        ){
            statement.setInt(1,teamId);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Player toAdd = new Player();

                toAdd.setId(set.getInt("id"));
                toAdd.setName(set.getString("name"));
                toAdd.setUsername(set.getString("username"));
                toAdd.setRole(set.getString("role"));
                toAdd.setNationality(set.getString("nationality"));
                Date bd = Date.valueOf(set.getString("birthDate"));
                toAdd.setBirthDate(bd == null ? LocalDate.ofEpochDay(0) : bd.toLocalDate());
                TeamDAO teams = new TeamDAOImpl();
                toAdd.setTeams(FXCollections.observableList(teams.getByPlayerID(toAdd.getId())));
                toAdd.setActive(set.getInt("active") == 1);

                retval.add(toAdd);
                }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retval;
    }



    @Override
    public List<Player> findByRole(String role) {
        List<Player> retval = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ROLE)
        ){
            statement.setString(1,role);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Player toAdd = new Player();

                toAdd.setId(set.getInt("id"));
                toAdd.setName(set.getString("name"));
                toAdd.setUsername(set.getString("username"));
                toAdd.setRole(set.getString("role"));
                toAdd.setNationality(set.getString("nationality"));
                Date bd = Date.valueOf(set.getString("birthDate"));
                toAdd.setBirthDate(bd == null ? LocalDate.ofEpochDay(0) : bd.toLocalDate());
                TeamDAO teams = new TeamDAOImpl();
                toAdd.setTeams(FXCollections.observableList(teams.getByPlayerID(toAdd.getId())));
                toAdd.setActive(set.getInt("active") == 1);

                retval.add(toAdd);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retval;
    }


    @Override
    public List<Player> findByNationality(String nat) {
        List<Player> retval = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_NATIONALITY)
        ){
            statement.setString(1,nat);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Player toAdd = new Player();

                toAdd.setId(set.getInt("id"));
                toAdd.setName(set.getString("name"));
                toAdd.setUsername(set.getString("username"));
                toAdd.setRole(set.getString("role"));
                toAdd.setNationality(set.getString("nationality"));
                Date bd = Date.valueOf(set.getString("birthDate"));
                toAdd.setBirthDate(bd == null ? LocalDate.ofEpochDay(0) : bd.toLocalDate());
                TeamDAO teams = new TeamDAOImpl();
                toAdd.setTeams(FXCollections.observableList(teams.getByPlayerID(toAdd.getId())));
                toAdd.setActive(set.getInt("active") == 1);

                retval.add(toAdd);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retval;
    }

    @Override
    public Player findById(Player player) {
        return findById(player.getId());
    }

    @Override
    public Player findById(int playerId) {
        Player retval = new Player();
        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_DB_ID)
        ){
            statement.setInt(1,playerId);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                retval.setId(set.getInt("id"));
                retval.setName(set.getString("name"));
                retval.setUsername(set.getString("username"));
                retval.setRole(set.getString("role"));
                retval.setNationality(set.getString("nationality"));
                Date bd = Date.valueOf(set.getString("birthDate"));
                retval.setBirthDate(bd == null ? LocalDate.ofEpochDay(0) : bd.toLocalDate());
                TeamDAO teams = new TeamDAOImpl();
                retval.setTeams(FXCollections.observableList(teams.getByPlayerID(retval.getId())));
                retval.setActive(set.getInt("active") == 1);
            }else{
                return null;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retval;
    }


    @Override
    public Player save(Player player) {
        try(Connection connection = DriverManager.getConnection(CONN_URL)){
            PreparedStatement statement;
            if(player.getId()<=0){
                //isAdmin,name,username,role,nationality,birthDate,active
                statement = connection.prepareStatement(INSERT_INTO_USERS,Statement.RETURN_GENERATED_KEYS);
            }else{
                statement = connection.prepareStatement(UPDATE_USERS);
                statement.setInt(8,player.getId());
            }

            statement.setInt(1,0);
            statement.setString(2,player.getName());
            statement.setString(3,player.getUsername());
            statement.setString(4,player.getRole());
            statement.setString(5,player.getNationality());
            statement.setString(6,player.getBirthDate().toString());
            statement.setInt(7,player.isActive() ? 1:0);

            int affected = statement.executeUpdate();
            if(affected==0){
                return null;
            }
            if(player.getId()<=0){
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()) player.setId(rs.getInt(1));
            }
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return player;
    }

    @Override
    public void delete(Player player) {
        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(DELETE_PLAYER)
        ){
            statement.setInt(1,player.getId());
            statement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    private boolean isCurrent(String from, String to) {
        return true;
    }
}
