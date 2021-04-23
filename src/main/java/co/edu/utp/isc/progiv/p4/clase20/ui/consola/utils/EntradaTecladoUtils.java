/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.ui.consola.utils;

import java.util.Scanner;

/**
 *
 * @author cdiaz
 */
public class EntradaTecladoUtils {

    private static Scanner entrada;

    static {
        entrada = new Scanner(System.in);
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
