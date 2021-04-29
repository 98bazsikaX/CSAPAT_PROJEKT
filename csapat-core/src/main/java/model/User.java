package model;

import javafx.beans.property.*;

import java.time.LocalDate;

public abstract class User {
    protected IntegerProperty id = new SimpleIntegerProperty(this,"id");
    protected StringProperty username = new SimpleStringProperty(this,"username");
    protected StringProperty name = new SimpleStringProperty(this,"name");
    protected BooleanProperty online = new SimpleBooleanProperty(this,"online");
    protected StringProperty nationality = new SimpleStringProperty(this,"nationality");
    protected ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>(this, "birthDate");
   // protected ObjectProperty<UserType> type;


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
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

    public boolean isOnline() {
        return online.get();
    }

    public BooleanProperty onlineProperty() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online.set(online);
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
}
