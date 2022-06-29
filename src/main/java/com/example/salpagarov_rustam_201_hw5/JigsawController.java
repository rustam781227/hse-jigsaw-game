package com.example.salpagarov_rustam_201_hw5;

import api.SocketResponse;
import com.example.salpagarov_rustam_201_hw5.navigation.Navigation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JigsawController {

    public Button topButton;
    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;
    @FXML
    private ProgressIndicator progressBar;
    @FXML
    private TextField nameTextFiled;

    @FXML
    protected void onExitButtonClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    @FXML
    protected void onNewGameButtonClicked(MouseEvent mouseEvent) throws IOException {


    }

    @FXML
    public void onNewMultiplayerButtonClicked(MouseEvent mouseEvent) throws IOException {

    }

    @FXML
    public void onJoinMultiplayerButtonClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate("wait.fxml",
                (Navigation.ReadyListener<WaitController>)
                        controller -> controller.setText("Ожидаем подключение соперника"));
        SocketConnection.send(new SocketResponse("REGISTER", nameTextFiled.getText()));

    }

    @FXML
    public void showTop(ActionEvent actionEvent) throws IOException {
        SocketConnection.send(new SocketResponse("TOP", ""));
        Navigation.modal("top.fxml", null);
    }
}