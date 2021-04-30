package hu.alkfejl.controller;


import dao.PlayerDAO;
import dao.PlayerDAOImpl;
import hu.alkfejl.App;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import model.Player;


public class AddEditPlayer{

    private Player player;
    private PlayerDAO playerDAO = new PlayerDAOImpl();

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    @FXML
    private DatePicker birthDate;

    @FXML
    private CheckBox isActive;

    @FXML
    private ChoiceBox<String> role;

    @FXML
    private TextField nationality;



    public void setPlayer(Player p) {
        this.player = p;


        this.role.getItems().addAll("Lurk","AWP","Entry fragger","Support","In game leader","Coach","Other");

        name.textProperty().bindBidirectional(player.nameProperty());
        username.textProperty().bindBidirectional(player.usernameProperty());
        //TODO: birthdate
        birthDate.valueProperty().bindBidirectional(player.birthDateProperty());

        isActive.selectedProperty().bindBidirectional(player.activeProperty());
        nationality.textProperty().bindBidirectional(player.nationalityProperty());
        role.valueProperty().bindBidirectional(player.roleProperty());

    }



    public void savePlayer(){

        this.player = playerDAO.save(this.player);

    }


    public void cancel(){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Menteni akarja a mostani állást? ",ButtonType.YES,ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                savePlayer();
            }
        });
        App.loadFXML("/fxml/mainWindow.fxml");
    }
}
