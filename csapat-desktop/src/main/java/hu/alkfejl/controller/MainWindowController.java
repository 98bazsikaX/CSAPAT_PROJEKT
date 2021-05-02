package hu.alkfejl.controller;

import dao.OrgDAO;
import dao.OrgDAOImpl;
import dao.PlayerDAO;
import dao.PlayerDAOImpl;
import hu.alkfejl.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    PlayerDAO dao = new PlayerDAOImpl();

    @FXML
    private TableView<Player> playerTable;

    @FXML
    private TableColumn<Player,String> userName;

    @FXML
    private TableColumn<Player,String> playerRole;

    @FXML
    private TableColumn<Player,Void> OptionsColumn;

    private URL url;
    private ResourceBundle b;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.url = url;
        this.b = resourceBundle;
        refreshTable();

        userName.setCellValueFactory(new PropertyValueFactory<>("username"));
        playerRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        OptionsColumn.setCellFactory(param -> new TableCell<>(){
              private final Button deleteButton = new Button("Delete");
              private final Button editButton = new Button("Edit");

              { //STATIKUS CUCC__________________________________
                   deleteButton.setOnAction(event -> {
                        Player player =  getTableRow().getItem();
                       deletePlayer(player);
                        refreshTable();

                   });

                   editButton.setOnAction(event -> {
                        Player p =  getTableRow().getItem();
                       editPlayer(p);
                        refreshTable();
                   });
              }


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

    public void showMaps(){
        FXMLLoader asd =   App.loadFXML("/fxml/showMaps.fxml");
    }

    public void showTeams(){
        FXMLLoader asd = App.loadFXML("/fxml/showTeams.fxml");
    }

    public void showOrgs(){
        App.loadFXML("/fxml/showOrgs.fxml");
    }

    public void showTournaments(){
        App.loadFXML("/fxml/showTournament.fxml");
    }

    public void teamHistory(){
        App.loadFXML("/fxml/teamHistory.fxml");
    }

    @FXML
    private void addPlayer(){
        Player p = new Player();
        editPlayer(p);
    }

    private void editPlayer(Player p) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_EditPlayer.fxml");
        AddEditPlayer controller = fxmlLoader.getController();
        controller.setPlayer(p);
        refreshTable();
    }

    private void deletePlayer(Player player) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Biztos törölni akarja " + player.getUsername() + " -t?",ButtonType.APPLY,ButtonType.CANCEL);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.APPLY)){
                dao.delete(player);
            }
        });
    }

    private void refreshTable(){
        playerTable.getItems().setAll(dao.findAll());
    }



    @FXML
    public void onExit(){
        Platform.exit();
    }
}
