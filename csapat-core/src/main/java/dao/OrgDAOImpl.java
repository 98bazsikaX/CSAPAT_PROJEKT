package dao;

import configuration.ProjectConfig;
import model.Organization;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrgDAOImpl implements OrgDAO{

    private final String SELECT_ALL = "SELECT * FROM ORG";
    private String CONN_URL ;

    public OrgDAOImpl(){
        this.CONN_URL = ProjectConfig.getValue("db.url");
    }

    @Override
    public List<Organization> findAll(Organization org) {
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
        return null;
    }

    @Override
    public Organization findByName(Organization org) {
        return null;
    }

    @Override
    public Organization save(Organization org) {
        return null;
    }

    @Override
    public void delete(Organization org) {

    }
}
