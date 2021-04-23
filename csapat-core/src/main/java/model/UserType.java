package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum UserType {
    PLAYER("Player"),
    ADMIN("Admin");

    private StringProperty value = new SimpleStringProperty(this,"value");

    UserType(String value) {
        this.setValue(value);
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
}
