package ejercicio2;

public class Main {
    // ^ Definimos dos variables que contendran los nombres de los Jugadores
    public static final String NOMBRE_JUGADOR_1 = "JUGADOR 1";
    public static final String NOMBRE_JUGADOR_2 = "JUGADOR 2";

    public static void main(String[] args) {
        // ^ Definimos dos variables de control, movimiento que servirá para ver si ha sacado carta o no y seHaPasado que indicará si se haPasado o no
        boolean movimiento, seHaPasado;

        // ! Presentamos el Juego al Usuario
        System.out.println("Bienvenidos al juego de las 7 y Media");
        System.out.println("El primer jugador podrá sacar cartas hasta que se detenga al igual que el jugador 2, gana quien mas puntos tenga");
        System.out.println("Si se pasa de 7.5 puntos se pierde");
        System.out.println("Presiona Enter para continuar...");
        Scan.read.nextLine();

        // ? Limpiamos la Información de La Consola
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        do { // ! Mediante un Bucle Do-While vamos a pedir cartas al Jugador1 hasta que deje de pedirlas o se pase
            movimiento = Game.menu(NOMBRE_JUGADOR_1);
            if (movimiento) { // ! Solo sacaremos carta en caso de que el jugador lo haya querido
                Game.sacaCarta(NOMBRE_JUGADOR_1);
            } seHaPasado = Game.check(Game.mazoJugador1);
        } while (movimiento && !seHaPasado);

        // ! Si el Jugador se ha pasado lo indicaremos en la consola
        if (seHaPasado) {
            System.out.printf("Te has pasado %s\n", NOMBRE_JUGADOR_1);
            seHaPasado = false;
        }
        
        // ? Salto de Línea Triple
        System.out.println("\n--------------------------------------------\n");
        
        do { // ! Hacemos lo mismo
            movimiento = Game.menu(NOMBRE_JUGADOR_2);
            if (movimiento) {
                Game.sacaCarta(NOMBRE_JUGADOR_2);
            } seHaPasado = Game.check(Game.mazoJugador2);
        } while (movimiento && !seHaPasado);
        if (seHaPasado) {
            System.out.printf("Te has pasado %s\n\n", NOMBRE_JUGADOR_2);
        }

        // ! Llamamos a la función que imprime el resultado
        Game.ganador(NOMBRE_JUGADOR_1, NOMBRE_JUGADOR_2, seHaPasado);
    }
}
