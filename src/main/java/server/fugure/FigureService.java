package server.fugure;

import server.fugure.Figure;

public class FigureService {

    private static int[][][] FIGURES;

    public static int[][][] get(){
        if (FIGURES == null){
            FIGURES = new int[81][][];
            for (int i = 0; i < 81; ++i) {
                FIGURES[i] = Figure.getRandomFigure();
            }
            return FIGURES;
        }
        return FIGURES;
    }

}
