package com.example.salpagarov_rustam_201_hw5;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JigsawController {

    @FXML
    protected void onExitButtonClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    @FXML
    protected void onNeGameButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent gamePageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene gamePageScene = new Scene(gamePageParent);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(gamePageScene);
        stage.show();
    }
}