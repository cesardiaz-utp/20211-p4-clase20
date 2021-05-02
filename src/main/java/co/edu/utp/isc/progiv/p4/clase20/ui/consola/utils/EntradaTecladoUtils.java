/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.ui.consola.utils;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author cdiaz
 */
public class EntradaTecladoUtils {

    private static final Scanner entrada;

    static {
        entrada = new Scanner(System.in);
    }

    public static void limpiarPantalla() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                //Runtime.getRuntime().exec("cls");
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                //Runtime.getRuntime().exec("clear");
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (final IOException | InterruptedException ex) {
            System.err.println("Error al limpiar pantalla: " + ex.getMessage());
        }

//        try {
//            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
////        System.out.print("\033[H\033[2J");
////        System.out.flush();
//        } catch (IOException | InterruptedException ex) {
//            System.err.println("" + ex.getMessage());
//        }
    }

    public static String obtenerCadena(String mensaje) {
        System.out.println(mensaje);
        return entrada.nextLine();
    }

    public static void presionaParaContinuar() {
        obtenerCadena("Presione ENTER para continuar: ");
    }

    public static String obtenerOpcion(String[] opciones) {
        String valor = null;
        do {
            var dato = obtenerCadena("Presione la opción elegida: ");
            for (String opcion : opciones) {
                if (dato.equalsIgnoreCase(opcion.trim())) {
                    valor = dato;
                    break;
                }
            }

            if (valor != null) {
                break;
            }

            System.out.println("La opcion elegida no es válida. Intenta de nuevo.");
        } while (valor == null);

        return valor;
    }

}
