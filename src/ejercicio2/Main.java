package ejercicio2;

public class Main {
    public static boolean sigueJugando = true;

    public static final String nombreJugador1 = "JUGADOR 1";
    public static final String nombreJugador2 = "JUGADOR 2";
    public static void main(String[] args) {
        boolean movimiento;
        /* INTRO */
        do {
            do {
                movimiento = Game.menu(nombreJugador1);
                if (movimiento) {
                    Game.sacaCarta(Game.mazoJugador1);
                }
            } while (!movimiento);
        } while (sigueJugando);
    }
}
