package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Organization {
    /*
    *   id (database id)
    *   name (string)
    *   matches (org-nal jatszott meccsek, match list)
    *   foundationDate
    *   events (tournaments obj lista)
    * */

    private IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private StringProperty name = new SimpleStringProperty(this,"name");
    private ObjectProperty<ObservableList<Match>> matches = new SimpleObjectProperty<>(this,"matches");
    private ObjectProperty<ObservableList<Tournament>> tournaments = new SimpleObjectProperty<>(this,"matches");
    //private IntegerProperty foundationYear = new SimpleIntegerProperty(this,"foundationYear");
    private ObjectProperty<LocalDate> foundation = new SimpleObjectProperty<>(this,"foundation");

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

    public ObservableList<Match> getMatches() {
        return matches.get();
    }

    public ObjectProperty<ObservableList<Match>> matchesProperty() {
        return matches;
    }

    public void setMatches(ObservableList<Match> matches) {
        this.matches.set(matches);
    }

    public ObservableList<Tournament> getTournaments() {
        return tournaments.get();
    }

    public ObjectProperty<ObservableList<Tournament>> tournamentsProperty() {
        return tournaments;
    }

    public void setTournaments(ObservableList<Tournament> tournaments) {
        this.tournaments.set(tournaments);
    }

    public LocalDate getFoundation() {
        return foundation.get();
    }

    public ObjectProperty<LocalDate> foundationProperty() {
        return foundation;
    }

    public void setFoundation(LocalDate foundation) {
        this.foundation.set(foundation);
    }

}
