package ejercicio1;

import java.util.Arrays;

import funciones.Mathematics;
import funciones.PerArrays;

/**
 * Clase que controla el flujo del Juego
 * @author Antonio Navaro
 * @version Release 1.2
 * @see PerArrays
 * @see Mathematics
 */
public class Game {
    /**
     * Variable de Desarrollo, imprime el tablero entero arriba del que tu estes jugando
     */
    private static final boolean DEV = false; // ? Cambia esta variable a true para tener Hacks al Jugar

    /**
     * Número de Minas que tendrá el Tablero, si es menor a 0 o menor no se generarán minas
     */
    private static final int NUMERO_MINAS = 6;
    
    /**
     * Tablero que solo verá la máquina, contiene las minas y los números
     */
    public static String tablero[] = new String[20];

    /**
     * Tablero del Jugador, podrá colocar banderines y destapar casillas
     */
    public static String tableroJugador[] = new String[tablero.length];

    /**
     * Posición relativa del cursor, este cursor "se mueve por el String del Array" y no por las posiciones del array
     */
    public static int cursor = 1;

    /**
     * Posición absoluta del cursor, esta posición dice la posición real que estas apuntando en el array
     */
    public static int cursorPos = 0;

    /**
     * Variable que controla cuantas casillas has destapado, cuando llegue a tablero.lenght - NUMERO_MINAS habrás ganado
     */
    public static int casillasDestapadas = 0;

    /**
     * Variable que controla las minas que te quedan por tapar (Es opcional tapar las minas)
     */
    public static int minasRestantes = NUMERO_MINAS;

    /**
     * Variable que contiene el tablero convertido a
     */
    public static String tableroString;

    /**
     * Función que genera el tablero con las posiciones de las minas y los números correspondientes, también hace la función de reset() cada vez que empieces una nueva partida
     */
    public static void generateBoard() {
        // Reseteamos las variables, nuevas partidas
        cursor = 1;
        cursorPos = 0;
        minasRestantes = NUMERO_MINAS;
        Main.juego = true;
        Main.haGanado = false;
        casillasDestapadas = 0;

        // Rellenamos los dos arrays con "?"
        PerArrays.fillArray(tablero, "?");
        PerArrays.fillArray(tableroJugador, "?");

        int posicionesOcupadas[] = new int[0];

        for (int i = 1; i <= NUMERO_MINAS; i++) {
            int posicionMina;

            do {
                // ! Generamos una posición para la mina
                posicionMina = Mathematics.rng(tablero.length - 1);
            } while (PerArrays.secuentialSearch(posicionesOcupadas, posicionMina) != -1);

            posicionesOcupadas = PerArrays.insertData(posicionesOcupadas, posicionMina);
        }

        for (int pos = 0; pos < posicionesOcupadas.length; pos++) {
            tablero[posicionesOcupadas[pos]] = "x";
        }

        for (int pos = 0; pos < tablero.length; pos++) {
            if (!tablero[pos].equals("x")) {
                tablero[pos] = checkMines(pos);
            }
        }

        tableroString = Arrays.toString(tablero);
    }

    /**
     * Función que genera el número que debe ir en una casilla sin mina
     * @param posActual Posición en el array
     * @return El número que tendrá que ser colocado en el array
     */
    public static String checkMines(int posActual) {
        int numCasilla = 0;
        int mode = 0;
        
        if (posActual == 0) {
            mode = 1;
        } else if (posActual == tablero.length - 1) {
            mode = 2;
        }

        switch (mode) {
            case 0:
                if (tablero[posActual - 1].equals("x")) {
                    numCasilla++;
                } if (tablero[posActual + 1].equals("x")) {
                    numCasilla++;
                }
                break;
            case 1:
                if (tablero[posActual + 1].equals("x")) {
                    numCasilla++;
                }
                break;
            case 2:
                if (tablero[posActual - 1].equals("x")) {
                    numCasilla++;
                }
                break;
            default:
                System.err.println("Error Fatal");
                System.exit(1);
                break;
        }

        return String.valueOf(numCasilla);
    }

