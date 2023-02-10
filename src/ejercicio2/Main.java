package ejercicio2;

public class Main {
    public static final String NOMBRE_JUGADOR_1 = "JUGADOR 1";
    public static final String NOMBRE_JUGADOR_2 = "JUGADOR 2";

    public static void main(String[] args) {
        boolean movimiento, seHaPasado;

        System.out.println("Bienvenidos al juego de las 7 y Media");
        System.out.println("El primer jugador podrá sacar cartas hasta que se detenga al igual que el jugador 2, gana quien mas puntos tenga");
        System.out.println("Si se pasa de 7.5 puntos se pierde");
        System.out.println("Presiona Enter para continuar...");
        Scan.read.nextLine();

        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        do {
            movimiento = Game.menu(NOMBRE_JUGADOR_1);
            if (movimiento) {
                Game.sacaCarta(NOMBRE_JUGADOR_1);
            } seHaPasado = Game.check(Game.mazoJugador1);
        } while (movimiento && !seHaPasado);

        if (seHaPasado) {
            System.out.printf("Te has pasado %s\n", NOMBRE_JUGADOR_1);
        }
        
        // ? Salto de Línea Triple
        System.out.println("\n--------------------------------------------\n");
        
        if (!seHaPasado) {
            do {
                movimiento = Game.menu(NOMBRE_JUGADOR_2);
                if (movimiento) {
                    Game.sacaCarta(NOMBRE_JUGADOR_2);
                } seHaPasado = Game.check(Game.mazoJugador2);
            } while (movimiento && !seHaPasado);

            if (seHaPasado) {
                System.out.printf("Te has pasado %s\n\n", NOMBRE_JUGADOR_2);
            }
        }

        Game.ganador(NOMBRE_JUGADOR_1, NOMBRE_JUGADOR_2);

        Scan.read.nextLine();
    }
}
