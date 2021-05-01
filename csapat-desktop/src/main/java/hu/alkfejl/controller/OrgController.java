package hu.alkfejl.controller;

import com.sun.tools.javac.Main;
import dao.OrgDAO;
import dao.OrgDAOImpl;
import hu.alkfejl.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.Organization;
import model.Player;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrgController implements Initializable {

    OrgDAO dao = new OrgDAOImpl();

    @FXML
    private TableView<Organization> orgTable;

    @FXML
    private TableColumn<Organization,Integer> idColumn;

    @FXML
    private TableColumn<Organization,String> nameColumn;

    @FXML
    private TableColumn<Organization,String> foundedColumn;

    @FXML
    private TableColumn<Organization,Void> settingsColumn;

    private URL url;
    private ResourceBundle b;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        refreshTable();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        foundedColumn.setCellValueFactory(new PropertyValueFactory<>("foundation"));

        settingsColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteButton = new Button("Delete");
            private final Button editButton = new Button("Edit");

            { //STATIKUS CUCC__________________________________
                deleteButton.setOnAction(event -> {
                    Organization org = getTableRow().getItem();
                    deleteOrg(org);
                    refreshTable();

                });

                editButton.setOnAction(event -> {
                    Organization org = getTableRow().getItem();
                    editOrg(org);
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

    private void editOrg(Organization org) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Biztos törölni akarja a céget?" ,ButtonType.APPLY,ButtonType.CANCEL);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.APPLY)){
                dao.delete(org);
            }
        });

    }

    private void deleteOrg(Organization org) {

    }

    private void refreshTable() {
        orgTable.getItems().addAll(dao.findAll());
    }

    public void showPlayer(){
        FXMLLoader players = App.loadFXML("/fxml/mainWindow.fxml");
        MainWindowController controller = players.getController();
        controller.initialize(url,b);
    }

}
