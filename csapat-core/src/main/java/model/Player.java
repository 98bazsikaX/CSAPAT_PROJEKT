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

    private StringProperty role = new SimpleStringProperty(this,"role");
    private ObjectProperty<Team> team = new SimpleObjectProperty<>(this,"team");
    private BooleanProperty active = new SimpleBooleanProperty(this,"active");

    public Player() {}


    public String getRole() {
        return role.get();
    }

    public StringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public Team getTeam() {
        return team.get();
    }

    public ObjectProperty<Team> teamProperty() {
        return team;
    }

    public void setTeam(Team team) {
        this.team.set(team);
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
