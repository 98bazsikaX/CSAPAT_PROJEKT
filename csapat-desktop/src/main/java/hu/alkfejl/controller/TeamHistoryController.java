package hu.alkfejl.controller;

import dao.PlayerDAO;
import dao.PlayerDAOImpl;
import dao.TeamDAO;
import dao.TeamDAOImpl;
import hu.alkfejl.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Player;
import model.Team;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamHistoryController implements Initializable {

    private PlayerDAO pDao = new PlayerDAOImpl();
    private TeamDAO tDao = new TeamDAOImpl();
    private Player current = new Player();


    @FXML
    public ComboBox<Player> playerComboBox;

    @FXML
    public TableView<Team> teamTableView;

    @FXML
    public TableColumn<Team,String> nameColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerComboBox.getItems().addAll(pDao.findAll());

        Callback<ListView<Player>, ListCell<Player>> playerFactory = new Callback<ListView<Player>, ListCell<Player>>() {

            @Override
            public ListCell<Player> call(ListView<Player> l) {
                return new ListCell<Player>() {

                    @Override
                    protected void updateItem(Player item, boolean empty) {
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

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        playerComboBox.setButtonCell(null);
        playerComboBox.setCellFactory(playerFactory);

    }

    public void refreshTable(){
        current = playerComboBox.getValue();
        teamTableView.getItems().setAll(tDao.oldTeams(current));
    }

    public void goBack(){
        App.loadFXML("/fxml/mainWindow.fxml");
    }
}
