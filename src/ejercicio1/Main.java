package ejercicio1;

// ? Importamos la Clase Scanner y Locale
import java.util.Locale;
import java.util.Scanner;
import funciones.ConsoleManager;

public class Main {
    // ^ Definimos una instancia de la clase Scanner como private static final de nombre read y cambiamos su formato a US
    private static final Scanner read = new Scanner(System.in).useLocale(Locale.US);

    // ^ Definimos las variables booleanas que controlaran el flujo del Juego, si quiere repetir o si quiere jugar de nuevo
    public static boolean juego, haGanado, sigueJugando = true;

    public static void main(String[] args) {
        String movement;

        System.out.printf("Bienvenido al %sJuego%s del %sBuscaminas%s\n", ConsoleManager.GREEN, ConsoleManager.RESET, ConsoleManager.RED, ConsoleManager.RESET);
        System.out.printf("Los %sControles%s son:\n", ConsoleManager.YELLOW, ConsoleManager.RESET);
        System.out.printf("'%sa%s' para mover el cursor a la %sIzquierda%s\n", ConsoleManager.GREEN, ConsoleManager.RESET, ConsoleManager.GREEN, ConsoleManager.RESET);
        System.out.printf("'%sd%s' para mover el cursor a la %sDerecha%s\n", ConsoleManager.GREEN, ConsoleManager.RESET, ConsoleManager.GREEN, ConsoleManager.RESET);System.out.printf("'%sf%s' para %sMarcar%s la casilla\n", ConsoleManager.GREEN, ConsoleManager.RESET, ConsoleManager.GREEN, ConsoleManager.RESET);
        System.out.printf("'%sx%s' para %sDestapar%s la casilla\n", ConsoleManager.GREEN, ConsoleManager.RESET, ConsoleManager.GREEN, ConsoleManager.RESET);
        System.out.printf("Presiona %sEnter%s para comenzar... ", ConsoleManager.YELLOW, ConsoleManager.RESET);
        read.nextLine();
        do {
            Game.generateBoard(); // * Generamos el Tablero
            do {
                do {
                    Game.printGame();
                    System.out.print(">>> ");
                    movement = read.nextLine();
                    movement = Game.moveCursor(movement);
                } while (!movement.equals("x"));
                Game.destapar();
                Game.comprobar();
            } while (juego);
            ConsoleManager.clear();
            Game.printGameFull();
            if (haGanado) {
                System.out.printf("%sHas ganado!!!%s\n", ConsoleManager.GREEN, ConsoleManager.RESET);
            } else {
                System.out.printf("%sHas perdido!!!%s\n", ConsoleManager.RED, ConsoleManager.RESET);
            } System.out.printf("\nJugar otra vez? %sY%s/%sN%s\n", ConsoleManager.GREEN, ConsoleManager.RESET, ConsoleManager.RED, ConsoleManager.RESET);
            System.out.print(">>> ");
            movement = read.next();
            switch (movement) {
                case "n":
                case "N":
                case "No":
                case "NO":
                case "no":
                    sigueJugando = false;
                    break;
                default:
                    System.out.printf("\nComenzando nueva partida en:\n");
                    for (int i = 3; i > 0; i--) {
                        System.out.printf("%s.\n", i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException exception) {
                            System.out.printf("Error: %s\n", exception);
                        }
                    } break;
            }
        } while (sigueJugando);
    }
}
