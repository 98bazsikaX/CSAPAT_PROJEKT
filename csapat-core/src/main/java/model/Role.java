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
    Role(int value){
        switch (value) {
            case 0:
                this.setValue("Lurk");
                break;
            case 1:
                this.setValue("AWP");
                break;
            case 2:
                this.setValue("Entry fragger");
                break;
            case 3:
                this.setValue("Support");
                break;
            case 4:
                this.setValue("In game leader");
                break;
            case 5:
                this.setValue("Coach");
                break;
            default:
                this.setValue("Unknown");
                break;
        }
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
