package dao;

import configuration.ProjectConfig;
import model.Organization;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrgDAOImpl implements OrgDAO{

    private static final String INSERT = "INSERT INTO ORG (name, foundation_date) VALUES (?,?)";
    private static final String UPDATE = "UPDATE ORG SET name=? , foundation_date=? where id=?";
    private static final String DELETE = "DELETE FROM ORG WHERE id=?";
    private static final String SELECT_BY_ID = "SELECT * FROM ORG WHERE id=?";
    private final String SELECT_ALL = "SELECT * FROM ORG";
    private String CONN_URL ;

    public OrgDAOImpl(){
        this.CONN_URL = ProjectConfig.getValue("db.url");
    }

    @Override
    public List<Organization> findAll() {
        List<Organization> orgs = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL)
        ){
            ResultSet set = statement.executeQuery();

            while(set.next()){
                Organization toAdd = new Organization();
                toAdd.setName(set.getString("name"));
                toAdd.setId(set.getInt("id"));
                Date founded = Date.valueOf(set.getString("foundation_date"));
                toAdd.setFoundation(founded==null ? LocalDate.ofEpochDay(0) : founded.toLocalDate());
                orgs.add(toAdd);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return orgs;
    }

    @Override
    public Organization findByID(Organization org) {
        return findByID(org.getId());
    }

    @Override
    public Organization findByID(int id) {
        Organization org = new Organization();

        try (Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID)
        ){

            ResultSet set = statement.executeQuery();
            if(!set.next()){
                return null;
            }
            org.setName(set.getString("name"));
            Date f = Date.valueOf(set.getString("foundation_date"));
            org.setFoundation(f.toLocalDate()==null ? LocalDate.EPOCH : f.toLocalDate());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return org;
    }

    @Override
    public Organization save(Organization org){

        try(Connection conn = DriverManager.getConnection(CONN_URL)){
            PreparedStatement statement;
            if(org.getId()<=0){
                statement = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            }else{
                statement = conn.prepareStatement(UPDATE);
                statement.setInt(3,org.getId());
            }
            statement.setString(1,org.getName());
            statement.setString(2,org.getFoundation().toString());

            int affected = statement.executeUpdate();
            if(affected==0){
                return null;
            }

            if(org.getId()<=0){
                ResultSet set = statement.getGeneratedKeys();
                if(set.next()){
                    org.setId(set.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return org;
    }

    @Override
    public void delete(Organization org) {
        try(Connection conn = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = conn.prepareStatement(DELETE)
        ){
            statement.setInt(1,org.getId());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
