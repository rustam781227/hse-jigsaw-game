package com.example.salpagarov_rustam_201_hw5;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.*;

public class Middleman {
    protected static Socket socket;
    private static ObjectInputStream in;
    protected static String counter;
    public static void getConnection(String server, String port, String userName) throws IOException {
            try {
                SocketAddress socketAddress = new InetSocketAddress(server, Integer.parseInt(port));
                socket = new Socket();
                socket.connect(socketAddress);
                System.out.println("connected");
            } catch (IOException e) {
                return;
            }
            in = new ObjectInputStream(socket.getInputStream());
    }

    public static int[][][] getFigureFromServer() throws IOException, ClassNotFoundException {
        return (int[][][]) in.readObject();
    }
}
