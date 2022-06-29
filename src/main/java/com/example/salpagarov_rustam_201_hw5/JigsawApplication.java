package com.example.salpagarov_rustam_201_hw5;

import com.example.salpagarov_rustam_201_hw5.navigation.Navigation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JigsawApplication extends Application {

    private static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        Navigation.setStage(stage);
        Navigation.navigate("connect.fxml", null);
    }


    private static TopResultController topResultController;


    public static void gameFxml() throws IOException {
        FXMLLoader _fxmlLoader = new FXMLLoader(JigsawApplication.class.getResource("game.fxml"));
        Scene gamePageScene = new Scene(_fxmlLoader.load());
        gameController = _fxmlLoader.getController();
        mainStage.hide();
        mainStage.setScene(gamePageScene);
        mainStage.show();
    }

    public static TopResultController getTopResultController() {
        return topResultController;
    }


    private static GameController gameController;

    public static void main(String[] args) {
        launch();
    }
}