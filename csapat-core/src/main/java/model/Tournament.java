package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Tournament {

    /*
    *   matches
    *   id (database id)
    *   date (local date)
    *   Location (city+country/lan , string)
    *   Organizer (Organization)
    *   participant teams
    *   participant players
    *   winner team
    *   MVP player (a verseny legertekesebb jatekosa,lehet h nem a nyertes csapate, asszem S1mple-nel volt h masodikok lettek de olyan kemeny h mvp lett a tag)
    * */


    private IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty(this,"date");
    private StringProperty location = new SimpleStringProperty(this,"location");
    private StringProperty name = new SimpleStringProperty(this,"name");
    private SimpleObjectProperty<Organization> organizer = new SimpleObjectProperty<>(this,"organizer");
    private SimpleObjectProperty<ObservableList<Team>> teams = new SimpleObjectProperty<>(this,"teams");
    private SimpleObjectProperty<ObservableList<Player>> players = new SimpleObjectProperty<>(this,"players");
    private SimpleObjectProperty<Team> winner = new SimpleObjectProperty<>(this,"winner");
   // private SimpleObjectProperty<ObservableList<Match>> matches = new SimpleObjectProperty<>(this,"matches");

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

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public Organization getOrganizer() {
        return organizer.get();
    }

    public SimpleObjectProperty<Organization> organizerProperty() {
        return organizer;
    }

    public void setOrganizer(Organization organizer) {
        this.organizer.set(organizer);
    }

    public ObservableList<Team> getTeams() {
        return teams.get();
    }

    public SimpleObjectProperty<ObservableList<Team>> teamsProperty() {
        return teams;
    }

    public void setTeams(ObservableList<Team> teams) {
        this.teams.set(teams);
    }

    public ObservableList<Player> getPlayers() {
        return players.get();
    }

    public SimpleObjectProperty<ObservableList<Player>> playersProperty() {
        return players;
    }

    public void setPlayers(ObservableList<Player> players) {
        this.players.set(players);
    }

//    public Player getMvp() {
//        return mvp.get();
//    }

//    public SimpleObjectProperty<Player> mvpProperty() {
//        return mvp;
//    }

//    public void setMvp(Player mvp) {
//        this.mvp.set(mvp);
//    }

    public Team getWinner() {
        return winner.get();
    }

    public SimpleObjectProperty<Team> winnerProperty() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner.set(winner);
    }

//    public ObservableList<Match> getMatches() {
//        return matches.get();
//    }
//
//    public SimpleObjectProperty<ObservableList<Match>> matchesProperty() {
//        return matches;
//    }
//
//    public void setMatches(ObservableList<Match> matches) {
//        this.matches.set(matches);
//    }
}
