package model;

import javafx.beans.property.*;

public class PlayableMap {
    private IntegerProperty dbId = new SimpleIntegerProperty(this,"dbId");
    private StringProperty id = new SimpleStringProperty(this,"id"); //pl: de_dust2
    private StringProperty name = new SimpleStringProperty(this,"name"); //Dust 2
    //TODO: implement icon

    public int getDbId() {
        return dbId.get();
    }

    public IntegerProperty dbIdProperty() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId.set(dbId);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
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
}
