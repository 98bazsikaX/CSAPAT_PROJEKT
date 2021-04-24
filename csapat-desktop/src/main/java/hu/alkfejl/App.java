package hu.alkfejl;

import dao.MapDAO;
import dao.MapDAOImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.PlayableMap;

import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        MapDAO dao = new MapDAOImpl();

        PlayableMap asd = dao.findByName("Dust 2");
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