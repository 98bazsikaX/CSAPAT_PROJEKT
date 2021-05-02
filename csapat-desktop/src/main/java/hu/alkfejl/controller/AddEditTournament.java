package hu.alkfejl.controller;

import dao.*;
import hu.alkfejl.App;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.Organization;
import model.Team;
import model.Tournament;

import java.util.ArrayList;
import java.util.List;


public class AddEditTournament {
    private Tournament tournament;
    private TournamentDAO dao = new TournamentDAOImpl();

    private OrgDAO orgDao = new OrgDAOImpl();
    private ObservableList<Organization> organizations = FXCollections.observableList(orgDao.findAll());

    private TeamDAO teamDAO = new TeamDAOImpl();
    private ObservableList<Team> winnersList = FXCollections.observableList(teamDAO.findAll());

    @FXML
    private TextField name;

    @FXML
    private DatePicker date;

    @FXML
    private TextField locationRow;

    @FXML
    private ComboBox<Organization> orgBox;


    @FXML
    private ComboBox<Team> winner;



    public void set(Tournament t) {
        this.tournament = t;

        orgBox.getItems().addAll(organizations);

        Callback<ListView<Organization>, ListCell<Organization>> cellFactory = new Callback<ListView<Organization>, ListCell<Organization>>() {

            @Override
            public ListCell<Organization> call(ListView<Organization> l) {
                return new ListCell<Organization>() {

                    @Override
                    protected void updateItem(Organization item, boolean empty) {
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
        orgBox.setButtonCell(cellFactory.call(null));
        orgBox.setCellFactory(cellFactory);


        winner.getItems().addAll(winnersList);

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

        winner.setButtonCell(teamFactory.call(null));
        winner.setCellFactory(teamFactory);

        name.textProperty().bindBidirectional(tournament.nameProperty());
        date.valueProperty().bindBidirectional(tournament.dateProperty());
        locationRow.textProperty().bindBidirectional(tournament.locationProperty());
        orgBox.valueProperty().bindBidirectional(tournament.organizerProperty());
        winner.valueProperty().bindBidirectional(tournament.winnerProperty());
    }

    public void save(){
        this.tournament = dao.save(this.tournament);
    }

    public void exitWindow(){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Menteni akarja a mostani állást? ", ButtonType.YES,ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
               save();
            }
        });
        App.loadFXML("/fxml/showTournament.fxml");
    }
}
