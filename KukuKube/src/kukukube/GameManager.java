package kukukube;

import java.util.Random;
import java.awt.Color;

/**
 * Maneja todo el juego, las variantes de los colores de los cubos, dimensiones
 * del grid y dificultad del juego.
 */
public class GameManager {

    public static int MAX_GRID_SIZE = 10;
    public static final int NUM_COLOR_COMPONENTS = 3;
    public static final int NUM_RGB_VALUES = 256;
    private static final int STARTING_SMOOTH_AMOUNT = 35;

    private int gridDimension;
    private int timesClicked;
    private int[] targetCubeCoordinates;
    private Random r;
    private int niveles;
    private int dificultad;

    // Normal y variante, colores que se usan.
    private Color normalColor;
    private Color variantColor;

    // Cantidad de cuanto difiere el color.
    private int smoothAmount;

    public GameManager() {
        r = new Random();
        gridDimension = 2;
        niveles = 1;
        targetCubeCoordinates = new int[2];
        smoothAmount = STARTING_SMOOTH_AMOUNT;
        asignarColorNuevo();

    }

    /**
     * Asigna colores nuevos. Primero, llega a un numero random. Después, el
     * colores variable se saca reduciendo un valor "smooth" de R,G y B del
     * color original.
     */
    public void asignarColorNuevo() {
        try {

            int[] actualRgb = new int[3];
            int[] changedRgb = new int[3];

            for (int i = 0; i < NUM_COLOR_COMPONENTS; i++) {
                actualRgb[i] = r.nextInt(NUM_RGB_VALUES);
                if (actualRgb[i] <= 50) {
                    actualRgb[i] += r.nextInt(220);
                }
                changedRgb[i] = actualRgb[i] - smoothAmount;
            }

            normalColor = new Color((int) actualRgb[0], (int) actualRgb[1], (int) actualRgb[2]);
            variantColor = new Color((int) changedRgb[0], (int) changedRgb[1], (int) changedRgb[2]);

        } catch (IllegalArgumentException ex) {
            asignarColorNuevo();
        }
    }

    public Color getNormalColor() {
        return normalColor;
    }

    public Color getVariantColor() {
        return variantColor;
    }

    /**
     * @returns Un elemeneto de array que devuelve las coordenadas del cubo
     * correcto.
     */
    public int[] getTargetCubeCoordinates() {
        return targetCubeCoordinates;
    }

    public int getGridDimension() {
        return gridDimension;
    }

    /**
     * Returns true si el juego ha acabado, false si no.
     */
    public boolean isGameOver() {
        return niveles >= MAX_GRID_SIZE;
    }

    /**
     * Método que actualiza el tamaño del grid y pone la dificultad de los
     * cubos.
     */
    public void updateBoardSize() {
        //parte que aumenta la dificultad 

        if (dificultad > 0 && dificultad <= 4) {
            smoothAmount = 35;
        } else if (dificultad >= 5 && dificultad <= 7) {
            smoothAmount = 17;
        } else if (dificultad >= 8 && dificultad <= 10) {
            smoothAmount = 3;
        }

        //aumenta el grid
        if (niveles == 1) {
            niveles++;
        } else {
            gridDimension++;
            niveles++;
        }

    }

    /**
     *
     * Getters y setters de los metodos.
     */
    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }

    public int getNiveles() {
        return niveles;
    }

    public void setGridDimension(int gridDimension) {
        this.gridDimension = gridDimension;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * Elige una X e una Y para el cubo correcto.
     */
    public void shuffleTargetCubeCoordinates() {
        targetCubeCoordinates[0] = r.nextInt(gridDimension);
        targetCubeCoordinates[1] = r.nextInt(gridDimension);
    }
}
