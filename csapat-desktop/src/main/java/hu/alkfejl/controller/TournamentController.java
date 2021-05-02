package hu.alkfejl.controller;

import dao.TournamentDAO;
import dao.TournamentDAOImpl;
import hu.alkfejl.App;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.Team;
import model.Tournament;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TournamentController implements Initializable {

    private TournamentDAO tDao = new TournamentDAOImpl();

    @FXML
    private TableView<Tournament> tournamentTable;

    @FXML
    private  TableColumn<Tournament,Integer> tId;

    @FXML
    private TableColumn<Tournament,String> name;

    @FXML
    private TableColumn<Tournament,String > location;

    @FXML
    private TableColumn<Tournament, LocalDate> date;

    @FXML
    private TableColumn<Tournament, Team> winner;

    @FXML
    private TableColumn<Tournament,Void> options;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();

        tId.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));


        winner.setCellValueFactory(Tournament ->{
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(Tournament.getValue().getWinner().getName());
            return property;
        });

        options.setCellFactory(param -> new TableCell<>(){
            private final Button deleteButton = new Button("Delete");
            private final Button editButton = new Button("Edit");

            { //STATIKUS CUCC__________________________________
                deleteButton.setOnAction(event -> {
                    //Organization org = getTableRow().getItem();
                    Tournament t = getTableRow().getItem();
                    deleteTournament(t);
                    refreshTable();

                });

                editButton.setOnAction(event -> {
                    Tournament t = getTableRow().getItem();
                    editTournament(t);
                    refreshTable();
                });
            }//________________________________________________


            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }else{
                    HBox container =  new HBox();
                    container.getChildren().addAll(editButton,deleteButton);
                    container.setSpacing(10);
                    setGraphic(container);
                }
            }
        });

    }

    public void newTournament(){
        Tournament t=new Tournament();
        editTournament(t);
    }

    private void editTournament(Tournament t) {
        FXMLLoader loader = App.loadFXML("/fxml/add_edit_tournament.fxml");
        AddEditTournament edit = loader.getController();
        edit.set(t);
        refreshTable();
    }

    private void deleteTournament(Tournament t) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Biztos törölni akarja a céget?" ,ButtonType.APPLY,ButtonType.CANCEL);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.APPLY)){
                tDao.delete(t);
            }
        });
    }

    private void refreshTable() {
        tournamentTable.getItems().addAll(tDao.findAll());
    }

    public void showPlayers(){
        App.loadFXML("/fxml/mainWindow.fxml");
    }
}
