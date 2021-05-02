package hu.alkfejl.controller;


import dao.PlayerDAO;
import dao.PlayerDAOImpl;
import dao.TeamDAO;
import dao.TeamDAOImpl;
import hu.alkfejl.App;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.util.Callback;
import model.Player;
import model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AddEditPlayer{

    private Player player;
    private PlayerDAO playerDAO = new PlayerDAOImpl();

    private TeamDAO teamDAO = new TeamDAOImpl();

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

    @FXML
    private ComboBox<Team> teamComboBox;



    public void setChoice(){
        this.role.getItems().addAll("Lurk","AWP","Entry fragger","Support","In game leader","Coach","Other");
        role.setDisable(false);

        Locale[] countries= Locale.getAvailableLocales();

        List<String> countryNames = new ArrayList<>();

        for(Locale c : countries){
            if(c.getDisplayCountry().length()>0) countryNames.add(c.getCountry());
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
        teamComboBox.valueProperty().bindBidirectional(player.teamProperty());
        teamComboBox.getItems().addAll(teamDAO.findAll());
        Callback<ListView<Team>, ListCell<Team>> teamFactory = new Callback<ListView<Team>, ListCell<Team>>() {

            @Override
            public ListCell<Team> call(ListView<Team> l) {
                return new ListCell<Team>() {

                    @Override
                    protected void updateItem(Team item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getId() + "    " + item.getName());
                        }
                    }
                } ;
            }
        };

        teamComboBox.setButtonCell(teamFactory.call(null));
        teamComboBox.setCellFactory(teamFactory);

        role.setDisable(true);
        nationality.setDisable(true);
        initChoice();
    }



    public void savePlayer(){
        System.out.println("Called");
        this.player = playerDAO.save(this.player);
    }

    public void ActiveAction(){
        //System.out.println("Szoszimó");
        teamComboBox.setDisable(!isActive.isSelected());
    }


    public void cancel(){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Menteni akarja a mostani állást? ",ButtonType.YES,ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                if(!(this.player.getTeam()==null || this.player.getNationality().equals("")|| this.player.getName()==null)){
                    savePlayer();
                }
            }
        });
        App.loadFXML("/fxml/mainWindow.fxml");
    }
}
