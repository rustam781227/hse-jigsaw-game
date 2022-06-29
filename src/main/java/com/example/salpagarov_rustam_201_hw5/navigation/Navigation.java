package com.example.salpagarov_rustam_201_hw5.navigation;

import com.example.salpagarov_rustam_201_hw5.JigsawApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    private static Stage stage;
    private static ReadyListener<?> listener;
    private static FXMLLoader fxmlLoader;

    @FunctionalInterface
    public  interface ReadyListener<T>{
        void ready(T controller);
    }

    public static void setStage(Stage stage) {
        Navigation.stage = stage;
    }


    public static void navigate(String fxml, ReadyListener<?> listener) throws IOException {
        fxmlLoader = new FXMLLoader(JigsawApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Platform.runLater(() -> {
            stage.setTitle("Jigsaw");
            stage.setScene(scene);
            stage.show();
            if (listener != null) listener.ready(fxmlLoader.getController());
        });

    }

    public static void modal(String fxml, ReadyListener<?> listener) throws IOException {
        fxmlLoader = new FXMLLoader(JigsawApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Platform.runLater(() -> {

            Stage stageModal = new Stage();
            stageModal.initModality(Modality.WINDOW_MODAL);
            stageModal.initOwner(stage);
            stageModal.setScene(scene);
            stageModal.show();
            if (listener != null) listener.ready(fxmlLoader.getController());
        });

    }

    public static Object getController(){
        return fxmlLoader.getController();
    }



}
