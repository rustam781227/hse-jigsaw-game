package com.example.salpagarov_rustam_201_hw5;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class JoinMultiplayerMenuController {
    @FXML
    private TextField hostTextField;
    @FXML
    private TextField portTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label errorLabel;

    public void onSeverJoinButtonClicked(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        String name = nameTextField.getText();
        String host = hostTextField.getText();
        int port = Integer.parseInt(portTextField.getText());
        new SocketConnection(host, port);
        SocketConnection.send("REGISTER" + SocketConnection.SPLITTER + name);
    }
}
