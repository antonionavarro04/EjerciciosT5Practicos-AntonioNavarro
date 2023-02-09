package ejercicio2;

public class Main {
    public static final String nombreJugador1 = "JUGADOR 1";
    public static final String nombreJugador2 = "JUGADOR 2";

    public static void main(String[] args) {
        boolean movimiento;
        /* INTRO */
        do {
            movimiento = Game.menu(nombreJugador1);
            if (movimiento) {
                Game.sacaCarta(nombreJugador1);
            }
        } while (movimiento);
        
        // ? Salto de Línea Triple
        System.out.println("\n-------------------------------------------- \n");
        
        do {
            movimiento = Game.menu(nombreJugador2);
            if (movimiento) {
                Game.sacaCarta(nombreJugador2);
            }
        } while (movimiento);

        Scan.read.nextLine();
    }
}
