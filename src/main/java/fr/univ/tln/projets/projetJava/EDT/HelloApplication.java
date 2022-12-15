package fr.univ.tln.projets.projetJava.EDT;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fr.univ.tln.projets.projetsJava.EDT/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 750 );
        stage.setTitle("Authentification");
        stage.setScene(scene);
        //stage.setFill(Color.web("#81c483"));
        stage.show();


    }

    public static void main(String[] args) {
        launch();

    }
}