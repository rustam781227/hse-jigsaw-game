package server;

import api.SocketResponse;
import server.db.GameResult;
import server.db.ResultDao;
import server.fugure.FigureService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class Player implements Runnable {

    private static final String SPLITTER = "#####";
    public static String DEFAULT_NAME = "====NONAME===";
    private final GameServer server;
    private final Socket socket;
    private final ResultDao dao;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private String name;
    private int steps;

    private GameResult gameResult;

    public Player(GameServer gameServer, Socket socket) throws IOException {
        this.server = gameServer;
        this.socket = socket;
         dao = new ResultDao();
        reader = new ObjectInputStream(socket.getInputStream());
        writer = new ObjectOutputStream(socket.getOutputStream());
        name = DEFAULT_NAME;
        steps = 0;
        new Thread(this).start();
    }

    public void send(String header, Object body) {
        SocketResponse socketResponse = new SocketResponse(header, body);
        System.out.println("Send: " + socketResponse);
        try {
            writer.writeObject(socketResponse);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        send("CONNECT", "");
        while (true) {
            try {
                SocketResponse response = (SocketResponse) reader.readObject();
                if (response == null) break;
                new Thread(() -> {
                    try {
                        parse(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
        server.remove(this);
    }

    public String getName() {
        return name;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    private void parse(SocketResponse msg) throws IOException {
        System.out.println(msg);
        String header = msg.header();
        if (header.contains("REGISTER")) {
            steps = 0;
            this.name = (String) msg.body();
            server.checkAllReady();
        }

        if (header.contains("TOP")) {
            ResultDao resultDao = new ResultDao();
            List<GameResult> gameResults = resultDao.getResults();
            send("TOP", gameResults);
        }

        if (header.contains("FIGURE")) {
            steps++;
            send("FIGURE", FigureService.get()[steps]);
        }

        if (header.contains("RESULT")) {
            server.checkAllGameEnd();
        }

        if(header.contains("END")){
            gameResult = new GameResult();
            gameResult.setDate(new Date(System.currentTimeMillis()).toString());
            gameResult.setScore(steps);
            gameResult.setTime((String) msg.body());
            gameResult.setLogin(name);
            dao.add(gameResult);
        }


    }
}
