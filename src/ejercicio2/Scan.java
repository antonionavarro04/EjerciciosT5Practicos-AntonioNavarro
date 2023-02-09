package ejercicio2;

// ? Importamos la Clase Scanner y Locale
import java.util.Scanner;
import java.util.Locale;

public class Scan {
    // ^ Definimos una instancia de la clase Scanner como private static final de nombre read y cambiamos su formato a US
    public static final Scanner read = new Scanner(System.in).useLocale(Locale.US);
}
