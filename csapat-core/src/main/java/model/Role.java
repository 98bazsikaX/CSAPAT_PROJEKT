package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public enum Role {
    LURK("Lurk"),
    AWP("AWP"),
    ENTRY("Entry fragger"),
    SUPPORT("Support"),
    IN_GAME_LEADER("In game leader"),
    COACH("Coach"),
    UNKNOWN("Unknown");

    private StringProperty value = new SimpleStringProperty(this,"value");

    Role(String value) {
        this.value.set(value);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
