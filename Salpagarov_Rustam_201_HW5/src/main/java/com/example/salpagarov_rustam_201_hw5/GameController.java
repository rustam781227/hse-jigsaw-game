package com.example.salpagarov_rustam_201_hw5;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Canvas gameArea;
    @FXML
    private Canvas figureGenerator;
    @FXML
    private Text stopwatch;
    @FXML
    private Pane mainContainerPane;

    private int[][] curFigure;
    private int curFigureColor;
    private Affine affine;
    private Round round;
    private Time time;
    private Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.affine = new Affine();
        this.affine.appendScale(400 / 9f, 400 / 9f);
        this.round = new Round(9, 9);
        draw();
        createFigure();
        startStopWatch();
    }

    /**
     * Starts the stopwatch on the game screen
     */
    private void startStopWatch() {
        time = new Time(0, 0);
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            time.oneSecondPassed();
                            stopwatch.setText(time.getCurrentTime());
                        }
                )
        );
        stopwatch.setText(time.getCurrentTime());
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void createFigure() {
        figureGenerator = new Canvas(142, 142);
        mainContainerPane.getChildren().add(figureGenerator);
        GraphicsContext g = figureGenerator.getGraphicsContext2D();
        curFigure = Figure.getRandomFigure();
        curFigureColor = Figure.getRandomColor();
        g.setFill(Figure.colors[curFigureColor]);
        for (int i = 0; i < curFigure.length; i++) {
            for (int j = 0; j < curFigure[i].length; j++) {
                if (curFigure[i][j] == 1) {
                    g.fillRect(i * 44, j * 44, 44, 44);
                }
            }
        }

        figureGenerator.setLayoutX(500);
        figureGenerator.setLayoutY(200);
        figureGenerator.setOnMouseDragged(this::figureGeneratorOnDrag);
        figureGenerator.setOnMouseReleased(this::figureGeneratorDragReleased);
    }

    private void figureGeneratorDragReleased(MouseEvent mouseEvent) {
        double xCoord = figureGenerator.getLayoutX();
        double yCoord = figureGenerator.getLayoutY();
        if (xCoord > gameArea.getLayoutX() + gameArea.getWidth() + 44 || yCoord > gameArea.getLayoutY() + gameArea.getHeight() + 44 || xCoord < gameArea.getLayoutX() - 44 || yCoord < gameArea.getLayoutY() - 44) {
            figureGenerator.setLayoutY(200);
            figureGenerator.setLayoutX(500);
            return;
        }
        int x = (int) ((xCoord - 80) / 44);
        int y = (int) ((yCoord - 167) / 44);
        boolean flag = true;
        for (int i = 0; i < curFigure.length; i++) {
            for (int j = 0; j < curFigure[i].length; j++) {
                if (curFigure[i][j] == 1) {
                    if ((x + i >= round.width || y + j >= round.height) || (round.getState(x + i, y + j) > 0)) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        if (!flag) {
            figureGenerator.setLayoutY(200);
            figureGenerator.setLayoutX(500);
        } else {
            for (int i = 0; i < curFigure.length; i++) {
                for (int j = 0; j < curFigure[i].length; j++) {
                    if (curFigure[i][j] == 1) {
                        round.setAlive(x + i, y + j, curFigureColor);
                    }
                }
            }
            draw();
            mainContainerPane.getChildren().remove(figureGenerator);
            createFigure();
            ++round.numberOfMoves;
        }
    }

    private void figureGeneratorOnDrag(MouseEvent mouseEvent) {
        figureGenerator.setLayoutX(figureGenerator.getLayoutX() + mouseEvent.getX());
        figureGenerator.setLayoutY(figureGenerator.getLayoutY() + mouseEvent.getY());
    }

    public void draw() {
        GraphicsContext g = this.gameArea.getGraphicsContext2D();
        g.setFill(Color.BLUE);
        g.setTransform(this.affine);
        g.setFill(Color.WHITESMOKE);
        g.fillRect(0, 0, 400, 400);
        g.setFill(Color.GRAY);
        for (int x = 0; x < this.round.width; ++x) {
            for (int y = 0; y < this.round.height; ++y) {
                if (round.getState(x, y) > 0) {
                    g.setFill(Figure.colors[round.getState(x, y)]);
                } else {
                    g.setFill(Color.WHITESMOKE);
                }
                g.fillRect(x, y, 1, 1);
            }
        }
        g.setStroke(Color.BLACK);
        g.setLineWidth(0.05);
        for (int x = 0; x <= this.round.width; ++x) {
            g.strokeLine(x, 0, x, 9);
        }
        for (int y = 0; y <= this.round.height; ++y) {
            g.strokeLine(0, y, 9, y);
        }
    }

    public void FinishButtonClicked(MouseEvent mouseEvent) throws IOException {
        timeline.stop();
        Parent mainMenuParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-menu.fxml")));
        Scene mainMenuScene = new Scene(mainMenuParent, 600, 600);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(mainMenuScene);
        stage.show();
        ResultsAlertBox.display(stopwatch.getText(), round.numberOfMoves);
    }
}
