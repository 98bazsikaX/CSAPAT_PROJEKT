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
    *   events (events obj lista)
    * */

    private IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private StringProperty name = new SimpleStringProperty(this,"name");
    private ObjectProperty<ObservableList<Match>> matches = new SimpleObjectProperty<>(this,"matches");
    private ObjectProperty<ObservableList<Tournament>> tournaments = new SimpleObjectProperty<>(this,"matches");
    private IntegerProperty foundationYear = new SimpleIntegerProperty(this,"foundationYear");

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

    public int getFoundationYear() {
        return foundationYear.get();
    }

    public IntegerProperty foundationYearProperty() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        this.foundationYear.set(foundationYear);
    }
}
