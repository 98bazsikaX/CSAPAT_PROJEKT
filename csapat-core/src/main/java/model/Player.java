package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;


public class Player extends User{


    /*
    * id (database id)
    * nev
    * ingame_nev
    * role (enum)
    * nemzetiseg
    * szul datum
    * volt csapatok
    *  jelenlegi csapat
    *  active (aktiv e a jatekos, vagy epp kispadra van teve)
    * */


    private ObjectProperty<Role> role = new SimpleObjectProperty<>(this,"role");
    private ObjectProperty<ObservableList<Team>> teams = new SimpleObjectProperty<>(this,"teams");
    private BooleanProperty active = new SimpleBooleanProperty(this,"active");

    public Player() {
        super.setType(UserType.valueOf("Player"));
    }


    public Role getRole() {
        return role.get();
    }

    public ObjectProperty<Role> roleProperty() {
        return role;
    }

    public void setRole(Role role) {
        this.role.set(role);
    }

    public ObservableList<Team> getTeams() {
        return teams.get();
    }

    public ObjectProperty<ObservableList<Team>> teamsProperty() {
        return teams;
    }

    public void setTeams(ObservableList<Team> teams) {
        this.teams.set(teams);
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }
}
