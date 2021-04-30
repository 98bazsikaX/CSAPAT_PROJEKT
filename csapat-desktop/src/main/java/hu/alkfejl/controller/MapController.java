package hu.alkfejl.controller;

import dao.MapDAO;
import dao.MapDAOImpl;
import hu.alkfejl.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.PlayableMap;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;


public class MapController {

    MapDAO dao = new MapDAOImpl();

    @FXML
    private TableView<PlayableMap> mapTable;

    @FXML
    private TableColumn<PlayableMap,String> mapIdColumn;

    @FXML
    private TableColumn<PlayableMap,String> mapNameColumn;

    @FXML
    private TableColumn<PlayableMap,Void> OptionsColumn;


    //@Override
    public void initialize() {
        mapIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        mapNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        OptionsColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteButton = new Button("Törlés");
            private final Button editButton = new Button("Módosítás");

            {
                deleteButton.setOnAction(event -> {
                    PlayableMap map = getTableRow().getItem();
                    deleteMap(map);
                    refreshTable();

                });

                editButton.setOnAction(event -> {
                    PlayableMap m = getTableRow().getItem();
                    editMap(m);
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
                    container.getChildren().addAll(editButton,deleteButton);
                    container.setSpacing(10);
                    setGraphic(container);
                }
            }
        });


        refreshTable();
    }

    private void refreshTable() {
        //System.out.println(dao.findAll()==null);
        mapTable.getItems().setAll((Collection<PlayableMap>) dao.findAll() );
    }

    public void newMap(){
        PlayableMap map = new PlayableMap();

        editMap(map);

    }

    public void editMap(PlayableMap map){
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_edit_map.fxml");
        AddEditMap controller = fxmlLoader.getController();
        controller.setMap(map);
        refreshTable();
    }
    public void goToMain(){
        App.loadFXML("/fxml/mainWindow.fxml");
    }

    private void deleteMap(PlayableMap m){
        //TODO: biztos?
        dao.delete(m);
    }

    public void exit(){
        Platform.exit();
    }
}
