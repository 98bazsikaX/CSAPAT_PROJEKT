package hu.alkfejl.controller;


import dao.PlayerDAO;
import dao.PlayerDAOImpl;
import hu.alkfejl.App;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


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
    private ChoiceBox<String> nationality;



    public void setChoice(){
        this.role.getItems().addAll("Lurk","AWP","Entry fragger","Support","In game leader","Coach","Other");
        role.setDisable(false);

        Locale[] countries= Locale.getAvailableLocales();

        List<String> countryNames = new ArrayList<>();

        for(Locale c : countries){
            if(c.getDisplayCountry().length()>0) countryNames.add(c.getDisplayCountry());
        }

        this.nationality.getItems().addAll(countryNames);

        nationality.setDisable(false);
    }


    public void initChoice(){
        Runnable setCheck = () -> setChoice();
        Thread longRunning = new Thread(setCheck);
        longRunning.setDaemon(true);
        longRunning.start();
    }


    public void setPlayer(Player p) {
        this.player = p;

        name.textProperty().bindBidirectional(player.nameProperty());
        username.textProperty().bindBidirectional(player.usernameProperty());
        birthDate.valueProperty().bindBidirectional(player.birthDateProperty());
        isActive.selectedProperty().bindBidirectional(player.activeProperty());
        nationality.valueProperty().bindBidirectional(player.nationalityProperty());
        role.valueProperty().bindBidirectional(player.roleProperty());

        role.setDisable(true);
        nationality.setDisable(true);
        initChoice();
    }



    public void savePlayer(){
        System.out.println("Called");
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
