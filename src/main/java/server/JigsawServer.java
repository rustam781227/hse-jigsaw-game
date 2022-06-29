package server;

import server.fugure.Figure;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class JigsawServer extends Thread {

    static class ConnectionHandler extends Thread {

        private final ServerSocket serverSocket;

        ConnectionHandler(ServerSocket serverSocket) {
            super("ConnectionHandler");
            this.serverSocket = serverSocket;
        }

        public void run() {
            try {
                while (true) {
                    Socket connected = serverSocket.accept();
                    System.out.println("ConnectionHandler: " + connected);
                    new JigsawServer(connected).start();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static final Set<JigsawServer> TEST_SERVERS = new HashSet<>();

    private final Socket connectedSocket;
    public static void startJigsawServer() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            ConnectionHandler connectionHandler = new ConnectionHandler(serverSocket);
            connectionHandler.start();
            Scanner scanner = new Scanner(System.in);
            try {
                connectionHandler.join();
            } catch (InterruptedException interruptedException) {
                System.out.println(interruptedException.getMessage());
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public JigsawServer(Socket connected) {
        super("JigsawServer: thread(" + connected.getPort() + ")");
        this.connectedSocket = connected;
        registerJigsawServer(this);
    }

    private static synchronized void registerJigsawServer(JigsawServer testServer) {
        TEST_SERVERS.add(testServer);
    }
    private static synchronized void stopJigsawServer(JigsawServer jigsawServer) {
        try {
            jigsawServer.interrupt();
            jigsawServer.connectedSocket.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void run() {
        try {
            registerJigsawServer(this);
            ObjectOutputStream out = new ObjectOutputStream(connectedSocket.getOutputStream());
            while (!isInterrupted()) {
                out.writeObject(figures);
            }
            out.close();
            stopJigsawServer(this);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
    private static int[][][] figures;
    public static int[][][] getFigureFromServer() throws IOException, ClassNotFoundException {
        return figures;
    }
    public static void main(String[] args) throws IOException {

//        Scanner scanner = new Scanner(System.in);
//        int port = scanner.nextInt();
        new GameServer(5000, 1000, 1);
    }
}
