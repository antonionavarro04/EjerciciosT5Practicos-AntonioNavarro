package twodminer;

import java.util.Scanner;
import java.util.Locale;

public class Main {
    // ^ Definimos una instancia de la clase Scanner como private static final de nombre read y cambiamos su formato a US
    private static final Scanner read = new Scanner(System.in).useLocale(Locale.US);

    // ^ Variables booleanas de control
    public static boolean juego = true, haGanado = false, sigueJugando = true;

    public static void main(String[] args) {
        char movement;

        /* ? INTRO ? */

        do {
            Game.generateBoard();
            do {
                do {
                    Game.printGame(Game.tableroJugador);
                    System.out.print("\n>>> ");
                    movement = read.nextLine().charAt(0);
                    movement = Game.moveCursor(movement);
                } while (movement != 'x');
                Game.destapar();
            } while (juego);
            sigueJugando = false;
        } while (sigueJugando);
    }
}
