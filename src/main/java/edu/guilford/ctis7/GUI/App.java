package edu.guilford.ctis7.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/guilford/ctis7/main page.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);


        if(Screen.getScreens().size()  >= 2) {
            // Get the list of screens
            Screen secondScreen = Screen.getScreens().get(1); // Assuming the second screen is at index 1
            Rectangle2D bounds = secondScreen.getVisualBounds();
            stage.setX(bounds.getMaxX());
            stage.setY(bounds.getMaxY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight()/2);
            System.out.println(bounds.getWidth());
            System.out.println(bounds.getHeight()/2);
        }
        else {
            stage.setX(0);
            stage.setY(0);

            stage.setWidth(1080.0);
            stage.setHeight(947.5);

        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
