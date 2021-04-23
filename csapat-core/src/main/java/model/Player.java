package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Player {


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
    private IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private StringProperty irlName = new SimpleStringProperty(this,"irlName");
    private StringProperty ingameName = new SimpleStringProperty(this,"ingameName");
    private ObjectProperty<Role> role = new SimpleObjectProperty<>(this,"role");
    private StringProperty nationality = new SimpleStringProperty(this,"nationality");
    private ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>(this, "birthDate");
    private ObjectProperty<ObservableList<Team>> teams = new SimpleObjectProperty<>(this,"teams");
    private BooleanProperty active = new SimpleBooleanProperty(this,"active");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getIrlName() {
        return irlName.get();
    }

    public StringProperty irlNameProperty() {
        return irlName;
    }

    public void setIrlName(String irlName) {
        this.irlName.set(irlName);
    }

    public String getIngameName() {
        return ingameName.get();
    }

    public StringProperty ingameNameProperty() {
        return ingameName;
    }

    public void setIngameName(String ingameName) {
        this.ingameName.set(ingameName);
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

    public String getNationality() {
        return nationality.get();
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
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
