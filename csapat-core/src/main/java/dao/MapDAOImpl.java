package dao;


import configuration.ProjectConfig;
import model.PlayableMap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MapDAOImpl implements MapDAO {

    private static final String FIND_BY_DB_ID = "SELECT * FROM MAPS WHERE id=?";
    private static final String FIND_BY_IG_ID = "SELECT * FROM MAPS WHERE ingame_id=?";
    private static final String FIND_BY_NAME =  "SELECT *FROM MAPS WHERE name=?";
    private static final String DELETE_MAP = "DELETE FROM MAPS WHERE id=?";


    private final String SELECT_ALL = "SELECT * FROM MAPS";

    private static final String INSERT_INTO_MAPS = "INSERT INTO MAPS (ingame_id , name) VALUES(?,?)";
    private static final String UPDATE_MAPS ="UPDATE MAPS SET ingame_id=? name=? WHERE id=?";

    private String CONN_URL;

    public MapDAOImpl(){
        this.CONN_URL = ProjectConfig.getValue("db.url");

    }

    @Override
    public List<PlayableMap> findAll() {
       List<PlayableMap> retval = new ArrayList<>();

       try(Connection conn = DriverManager.getConnection(CONN_URL);
           PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
       ){
           ResultSet set =statement.executeQuery();
            while(set.next()){
                PlayableMap toAdd = new PlayableMap();

                toAdd.setDbId(set.getInt("id"));
                toAdd.setId(set.getString("ingame_id"));
                toAdd.setName(set.getString("name"));

                retval.add(toAdd);
            }
       }catch (SQLException throwables) {
           throwables.printStackTrace();
       }


        return retval;
    }

    @Override
    public PlayableMap findByDbId(int id) {
        PlayableMap retval = new PlayableMap();
        try (Connection conn = DriverManager.getConnection(CONN_URL);
             PreparedStatement statement = conn.prepareStatement(FIND_BY_DB_ID);
        ){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();

            if(set.next()){
                retval.setDbId(set.getInt("id"));
                retval.setId(set.getString("ingame_id"));
                retval.setName(set.getString("name"));
            }else{
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retval;
    }

    @Override
    public PlayableMap findByIgId(String id) {

        PlayableMap retval = new PlayableMap();

        try (Connection conn = DriverManager.getConnection(CONN_URL);
             PreparedStatement statement = conn.prepareStatement(FIND_BY_IG_ID);
        ){
            statement.setString(1,id);
            ResultSet set = statement.executeQuery();

            if(set.next()){
                retval.setDbId(set.getInt("id"));
                retval.setId(set.getString("ingame_id"));
                retval.setName(set.getString("name"));
            }else{
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retval;
    }

    @Override
    public PlayableMap findByName(String name) {

        PlayableMap retval = new PlayableMap();

        try (Connection conn = DriverManager.getConnection(CONN_URL);
             PreparedStatement statement = conn.prepareStatement(FIND_BY_NAME);
        ){
            statement.setString(1,name);
            ResultSet set = statement.executeQuery();

            if(set.next()){
                retval.setDbId(set.getInt("id"));
                retval.setId(set.getString("ingame_id"));
                retval.setName(set.getString("name"));
            }else{
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retval;

    }

    @Override
    public PlayableMap save(PlayableMap map) {
        try(Connection conn = DriverManager.getConnection(CONN_URL);){
            PreparedStatement statement;


            if(map.getDbId()<=0){
                statement = conn.prepareStatement(INSERT_INTO_MAPS,Statement.RETURN_GENERATED_KEYS);
            }else{
                statement = conn.prepareStatement(UPDATE_MAPS);
            }

            statement.setString(1,map.getId());
            statement.setString(2,map.getName());

            int affectedRows = statement.executeUpdate();

            if(affectedRows==0){
                return null;
            }

            if(map.getDbId()<=0){
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    map.setDbId(rs.getInt(1));
                }
            }

            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  null;
        }


        return map;
    }

    @Override
    public void delete(PlayableMap map) {
            try (Connection conn = DriverManager.getConnection(CONN_URL);
                PreparedStatement statement = conn.prepareStatement(DELETE_MAP);
            ){
                statement.setInt(1,map.getDbId());
                statement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }
}
