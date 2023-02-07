package ejercicio1;

public class Main {
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
        Scan.read.nextLine();
        do {
            Game.generateBoard(); // * Generamos el Tablero
            do {
                do {
                    Game.printGame();
                    System.out.print(">>> ");
                    movement = Scan.read.nextLine();
                    movement = Game.moveCursor(movement);
                } while (!movement.equals("x"));
                Game.destapar();
                Game.comprobar();
            } while (juego);
            ConsoleManager.clear();
            if (haGanado) {
                Game.printGameFull();
                System.out.printf("%sHas ganado!!!%s\n", ConsoleManager.GREEN, ConsoleManager.RESET);
            } else {
                Game.printGame();
                System.out.printf("%sHas perdido!!!%s\n", ConsoleManager.RED, ConsoleManager.RESET);
            } System.out.printf("\nJugar otra vez? %sY%s/%sN%s\n", ConsoleManager.GREEN, ConsoleManager.RESET, ConsoleManager.RED, ConsoleManager.RESET);
            System.out.print(">>> ");
            movement = Scan.read.next();
            switch (movement) {
                case "n":
                case "N":
                case "No":
                case "NO":
                case "no":
                case "Nou":
                    sigueJugando = false;
                    break;
                default:
                    break;
            }
        } while (sigueJugando);
    }
}
