package dao;

import model.User;

public interface UserDAO {

    boolean hasPasswordAndEmail(String username);
    int getIDbyUsername(String username);

    User getUserByID(User user);
    User getUserByID(int id);

    User login(String username,String password);

    void EditUser(String email, String userpassword, int id);
    void Delete(User user);
}
