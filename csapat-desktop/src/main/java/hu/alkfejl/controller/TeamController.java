package hu.alkfejl.controller;

import dao.TeamDAO;
import dao.TeamDAOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.Team;

public class TeamController {
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
    private TableColumn<Team,Integer> teamFoundedColumn;

    @FXML
    private TableColumn<Team,Void> settingsColumn;

    public void initialize(){
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

    private void refreshTable() {
        teamTable.getItems().setAll(teamDAO.findAll());
    }


    public void addTeam(){
        Team t = new Team();

        editTeam(t);
    }

    private void editTeam(Team t) {
        //TODO: IMPLEMENT
    }

    private void delete(Team t){
        //TODO: Implement
    }

}
