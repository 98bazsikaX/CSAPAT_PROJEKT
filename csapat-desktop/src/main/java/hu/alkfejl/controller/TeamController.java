package hu.alkfejl.controller;

import dao.TeamDAO;
import dao.TeamDAOImpl;
import hu.alkfejl.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.Team;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamController implements Initializable {

    TeamDAO teamDAO = new TeamDAOImpl();


    @FXML
    private TableView<Team> teamTable;


    @FXML
    private TableColumn<Team,Integer> teamIdColumn;

    @FXML
    private TableColumn<Team,String> teamNameColumn;

    @FXML
    private TableColumn<Team,String> teamNationalityColumn;

    @FXML
    private TableColumn<Team,String> teamFoundedColumn;

    @FXML
    private TableColumn<Team,Void> settingsColumn;

    private URL url;
    private ResourceBundle b;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.url=url;
        this.b = resourceBundle;
        teamIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teamNationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        teamFoundedColumn.setCellValueFactory(new PropertyValueFactory<>("founded"));

        settingsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button del = new Button("Törlés");
            private final Button edit = new Button("Módosítás");
            {
                del.setOnAction(event -> {
                    Team team =getTableRow().getItem();
                    delete(team);
                    refreshTable();
                });
                edit.setOnAction(event -> {
                    Team team = getTableRow().getItem();
                    editTeam(team);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }else{
                    HBox container = new HBox();
                    container.getChildren().addAll(edit,del);
                    container.setSpacing(10);
                    setGraphic(container);
                }
            }
        });

        refreshTable();
    }

    public void players(){
        App.loadFXML("/fxml/mainWindow.fxml");
    }

    public void maps(){
        App.loadFXML("/fxml/showMaps.fxml");
    }

    private void refreshTable() {
        teamTable.getItems().setAll(teamDAO.findAll());
    }


    public void addTeam(){
        Team t = new Team();

        editTeam(t);
    }

    private void editTeam(Team t) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_edit_team.fxml");
        AddEditTeam controller = fxmlLoader.getController();
        controller.setTeam(t);
        refreshTable();
    }

    private void delete(Team t){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Biztos törölni akarja a csapatot?" ,ButtonType.APPLY,ButtonType.CANCEL);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.APPLY)){
               teamDAO.delete(t);
            }
        });

    }

}
