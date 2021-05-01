/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.ui.consola;

import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Estudiante;
import co.edu.utp.isc.progiv.p4.clase20.ui.consola.utils.EntradaTecladoUtils;
import co.edu.utp.isc.progiv.p4.clase20.logica.UniversidadFacade;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.NoEncontradoException;

/**
 *
 * @author cdiaz
 */
public class CursosCLI {

    private static final UniversidadFacade facade;

    static {
        facade = new UniversidadFacade();
    }

    public static void menuCursos() {
        String opcion;
        do {
            System.out.println("\n\n");
            System.out.println("========================================================");
            System.out.println(" Gestión de Cursos");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Listado de cursos");
            //System.out.println("2. Consultar un estudiante por ID");
            System.out.println("3. Agregar un curso");
            System.out.println("0. Salir");
            System.out.println("========================================================");
//            opcion = EntradaTecladoUtils.obtenerOpcion("1,2,3,0".split(","));
            opcion = EntradaTecladoUtils.obtenerOpcion("1,3,0".split(","));
            switch (opcion) {
                case "1":
                    listarCursos();
                    break;
                case "2":
                    consultarEstudiante();
                    break;
                case "3":
                    agregarCursos();
                    break;
            }
        } while (!opcion.equals("0"));
        System.out.println("Hasta luego!");
    }

    private static void listarCursos() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Listado de cursos");
        System.out.println("========================================================");
        try {
            facade.listarCursos()
                    .forEach(curso -> {
                        System.out.println(curso.toString());
                    });
        } catch (BaseDatosException ex) {
            System.err.println("Error con base de datos: " + ex.getMessage());
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }

        EntradaTecladoUtils.presionaParaContinuar();
    }
   
    private static void consultarEstudiante() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Consultar estudiante");
        System.out.println("========================================================");
        String valor;
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la identificación del estudiante: ");

            if (valor == null
                    || valor.trim().isBlank()
                    || !valor.matches("[0-9]+")) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);

        var id = Long.valueOf(valor);
        try {
            System.out.println(facade.consultarEstudiante(id));
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }

    private static void agregarCursos() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Agregar un curso");
        System.out.println("========================================================");
        String codigo = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el código:");
        String nombre = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre:");
        String semestre = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el número de semestre:");

        try {
            var curso = facade.guardarCurso(codigo, nombre, Integer.valueOf(semestre));
            System.out.println("Se ha creado el curso:\n" + curso);
        } catch (BaseDatosException ex) {
            System.err.println("Error al guardar el curso: " + ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }

}
