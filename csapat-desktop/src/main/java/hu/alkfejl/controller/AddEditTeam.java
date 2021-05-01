package hu.alkfejl.controller;

import dao.TeamDAO;
import dao.TeamDAOImpl;
import hu.alkfejl.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddEditTeam {

    private TeamDAO teamDAO = new TeamDAOImpl();
    private Team team = new Team();

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<String> nationality;

    @FXML
    private DatePicker founded;

    public void initChoice(){
        Runnable setCheck = () -> setChoice();
        Thread longRunning = new Thread(setCheck);
        longRunning.setDaemon(true);
        longRunning.start();
    }

    private void setChoice() {
        Locale[] countries= Locale.getAvailableLocales();

        List<String> countryNames = new ArrayList<>();

        for(Locale c : countries){
            if(c.getDisplayCountry().length()>0) countryNames.add(c.getDisplayCountry());
        }

        this.nationality.getItems().addAll(countryNames);

        nationality.setDisable(false);
    }

    public void setTeam(Team t){
        this.team = t;

        name.textProperty().bindBidirectional(this.team.nameProperty());
        nationality.valueProperty().bindBidirectional(this.team.nationalityProperty());
        nationality.setDisable(true);
        initChoice();
        founded.valueProperty().bindBidirectional(this.team.foundedProperty());
    }

    public void saveTeam(){
        this.team = teamDAO.save(this.team);
    }

    public void cancel(){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Menteni akarja a mostani állást? ", ButtonType.YES,ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                saveTeam();
            }
        });
        App.loadFXML("/fxml/showTeams.fxml");
    }




}
