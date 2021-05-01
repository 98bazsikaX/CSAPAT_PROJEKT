package hu.alkfejl.controller;

import dao.MapDAO;
import dao.MapDAOImpl;
import hu.alkfejl.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.PlayableMap;

public class AddEditMap {
    private PlayableMap map;
    private MapDAO mapDAO = new MapDAOImpl();

    @FXML
    private TextField ingameId;

    @FXML
    private TextField name;

    public void setMap(PlayableMap m){
        this.map = m;

        ingameId.textProperty().bindBidirectional(map.idProperty());
        name.textProperty().bindBidirectional(map.nameProperty());
    }

    public void saveMap(){
        this.map = mapDAO.save(this.map);
    }

    public void cancel(){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Menteni akarja a mostani állást? ", ButtonType.YES,ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                saveMap();
            }
        });
        App.loadFXML("/fxml/showMaps.fxml");
    }
}
