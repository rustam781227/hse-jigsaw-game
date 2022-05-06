package com.example.salpagarov_rustam_201_hw5;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResultsAlertBox {
    public static void display(String time, int numberOfMoves) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Results");
        window.setMinWidth(250);
        Label movesResult = new Label();
        movesResult.setText("Number of moves " + String.valueOf(numberOfMoves));
        Label timeResult = new Label();
        timeResult.setText("Time " + time);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(movesResult, timeResult, closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
