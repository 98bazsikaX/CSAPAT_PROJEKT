package hu.alkfejl;

import dao.MapDAO;
import dao.MapDAOImpl;
import dao.PlayerDAO;
import dao.PlayerDAOImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.PlayableMap;
import model.Player;

import java.io.IOException;
import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) {


        App.stage = stage;
        App.loadFXML("/fxml/mainWindow.fxml");

        stage.show();
    }
    public static FXMLLoader loadFXML(String fxml){

        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
        Scene scene = null;
        try {
           Parent root =  loader.load();
           scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);

        return loader;

    }
    public static void main(String[] args) {
        launch();
    }

}