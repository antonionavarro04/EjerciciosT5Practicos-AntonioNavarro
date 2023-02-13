package twodminer;

import funciones.PerArrays;
import funciones.Mathematics;
import funciones.ConsoleManager;

public class Game {
    public static final int X_WIDTH = 10;
    public static final int Y_WIDTH = 10;

    public static final int NUMERO_MINAS = 10;

    public static char tablero[][] = new char[Y_WIDTH][X_WIDTH];
    public static char tableroJugador[][] = new char[tablero.length][tablero[0].length];

    public static final char SIMBOLO_BOMBA = 'x';

    public static void generateBoard() {
        /* ! RESETS ! */
        PerArrays.fillArray(tablero, '?');
        PerArrays.fillArray(tableroJugador, '?');
        /* ! RESETS ! */

        int posicionesY[] = new int[0];
        int posicionesX[] = new int[0];

        // ! Generación de Posiciones de Minas
        for (int num = 1; num <= NUMERO_MINAS; num++) {
            int posicionY;
            int posicionX;

            // TODO | Hacer que las minas no se solapen de casualidad
            posicionY = Mathematics.rng(tablero.length);
            posicionX = Mathematics.rng(tablero.length);
                
            posicionesY = PerArrays.insertData(posicionesY, posicionY);
            posicionesX = PerArrays.insertData(posicionesX, posicionX);
        }

        PerArrays.printArray(posicionesY);
        PerArrays.printArray(posicionesX);

        // ! Colocación de Minas en el Tablero
        for (int pos = 0; pos < posicionesY.length; pos++) { // ? POSICIONES
            for (int filas = 0; filas < tablero.length; filas++) { // ? FILAS
                for (int columnas = 0; columnas < tablero[0].length; columnas++) { // ? COLUMNAS
                    if (posicionesY[pos] == filas && posicionesX[pos] == columnas) {
                        tablero[filas][columnas] = SIMBOLO_BOMBA;
                    }
                }
            }
        }

        PerArrays.printArray(tablero);
    }

    public static void printGame() {
        for (int filas = 0; filas < tablero.length; filas++) {
            for (int columnas = 0; columnas < tablero[0].length; columnas++) {
                switch (tableroJugador[filas][columnas]) { // ! Color Manager
                    case 'F':
                    case 'x':
                        System.out.print(ConsoleManager.RED);
                        break;
                    case '0':
                        System.out.print(ConsoleManager.WHITE);
                        break;
                    case '1':
                        System.out.print(ConsoleManager.BLUE);
                        break;
                    case '2':
                        System.out.print(ConsoleManager.GREEN);
                        break;
                    case '?':
                        System.out.print(ConsoleManager.YELLOW);
                        break;
                    default:
                        System.err.println("Error Fatal");
                        System.exit(1);
                        break;
                }
            }
        }
    }
}
