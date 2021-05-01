package dao;

import configuration.ProjectConfig;
import model.Player;
import model.Team;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeamDAOImpl implements TeamDAO{

    private static final String SELECT_ALL = "SELECT * FROM TEAMS";
    private static final String INSERT_INTO_TEAMS = "INSERT INTO TEAMS(name, nationality, founded) VALUES (?,?,?)";
    private static final String UPDATE_TEAM = "UPDATE TEAMS SET name=?,nationality=?,founded=? WHERE id=?";
    private static final String DELETE_TEAM = "DELETE FROM TEAMS WHERE id=?";
    private String CONN_URL;

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

                toadd.setFounded(LocalDate.parse(set.getString("founded")));

                retval.add(toadd);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retval;
    }

    @Override
    public List<Team> getByPlayerID(Player player) {
        return this.getByPlayerID(player.getId());
    }

    @Override
    public List<Team> getByPlayerID(int id) {
        return new ArrayList<Team>();
    }

    @Override
    public Team getById(Team team) {
        return getById(team.getId());
    }

    @Override
    public Team getById(int id) {
        return null;
    }

    @Override
    public Team getByName(Team team) {
        return getByName(team.getName());
    }

    @Override
    public Team getByName(String name) {
        return null;
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
