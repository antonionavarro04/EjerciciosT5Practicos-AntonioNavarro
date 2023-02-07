package ejercicio1;

public class Game {
    private static final int NUMERO_MINAS = 6;
    
    public static String tablero[] = new String[20];
    public static String tableroJugador[] = new String[20];

    public static int cursor = 1;
    public static int cursorPos = 0;

    public static void generateBoard() {
        // Reseteamos el Cursor, nuevas partidas
        cursor = 1;
        cursorPos = 0;

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
    }

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

    public static void printGame() {
        ConsoleManager.clear();
        System.out.print("[");
        for (int pos = 0; pos < tableroJugador.length; pos++) {
            switch (tableroJugador[pos]) {
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
        for (int pos = 0; pos < 60; pos++) {
            if (pos == cursor) {
                System.out.printf("%s^", ConsoleManager.PURPLE);
            } else {
                System.out.print(" ");
            }
        } System.out.println(ConsoleManager.RESET);
    }

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
            default:
                movement = "?";
                break;
        } return movement;
    }

    public static void reset() {
        cursor = 1;

    }
}
