package model;

public class Admin extends User{

    //TODO: implement stuff if necessary
    public Admin() {
        super.setType(UserType.valueOf("Admin"));
    }
}
