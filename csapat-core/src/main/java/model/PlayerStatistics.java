package model;

import javafx.beans.property.*;

public class PlayerStatistics {
    /*  EZ MATCHRE ES OVERALL STATRA IS FASZANTOS!
    *   id (database id), null ha o
    *   player
    *   match , ha null akkor globalis statisztika!
    *   kill (int, suicide miatt epp lehet negativ,de ha pro maccsen annyit lo akkor bot mint en omegalul)
    *   death (int,0<=)
    *   assist (int0<=)
    *   mvp (int 0<=)
    *   adr (int 0<=)
    *   hs (float 0<=)
    *   k/d (float, fuggveny only)
    * */
    private IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private ObjectProperty<Player> player = new SimpleObjectProperty<>(this,"player");
    private ObjectProperty<Match> match = new SimpleObjectProperty<>(this,"match");
    private IntegerProperty kills = new SimpleIntegerProperty(this,"kills");
    private IntegerProperty deaths = new SimpleIntegerProperty(this,"deaths");
    private IntegerProperty assists = new SimpleIntegerProperty(this,"assists");
    private IntegerProperty mvp = new SimpleIntegerProperty(this,"mvp");
    private IntegerProperty adr = new SimpleIntegerProperty(this,"adr");
    private IntegerProperty headshotPercentage = new SimpleIntegerProperty(this,"headshotPercentage ");


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getKills() {
        return kills.get();
    }

    public IntegerProperty killsProperty() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills.set(kills);
    }

    public int getDeaths() {
        return deaths.get();
    }

    public IntegerProperty deathsProperty() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths.set(deaths);
    }

    public int getAssists() {
        return assists.get();
    }

    public IntegerProperty assistsProperty() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists.set(assists);
    }

    public int getMvp() {
        return mvp.get();
    }

    public IntegerProperty mvpProperty() {
        return mvp;
    }

    public void setMvp(int mvp) {
        this.mvp.set(mvp);
    }

    public int getAdr() {
        return adr.get();
    }

    public IntegerProperty adrProperty() {
        return adr;
    }

    public void setAdr(int adr) {
        this.adr.set(adr);
    }

    public int getHeadshotPercentage() {
        return headshotPercentage.get();
    }

    public IntegerProperty headshotPercentageProperty() {
        return headshotPercentage;
    }

    public void setHeadshotPercentage(int headshotPercentage) {
        this.headshotPercentage.set(headshotPercentage);
    }

    public Player getPlayer() {
        return player.get();
    }

    public ObjectProperty<Player> playerProperty() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player.set(player);
    }

    public Match getMatch() {
        return match.get();
    }

    public ObjectProperty<Match> matchProperty() {
        return match;
    }

    public void setMatch(Match match) {
        this.match.set(match);
    }

    public float calculateKd(){
        if(this.deaths.get() == 0){
            return this.kills.get();
        }
        return (float)this.kills.get()/(float)this.deaths.get();
    }
}
