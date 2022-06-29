package com.example.salpagarov_rustam_201_hw5;

import javafx.application.Platform;
import server.db.GameResult;
import api.SocketResponse;
import com.example.salpagarov_rustam_201_hw5.navigation.Navigation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SocketConnection extends Socket implements Runnable   {
    private static Socket socket;
    private static ObjectInputStream reader;
    private static ObjectOutputStream writer;
    public static final String SPLITTER = "#####";

    public SocketConnection(String host, int port) throws IOException {
        super(host, port);
        writer = new ObjectOutputStream(getOutputStream());
        reader = new ObjectInputStream(getInputStream());
        new Thread(this).start();
    }



    public static void send(Object o) throws IOException {
        new Thread(() -> {
            try {
                System.out.println(o);
                writer.writeObject(o);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }


    @Override
    public void run() {
        while (true){
            try {
                SocketResponse response =(SocketResponse) reader.readObject();
                if (response == null) break;
                parse(response);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void parse(SocketResponse response) throws IOException, ClassNotFoundException {
        String header = response.header();
        System.out.println("Send:" + response);
        if (header.contains("CONNECT")){
            Navigation.navigate("main-menu.fxml", null);
        }
        if (header.contains("START")){
            Navigation.navigate("game.fxml", null);

        }
        if (header.contains("TOP")){
            TopResultController controller = (TopResultController) Navigation.getController();
            controller.setResult((List<GameResult>) response.body());
        }

        if (header.contains("FIGURE")){
            GameController gameController = (GameController) Navigation.getController();
            Platform.runLater(() -> {
                try {
                    gameController.createFigure((int[][]) response.body());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });

        }
    }
}
