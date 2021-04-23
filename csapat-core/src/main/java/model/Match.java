package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableMap;

import java.time.LocalDate;

public class Match {
    /*
    * id (db id)
    *tournament
    * map
    * date
    *  loosing team
    *   winning team
    *   stats
    *   DRAW nem lehetséges official versenyen mivel van overtime
    * */
    private IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private ObjectProperty<Tournament> tournament = new SimpleObjectProperty<>(this,"tournament");
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>(this,"date");
    private ObjectProperty<PlayableMap> map = new SimpleObjectProperty<>(this,"map");
    private ObjectProperty<Team> winner = new SimpleObjectProperty<>(this,"winner");
    private IntegerProperty winnerScore = new SimpleIntegerProperty(this,"winnerScore");
    private ObjectProperty<Team> looser = new SimpleObjectProperty<>(this,"looser");
    private IntegerProperty looserScore = new SimpleIntegerProperty(this,"looserScore");
    private ObjectProperty<ObservableMap<Player,PlayerStatistics>> stats = new SimpleObjectProperty<>(this,"stats");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Tournament getTournament() {
        return tournament.get();
    }

    public ObjectProperty<Tournament> tournamentProperty() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament.set(tournament);
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

    public PlayableMap getMap() {
        return map.get();
    }

    public ObjectProperty<PlayableMap> mapProperty() {
        return map;
    }

    public void setMap(PlayableMap map) {
        this.map.set(map);
    }

    public Team getWinner() {
        return winner.get();
    }

    public ObjectProperty<Team> winnerProperty() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner.set(winner);
    }

    public Team getLooser() {
        return looser.get();
    }

    public ObjectProperty<Team> looserProperty() {
        return looser;
    }

    public void setLooser(Team looser) {
        this.looser.set(looser);
    }

    public ObservableMap<Player, PlayerStatistics> getStats() {
        return stats.get();
    }

    public ObjectProperty<ObservableMap<Player, PlayerStatistics>> statsProperty() {
        return stats;
    }

    public void setStats(ObservableMap<Player, PlayerStatistics> stats) {
        this.stats.set(stats);
    }

    public int getWinnerScore() {
        return winnerScore.get();
    }

    public IntegerProperty winnerScoreProperty() {
        return winnerScore;
    }

    public void setWinnerScore(int winnerScore) {
        this.winnerScore.set(winnerScore);
    }

    public int getLooserScore() {
        return looserScore.get();
    }

    public IntegerProperty looserScoreProperty() {
        return looserScore;
    }

    public void setLooserScore(int looserScore) {
        this.looserScore.set(looserScore);
    }
}
