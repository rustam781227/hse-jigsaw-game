package com.example.salpagarov_rustam_201_hw5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.db.GameResult;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TopResultController {


    @FXML
    ProgressIndicator progressBar;
    @FXML
    TableColumn<GameResult, String> login;
    @FXML
    TableColumn<GameResult, String> time;
    @FXML
    TableColumn<GameResult, Integer> score;
    @FXML
    TableColumn<GameResult, Date> date;


    @FXML
    TableView<GameResult> resultTable;

    public void setResult(List<GameResult> statistics){
        resultTable.getItems().clear();
        List<GameResult> sortedList = statistics.stream()
                .sorted(Comparator.comparing(GameResult::getDate).thenComparing(GameResult::getTime).thenComparing(GameResult::getScore)).limit(10)
                .collect(Collectors.toList());

       ObservableList<GameResult> data = FXCollections.observableArrayList(sortedList);
       resultTable.setItems(data);
       progressBar.setVisible(false);

    }

    @FXML
    public void initialize() {
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
}
