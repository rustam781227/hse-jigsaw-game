package server;

import server.db.GameResult;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer extends ServerSocket implements Runnable {

    private List<Player> players = new ArrayList<>();

    public GameServer(int port, int time, int gamers) throws IOException {
        super(port);
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Start");
        while (true){
            try {
                Socket socket = accept();
                players.add(new Player(this, socket));
                System.out.println("connect");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void remove(Player player){
        players.remove(player);
    }

    public void sendToAll(String header, Object body){
        for (Player p: players){
            p.send(header, body);
        }
    }

    public void checkAllReady() {
        for (Player p: players){
            if (p.getName().contains(Player.DEFAULT_NAME)) return;
        }
        sendToAll("START", "");
    }

    public void checkAllGameEnd() {
        for (Player player: players){
            if(player.getGameResult() == null) return;
        }
        List<GameResult> gameResults = new ArrayList<>();
        for (Player player: players){
           gameResults.add(player.getGameResult());
        }
        sendToAll("RESULT", gameResults);
    }
}
