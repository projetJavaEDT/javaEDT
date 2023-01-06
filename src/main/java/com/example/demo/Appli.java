package com.example.demo;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Appli extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Appli.class.getResource("auth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900,500);
        stage.setTitle("EDT-authentification");
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("em.png")));

        stage.setScene(scene);

        //stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        launch();



    }
}


