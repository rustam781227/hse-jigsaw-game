package com.example.salpagarov_rustam_201_hw5;

public class Round {
    int width;
    int height;
    int[][] board;
    int numberOfMoves;
    String userName;
    /**
     * Constructor - begins new round
     *
     * @param width  - width of the game area
     * @param height - height of the game area
     */
    public Round(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
        numberOfMoves = 0;
    }

    /**
     * Marks the cell as a used one
     *
     * @param x     - x coordinate
     * @param y     - y coordinate
     * @param color - color from Figure. colors
     */
    public void setAlive(int x, int y, int color) {
        this.board[x][y] = color;
    }

    public void setDead(int x, int y) {
        this.board[x][y] = 0;
    }

    public int getState(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }
        if (y < 0 || y >= height) {
            return 0;
        }
        return this.board[x][y];
    }


}