    /**
     * Imprime el tablero, así como las minas restantes y el cursor
     */
    public static void printGame() {
        ConsoleManager.clear();
        System.out.printf("Minas Restantes: %s\n\n", minasRestantes);
        if (DEV) {
            System.out.printf("Casillas Destapadas: %s\n\n", casillasDestapadas);
            PerArrays.printArray(tablero);
        }
        System.out.print("[");
        for (int pos = 0; pos < tableroJugador.length; pos++) {
            switch (tableroJugador[pos]) {
                case "F":
                case "x":
                    System.out.print(ConsoleManager.RED);
                    break;
                case "0":
                    System.out.print(ConsoleManager.WHITE);
                    break;
                case "1":
                    System.out.print(ConsoleManager.BLUE);
                    break;
                case "2":
                    System.out.print(ConsoleManager.GREEN);
                    break;
                case "?":
                    System.out.print(ConsoleManager.YELLOW);
                    break;
                default:
                    System.err.println("Error Fatal");
                    System.exit(1);
                    break;
            }
            System.out.print(tableroJugador[pos]);
            System.out.print(ConsoleManager.RESET);
            if (pos != tableroJugador.length - 1) {
                System.out.print(", ");
            }
        } System.out.println("]");
        for (int pos = 0; pos < tableroString.length(); pos++) {
            if (pos == cursor) {
                System.out.printf("%s^", ConsoleManager.PURPLE);
            } else {
                System.out.print(" ");
            }
        } System.out.println(ConsoleManager.RESET);
    }

    /**
     * Igual que la anterior, solo que solo se usa al terminar el juego, de forma que imprime el juego completo
     */
    public static void printGameFull() {
        ConsoleManager.clear();
        if (DEV)
            PerArrays.printArray(tablero);
        System.out.print("[");
        for (int pos = 0; pos < tablero.length; pos++) {
            switch (tablero[pos]) {
                case "x":
                    System.out.print(ConsoleManager.RED);
                    break;
                case "0":
                    System.out.print(ConsoleManager.WHITE);
                    break;
                case "1":
                    System.out.print(ConsoleManager.BLUE);
                    break;
                case "2":
                    System.out.print(ConsoleManager.GREEN);
                    break;
                case "?":
                    System.out.print(ConsoleManager.YELLOW);
                    break;
                default:
                    System.err.println("Error Fatal");
                    System.exit(1);
                    break;
            }
            System.out.print(tablero[pos]);
            System.out.print(ConsoleManager.RESET);
            if (pos != tablero.length - 1) {
                System.out.print(", ");
            }
        } System.out.println("]");
        for (int pos = 0; pos < 60; pos++) {
            if (pos == cursor) {
                System.out.printf("%s^", ConsoleManager.PURPLE);
            } else {
                System.out.print(" ");
            }
        } System.out.println(ConsoleManager.RESET);
    }

    /**
     * Función que controla el movimiento del cursor, así como el sistema de banderas del juego
     */
    public static String moveCursor(String movement) {
        switch (movement) {
            case "x":
                break;
            case "a":
                cursor -= 3;
                cursorPos--;
                break;
            case "d":
                cursor += 3;
                cursorPos++;
                break;
            case "f":
                if (tableroJugador[cursorPos].equals("F")) {
                    tableroJugador[cursorPos] = "?";
                    minasRestantes++;
                } else {
                    tableroJugador[cursorPos] = "F";
                    minasRestantes--;
                } break;
            case "*":
                ayuda();
            default:
                movement = "?";
                break;
        }

        if (cursorPos < 0) {
            cursorPos = 0;
            cursor = 1;
        } else if (cursorPos > tablero.length - 1) {
            cursorPos = tablero.length - 1;
            cursor = tableroString.length() - 2;
        }

        return movement;
    }

    /**
     * Destapa la casilla del tableroJugador con la de tablero
     */
    public static void destapar() {
        int positive = cursorPos + 1, negative = cursorPos - 1;

        if (tablero[cursorPos].equals("x")) {
            Main.juego = false;
            tableroJugador[cursorPos] = tablero[cursorPos];
        } else if (tableroJugador[cursorPos].equals("?") && !tablero[cursorPos].equals("F")) {
            casillasDestapadas++;
            tableroJugador[cursorPos] = tablero[cursorPos];
            
        }

        while (positive < tablero.length && !tablero[positive].equals("x") && !tableroJugador[positive].equals("F")) {
            tableroJugador[positive] = tablero[positive];
            positive++;
            casillasDestapadas++;
        }

        while (negative >= 0 && !tablero[negative].equals("x") && !tableroJugador[negative].equals("F") && !tablero[negative].equals("F")) {
            tableroJugador[negative] = tablero[negative];
            negative--;
            casillasDestapadas++;
        }
    }

    /**
     * Comprueba si las casillasDestapadas es igual a tablero.lenght - NUMERO_MINAS
     */
    public static void comprobar() {
        if (casillasDestapadas == tablero.length - NUMERO_MINAS) {
            Main.haGanado = true;
            Main.juego = false;
        }
    }

    public static void ayuda() {
        try {
            ConsoleManager.clear();
            PerArrays.printArray(tablero);
            Thread.sleep(500);
        } catch (InterruptedException exception) {
            System.err.println(exception);
        }
    }
}
