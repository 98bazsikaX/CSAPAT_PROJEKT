package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum StatType {
    //TODO: implement
    MATCH("Match"),
    GLOBAL("Global");

    private StringProperty value = new SimpleStringProperty(this,"value");

    StatType(String value){
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
