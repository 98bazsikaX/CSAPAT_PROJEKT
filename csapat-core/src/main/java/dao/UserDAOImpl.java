package dao;


import at.favre.lib.crypto.bcrypt.BCrypt;
import configuration.ProjectConfig;
import model.User;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static UserDAOImpl instance;

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            instance = new UserDAOImpl();
        }
        return instance;
    }

    private static final String SELECT_BY_ID = "SELECT * FROM USERS WHERE id=?";
    private static final String LOGIN = "SELECT * FROM USERS WHERE username=?";
    private static final String UPDATE_USER = "UPDATE USERS SET email=? , password=? WHERE id= ? ";
    private static final String HAS_PWD_EMAIL = "SELECT * FROM USERS where username=?";
    private String CONN_URL ;

    private UserDAOImpl(){
        this.CONN_URL = ProjectConfig.getValue("db.url");
    }

    @Override
    public boolean hasPasswordAndEmail(String username) {
        try(Connection c = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = c.prepareStatement(HAS_PWD_EMAIL)
        ){
            statement.setString(1,username);
            ResultSet toCheck = statement.executeQuery();
            if(toCheck.next()){
                return toCheck.getString("email").length()!=0 && toCheck.getString("password").length()!=0;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int getIDbyUsername(String username){
        try(Connection c = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = c.prepareStatement("SELECT id from USERS where username=?");
        ) {
            statement.setString(1,username);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                return set.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public User getUserByID(User user) {
        return getUserByID(user.getId());
    }

    @Override
    public User getUserByID(int id) {
        User user = new User();
       try(Connection c = DriverManager.getConnection(CONN_URL);
           PreparedStatement statement = c.prepareStatement(SELECT_BY_ID);
       ) {
           statement.setInt(1,id);
           ResultSet set = statement.executeQuery();
            if(set.next()){
                user.setId(set.getInt("id"));
                user.setName(set.getString("name"));
                user.setUsername(set.getString("username"));
                user.setNationality(set.getString("nationality"));
                Date bd = Date.valueOf(set.getString("birthDate"));
                user.setBirthDate(bd.toLocalDate());
            }

       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return user;
    }

    @Override
    public void EditUser(String email, String userpassword, int id) {

        try(Connection c = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = c.prepareStatement(UPDATE_USER)
        ) {
            String password = BCrypt.withDefaults().hashToString(12,userpassword.toCharArray());

            statement.setString(1,email);
            statement.setString(2,password);
            statement.setInt(3,id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public User login(String username, String password) {

        try (Connection c = DriverManager.getConnection(CONN_URL);
            PreparedStatement statement = c.prepareStatement(LOGIN)
        ){
            statement.setString(1,username);
            ResultSet set = statement.executeQuery();

            if(set.next()){
                String dbPwd = set.getString("password");
                BCrypt.Result is = BCrypt.verifyer().verify(password.toCharArray(),dbPwd);
                if(is.verified){
                    User retval = new User();
                    retval.setId(set.getInt("id"));
                    retval.setName(set.getString("name"));
                    retval.setUsername(username);
                    retval.setNationality(set.getString("nationality"));
                    Date bd = Date.valueOf(set.getString("birthDate"));
                    retval.setBirthDate(bd.toLocalDate());
                    retval.setEmail(set.getString("email"));
                    retval.setPassword(password);
                    return retval;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void Delete(User user) {

    }
}
