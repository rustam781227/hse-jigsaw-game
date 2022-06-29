package com.example.salpagarov_rustam_201_hw5;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WaitController {
    @FXML
    private Label text;

    public void setText(String txt){
        text.setText(txt);
    }
}
