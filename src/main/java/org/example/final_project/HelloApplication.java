package org.example.final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.final_project.DAO.AdoptersDAO;
import org.example.final_project.DAO.AdoptionRecordsDAO;
import org.example.final_project.DAO.PetDAO;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        // Manually set the controller with dependencies
        HelloController controller = new HelloController(new PetDAO(), new AdoptionRecordsDAO(), new AdoptersDAO());
        loader.setController(controller);

        primaryStage.setTitle("Pet Adoption System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}