package com.example.salpagarov_rustam_201_hw5;

import com.example.salpagarov_rustam_201_hw5.navigation.Navigation;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ConnectController {
    public TextField host;
    public TextField port;
    public Button connectButton;
    public ProgressIndicator progressBar;

    public void connect(ActionEvent actionEvent) throws IOException {
        host.setDisable(true);
        port.setDisable(true);
        connectButton.setVisible(false);
        progressBar.setVisible(true);
        new SocketConnection(host.getText(), Integer.parseInt(port.getText()));
    }
}
