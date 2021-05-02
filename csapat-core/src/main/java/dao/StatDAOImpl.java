package dao;

import configuration.ProjectConfig;
import model.Match;
import model.Player;
import model.PlayerStatistics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatDAOImpl implements StatsDAO{


    private static final String FIND_ALL = "SELECT * FROM STATISTICS";
    private static final String DEL = "DELETE FROM STATISTICS WHERE id=?";
    private static final String INSERT = "INSERT INTO STATISTICS( kills, deaths, assists, mvp, adr, hs_percentage, player_id, match_id) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE STATISTICS SET kills=?,deaths=?,assists=?,mvp=?,adr=?,hs_percentage=?,player_id=?,match_id=? WHERE id=?";
    private String CONN_URL;
    private PlayerDAO getPlayers = new PlayerDAOImpl();
    private MatchDAO getMatch = new MatchDAOImpl();

    public StatDAOImpl(){
        this.CONN_URL = ProjectConfig.getValue("db.url");
    }


    @Override
    public List<PlayerStatistics> findAll() {
        List<PlayerStatistics> retval = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(FIND_ALL);
        ){
            ResultSet res = statement.executeQuery();
            while (res.next()){
                PlayerStatistics toAdd = new PlayerStatistics();
                toAdd.setId(res.getInt("id"));
                toAdd.setKills(res.getInt("kills"));
                toAdd.setDeaths(res.getInt("deaths"));
                toAdd.setAssists(res.getInt("assists"));
                toAdd.setMvp(res.getInt("mvp"));
                toAdd.setAdr(res.getInt("adr"));
                toAdd.setHeadshotPercentage(res.getInt("hs_percentage"));
                toAdd.setPlayer(getPlayers.findById(res.getInt("player_id")));
                toAdd.setMatch(getMatch.findByID(res.getInt("match_id")));

                retval.add(toAdd);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return retval;
    }

    @Override
    public List<PlayerStatistics> findByMatch(Match match) {
        return findByMatch(match.getId());
    }

    @Override
    public List<PlayerStatistics> findByMatch(int id) {
        List<PlayerStatistics> retval = new ArrayList<>();
        for(PlayerStatistics p : findAll()){
            if(p.getMatch().getId() == id){
                retval.add(p);
            }
        }

        return retval;
    }

    @Override
    public List<PlayerStatistics> findByPlayer(Player player) {
        return findByPlayer(player.getId());
    }

    @Override
    public List<PlayerStatistics> findByPlayer(int id) {
       List<PlayerStatistics> retval = new ArrayList<>();

       for(PlayerStatistics p : findAll()){
           if(p.getPlayer().getId() == id){
               retval.add(p);
           }
       }

       return retval;
    }

    @Override
    public PlayerStatistics save(PlayerStatistics stat) {

        try(Connection conn = DriverManager.getConnection(CONN_URL)){
            PreparedStatement statement;

            if(stat.getId()<=0){
                statement = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            }else{
                statement = conn.prepareStatement(UPDATE);
                statement.setInt(9,stat.getId());
            }

            statement.setInt(1,stat.getKills());
            statement.setInt(2,stat.getDeaths());
            statement.setInt(3,stat.getAssists());
            statement.setInt(4,stat.getMvp());
            statement.setInt(5,stat.getAdr());
            statement.setInt(6, (int) stat.getHeadshotPercentage());
            statement.setInt(7,stat.getPlayer().getId());
            statement.setInt(8,stat.getMatch().getId());

            int generated = statement.executeUpdate();
            if(generated==0){
                return null;
            }

            if(stat.getId()<=0){
                ResultSet set = statement.getGeneratedKeys();
                if(set.next()){
                    stat.setId(set.getInt(1));
                }else{
                    return null;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return stat;
    }

    @Override
    public void delete(PlayerStatistics stat) {
        try (Connection connection = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = connection.prepareStatement(DEL);
        ){
            statement.setInt(1,stat.getId());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
