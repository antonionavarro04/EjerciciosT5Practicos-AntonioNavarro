package twodminer;

import java.util.Arrays;

import funciones.ConsoleManager;
import funciones.Mathematics;
import funciones.PerArrays;

public class Game {
    // TODO | Hacer que esto Se Pueda Configurar
    
    public static final int X_WIDTH = 30;
    public static final int Y_WIDTH = 16;

    public static final int NUMERO_MINAS = 99;

    public static char tablero[][] = new char[Y_WIDTH][X_WIDTH];
    public static char tableroJugador[][] = new char[tablero.length][tablero[0].length];

    public static final char SIMBOLO_BOMBA = 'x';

    public static final char SIMBOLO_CURSOR = '<';

    public static int pos_x = 0;
    public static int pos_y = 0;

    public static int minasRestantes = NUMERO_MINAS;
    public static int casillasDestapadas = 0;

    public static void generateBoard() {
        /* ! RESETS ! */
        PerArrays.fillArray(tablero, '?');
        PerArrays.fillArray(tableroJugador, '?');
        /* ! RESETS ! */

        int posicionesY[] = new int[0];
        int posicionesX[] = new int[0];

        // ! Generación de Posiciones de Minas
        for (int numMina = 1; numMina <= NUMERO_MINAS; numMina++) {
            boolean volverA;

            int posicionY;
            int posicionX;

            int comparar[] = new int[2];
            int compararArray[] = new int[2];

            do {
                volverA = false;

                posicionY = Mathematics.rng(tablero.length - 1);
                posicionX = Mathematics.rng(tablero[0].length - 1);

                comparar[0] = posicionY;
                comparar[1] = posicionX;

                for (int i = 0; i < posicionesX.length; i++) {
                    compararArray[0] = posicionesY[i];
                    compararArray[1] = posicionesX[i];

                    if (Arrays.compare(comparar, compararArray) == 0) {
                        volverA = true;
                        break;
                    }
                }
            } while (volverA);    
                
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

        // ! Colocación de Números en el Tablero
        for (int filas = 0; filas < tablero.length; filas++) { // ? FILAS
            for (int columnas = 0; columnas < tablero[0].length; columnas++) { // ? COLUMNAS
                if (tablero[filas][columnas] != SIMBOLO_BOMBA) {
                    tablero[filas][columnas] = checkMines(filas, columnas);
                }
            }
        }
    }

    public static char checkMines(int fila, int columna) {
        char numCasilla = '0';
        int mode = 0;
        
        /* ! Modos de Busqueda
         * 0 = Normal (ALL)
         * 1 = Esquina Superior Izquierda
         * 2 = Esquina Superior Derecha
         * 3 = Esquina Inferior Izquierda
         * 4 = Esquina Inferior Derecha
         * 5 = Lado Superior
         * 6 = Lado Derecho
         * 7 = Lado Inferior
         * 8 = Lado Izquierdo
         */

        if (fila == 0 && columna == 0) {
            mode = 1;
        } else if (fila == 0 && columna == tablero[0].length - 1) {
            mode = 2;
        } else if (fila == tablero.length - 1 && columna == 0) {
            mode = 3;
        } else if (fila == tablero.length - 1 && columna == tablero[0].length - 1) {
            mode = 4;
        } else if (fila == 0) {
            mode = 5;
        } else if (columna == tablero[0].length - 1) {
            mode = 6;
        } else if (fila == tablero.length - 1) {
            mode = 7;
        } else if (columna == 0) {
            mode = 8;
        } else {
            mode = 0;
        }

        switch (mode) {
            case 0:
                if (tablero[fila - 1][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila + 1][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila + 1][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila - 1][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila + 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila - 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                }
                break;
            case 1:
                if (tablero[fila][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila + 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila + 1][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } break;
            case 2:
                if (tablero[fila][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila + 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila + 1][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } break;
            case 3:
                if (tablero[fila - 1][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila - 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } break;
            case 4:
                if (tablero[fila][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila - 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila - 1][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } break;
            case 5:
                if (tablero[fila][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                }
                fila++; // Comprobamos las Filas de Abajo
                if (tablero[fila][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } break;
            case 6:
                if (tablero[fila - 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila + 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                }
                columna--; // Comprobamos la Columna de la Izquierda
                if (tablero[fila + 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila - 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } break;
            case 7:
                if (tablero[fila][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                }
                fila--; // Comprobamos las Filas de Arriba
                if (tablero[fila][columna + 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna - 1] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } break;
            case 8:
                if (tablero[fila - 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila + 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                }
                columna++; // Comprobamos las Columnas de la Derecha
                if (tablero[fila + 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } if (tablero[fila - 1][columna] == SIMBOLO_BOMBA) {
                    numCasilla++;
                } break;
            default:
                System.err.println("Error Fatal");
                break;
        } return numCasilla;
    }

    public static void printGame(char array[][]) {
        ConsoleManager.clear();
        System.out.printf("Minas Restantes: %s%s%s\n", ConsoleManager.RED, minasRestantes, ConsoleManager.RESET);
        for (int filas = 0; filas < array.length; filas++) {
            for (int columnas = 0; columnas < array[0].length; columnas++) {
                switch (array[filas][columnas]) { // ! Color Manager
                    case 'F':
                        System.out.print(ConsoleManager.RED_UNDERLINED);
                        break;
                    case 'x':
                        System.out.print(ConsoleManager.WHITE);
                        break;
                    case '3':
                        System.out.print(ConsoleManager.RED);
                        break;
                    case '0':
                        System.out.print(ConsoleManager.WHITE);
                        break;
                    case '1':
                        System.out.print(ConsoleManager.CYAN);
                        break;
                    case '2':
                        System.out.print(ConsoleManager.GREEN);
                        break;
                    case '4':
                        System.out.print(ConsoleManager.BLUE);
                        break;
                    case '5':
                        System.out.print(ConsoleManager.RED_BOLD);
                        break;
                    case '6':
                        System.out.print(ConsoleManager.CYAN_BOLD);
                        break;
                    case '7':
                        System.out.print(ConsoleManager.BLACK_BOLD);
                        break;
                    case '8':
                        System.out.print(ConsoleManager.BLACK_BRIGHT);
                        break;
                    case '?':
                        System.out.print(ConsoleManager.YELLOW);
                        break;
                    default:
                        System.out.print(ConsoleManager.PURPLE);
                        break;
                }

                if (filas == pos_y && columnas == pos_x) {
                    System.out.printf("%s%s%s%s", array[filas][columnas], ConsoleManager.PURPLE_BOLD, SIMBOLO_CURSOR, ConsoleManager.RESET);
                } else {
                    System.out.printf("%s%s ", array[filas][columnas], ConsoleManager.RESET);
                }
            }
            System.out.println(); // ? Cambiamos la Linea
        }
    }

    public static char moveCursor(char movement) {
        switch (movement) {
            case 'x':
                break;
            case 'a':
                pos_x--;
                break;
            case 'd':
                pos_x++;
                break;
            case 'w':
                pos_y--;
                break;
            case 's':
                pos_y++;
                break;
            case 'f':
                if (tableroJugador[pos_y][pos_x] == 'F') {
                    tableroJugador[pos_y][pos_x] = '?';
                    minasRestantes++;
                } else {
                    tableroJugador[pos_y][pos_x] = 'F';
                    minasRestantes--;
                } break;
            case '*':
                //ayuda();
            default:
                movement = '?';
                break;
        }

        // TODO | Arreglar Cursor Fuera de Límites
        /* if (cursorPos < 0) {
            cursorPos = 0;
            cursor = 1;
        } else if (cursorPos > tablero.length - 1) {
            cursorPos = tablero.length - 1;
            cursor = tableroString.length() - 2;
        } */

        return movement;
    }

    public static void destapar() {
        //int positive = cursorPos + 1, negative = cursorPos - 1;

        if (tablero[pos_y][pos_x] == 'x') {
            Main.juego = false;
            tableroJugador[pos_y][pos_x] = tablero[pos_y][pos_x];
        } else if (tableroJugador[pos_y][pos_x] == '?' && tableroJugador[pos_y][pos_x] != 'F') {
            casillasDestapadas++;
            tableroJugador[pos_y][pos_x] = tablero[pos_y][pos_x];
        }

        // TODO | Terminar Recursividad

        /* while (positive < tablero.length && !tablero[positive].equals("x") && !tableroJugador[positive].equals("F")) {
            tableroJugador[positive] = tablero[positive];
            positive++;
            casillasDestapadas++;
        }

        while (negative >= 0 && !tablero[negative].equals("x") && !tableroJugador[negative].equals("F") && !tablero[negative].equals("F")) {
            tableroJugador[negative] = tablero[negative];
            negative--;
            casillasDestapadas++;
        } */
    }
    // TODO | Comprobador
}
