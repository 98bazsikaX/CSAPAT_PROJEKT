package hu.alkfejl;

import dao.MapDAO;
import dao.MapDAOImpl;
import dao.PlayerDAO;
import dao.PlayerDAOImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.PlayableMap;
import model.Player;

import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        PlayerDAO dao= new PlayerDAOImpl();
        Player asd = dao.findById(1);
        var str = ((asd==null)?("not found") : (asd.getName()));

        var label = new Label(str);
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}