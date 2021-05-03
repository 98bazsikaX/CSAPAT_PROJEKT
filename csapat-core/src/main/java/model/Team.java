package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Team {
    /*
    *   id (Database id)
    *   name (csapat neve)
    *   nationality (csapat nemzetisege
    *   Player list
    *   alapitasanak eve (int)
    * */

    private IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private StringProperty name = new SimpleStringProperty(this,"name");
    private StringProperty nationality = new SimpleStringProperty(this,"nationality");
    //private ObjectProperty<ObservableList<Player>> players = new SimpleObjectProperty<>(this,"players"); //Current players
    //private IntegerProperty founded = new SimpleIntegerProperty(this,"founded");

    private ObjectProperty<LocalDate> founded = new SimpleObjectProperty<>(this,"founded");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

//    public ObservableList<Player> getPlayers() {
//        return players.get();
//    }
//
//    public ObjectProperty<ObservableList<Player>> playersProperty() {
//        return players;
//    }
//
//    public void setPlayers(ObservableList<Player> players) {
//        this.players.set(players);
//    }

    public LocalDate getFounded() {
        return founded.get();
    }

    public ObjectProperty<LocalDate> foundedProperty() {
        return founded;
    }

    public void setFounded(LocalDate founded) {
        this.founded.set(founded);
    }
}
