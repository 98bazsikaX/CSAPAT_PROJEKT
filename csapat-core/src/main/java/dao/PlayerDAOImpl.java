package dao;

import configuration.ProjectConfig;
import model.Player;
import model.Team;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO{

    private static PlayerDAOImpl instance;

    public static PlayerDAOImpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            instance = new PlayerDAOImpl();
        }
        return instance;
    }

    private static final String SELECT_ALL = "SELECT * FROM USERS";
    private static final String DELETE_PLAYER = "DELETE FROM USERS WHERE id=?";
    private static final String INSERT_INTO_USERS = "INSERT INTO USERS(name,username,role,nationality,birthDate,active,team_id) VALUES(?,?,?,?,?,?,?)";
    private static final String UPDATE_USERS = "UPDATE users SET name=? , username=?, role=?, nationality=? ,birthDate=? , active=? , team_id=? WHERE id=?";
    private static final String SELECT_BY_ID = "SELECT * FROM USERS WHERE id=?";
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
                toAdd.setTeam(teams.getById(set.getInt("team_id")));
                toAdd.setActive(set.getInt("active") ==1);

                retval.add(toAdd);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retval;
    }

    @Override
    public List<Player> findByTeam(Team team) {
        return findByTeam(team.getId());
    }

    @Override
    public List<Player> findByTeam(int teamId) {
        List<Player> players = new ArrayList<>();


        for(Player p : findAll()){
            if(p.getTeam() != null){
                if (p.getTeam().getId() == teamId) {
                    players.add(p);
                }
            }
        }
        return players;
    }

    @Override
    public List<Player> findByRole(String role) {
        List<Player> retval = new ArrayList<>();
        for(Player p : findAll()){
            if(p.getRole().equals(role)){
                retval.add(p);
            }
        }
        return retval;
    }


    @Override
    public List<Player> findByNationality(String nat) {
        List<Player> retval = new ArrayList<>();
        for(Player p : findAll()){
            if(p.getNationality().equals(nat)){
                retval.add(p);
            }
        }
        return retval;
    }

    @Override
    public Player findById(Player player) {
        return findById(player.getId());
    }

    @Override
    public Player findById(int playerId) {
        for(Player p : findAll()){
            if(p.getId() == playerId){
                return p;
            }
        }
        return null;
//       TODO: eredeti implementacio megunta magat par oraval hatarido elott
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

            statement.setString(1,player.getName());
            statement.setString(2,player.getUsername());
            statement.setString(3,player.getRole());
            statement.setString(4,player.getNationality());
            statement.setString(5,player.getBirthDate().toString());
            statement.setInt(6,player.isActive() ? 1:0);
            statement.setInt(7,player.getTeam().getId());

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
}
