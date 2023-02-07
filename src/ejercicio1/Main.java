package ejercicio1;

public class Main {
    public static void main(String[] args) {
        // ^ Definimos dos variables booleanas que controlaran el flujo del Juego, si quiere repetir o si quiere jugar de nuevo
        boolean haGanado, sigueJugando = true;

        String movement;

        System.out.println("Bienvenido al Juego del Buscaminas");
        System.out.println("Los Controles son:");
        System.out.println("'a' para mover el cursor a la Izquierda");
        System.out.println("'d' para mover el cursor a la Derecha");
        System.out.println("'x' para destapar la casilla");
        System.out.println("\nPresiona 'Enter' para comenzar a jugar");
        Scan.read.nextLine();

        do {
            haGanado = false; // * Cada vez que vuelva a jugar diremos que haGanado es falso
            Game.generateBoard(); // * Generamos el Tablero
            do {
                do {
                    Game.printGame();
                    System.out.print(">>> ");
                    movement = Scan.read.nextLine();
                    movement = Game.moveCursor(movement);
                } while (!movement.equals("x"));
            } while (!haGanado);
        } while (sigueJugando);
    }
}
