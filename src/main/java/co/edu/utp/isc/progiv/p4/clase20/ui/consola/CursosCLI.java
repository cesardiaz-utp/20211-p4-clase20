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
            System.out.println("2. Consultar un curso por código");
            System.out.println("3. Agregar un curso");
            System.out.println("0. Salir");
            System.out.println("========================================================");
            opcion = EntradaTecladoUtils.obtenerOpcion("1,2,3,0".split(","));
            switch (opcion) {
                case "1":
                    listarCursos();
                    break;
                case "2":
                    consultarCurso();
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
   
    private static void consultarCurso() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Consultar curso");
        System.out.println("========================================================");
        String codigo;
        do {
            codigo = EntradaTecladoUtils.obtenerCadena("Ingrese el código del curso: ");

            if (codigo == null
                    || codigo.trim().isBlank()
                    || !codigo.matches("[A-Z][A-Z0-9]+")) {
                System.err.println("Debe ingresar un código válido");
                codigo = null;
            }
        } while (codigo == null);

        try {
            System.out.println(facade.consultarCurso(codigo));
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
