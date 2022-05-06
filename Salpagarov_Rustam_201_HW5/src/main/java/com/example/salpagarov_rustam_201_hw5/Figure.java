package com.example.salpagarov_rustam_201_hw5;

import javafx.scene.paint.Color;

import java.util.Random;

public class Figure {
    public static int[][] getRandomFigure() {
        int rand = new Random().nextInt(0, 31);
        return fig[rand];
    }

    public static int getRandomColor() {
        return new Random().nextInt(0, 8);
    }

    static Color[] colors = {
            Color.BLUE,
            Color.CORAL,
            Color.CYAN,
            Color.GREEN,
            Color.SALMON,
            Color.YELLOW,
            Color.BROWN,
            Color.SKYBLUE};


    static int[][][] fig = {
            {
                    {0, 1, 0},
                    {0, 1, 0},
                    {1, 1, 1}
            },

            {
                    {1, 1, 1},
                    {0, 0, 1},
                    {0, 0, 1}
            },

            {
                    {0, 0, 1},
                    {1, 1, 1},
                    {0, 0, 1}
            },

            {
                    {1, 1, 0},
                    {0, 1, 1}
            },

            {
                    {1, 0},
                    {1, 1},
                    {1, 0}
            },

            {
                    {1, 0},
                    {1, 1}
            },

            {
                    {1, 1, 1},
                    {0, 1, 0}
            },

            {
                    {1, 1, 1},
                    {0, 1, 0},
                    {0, 1, 0}
            },

            {
                    {1, 0, 0},
                    {1, 1, 1},
                    {1, 0, 0}
            },

            {
                    {1},
                    {1},
                    {1}
            },

            {
                    {1, 1, 1}
            },

            {
                    {0, 0, 1},
                    {0, 0, 1},
                    {1, 1, 1}
            },

            {
                    {0, 1},
                    {1, 1},
                    {1, 0}
            },

            {
                    {1, 0, 0},
                    {1, 0, 0},
                    {1, 1, 1}
            },

            {
                    {1, 1, 1},
                    {1, 0, 0},
                    {1, 0, 0}
            },

            {
                    {0, 1},
                    {1, 1}
            },

            {
                    {1}
            },

            {
                    {1, 1},
                    {0, 1}
            },

            {
                    {1, 1},
                    {1, 0}
            },

            {
                    {1, 0},
                    {1, 0},
                    {1, 1}
            },

            {
                    {1, 0},
                    {1, 1},
                    {0, 1}
            },

            {
                    {0, 1, 1},
                    {1, 1, 0}
            },

            {
                    {0, 1, 0},
                    {1, 1, 1}
            },

            {
                    {0, 1},
                    {1, 1},
                    {0, 1}
            },

            {
                    {1, 1},
                    {1, 0},
                    {1, 0}
            },

            {
                    {0, 1},
                    {0, 1},
                    {1, 1}
            },

            {
                    {1, 0, 0},
                    {1, 1, 1}
            },

            {
                    {0, 0, 1},
                    {1, 1, 1}
            },

            {
                    {1, 1, 1},
                    {0, 0, 1}
            },

            {
                    {1, 1},
                    {0, 1},
                    {0, 1}
            },

            {
                    {1, 1, 1},
                    {1, 0, 0}
            },
    };
}
