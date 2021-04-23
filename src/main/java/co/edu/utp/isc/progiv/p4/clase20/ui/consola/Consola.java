/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.ui.consola;

import co.edu.utp.isc.progiv.p4.clase20.ui.consola.utils.EntradaTecladoUtils;

/**
 *
 * @author cdiaz
 */
public class Consola {

    public static void iniciar() {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        String opcion;
        do {
            System.out.println("\n\n");
            System.out.println("========================================================");
            System.out.println(" Bienvenidos a la Gestion Universitaria");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Gestion de estudiantes");
            System.out.println("0. Salir");
            System.out.println("========================================================");
            opcion = EntradaTecladoUtils.obtenerOpcion("1,0".split(","));
            switch (opcion) {
                case "1":
                    EstudiantesCLI.menuEstudiantes();
                    break;
            }
        } while (!opcion.equals("0"));

        System.out.println("Gracias por usar nuestra aplicación");
    }

}
