package co.edu.utp.isc.progiv.p4.clase20.ui.consola;

import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Estudiante;
import co.edu.utp.isc.progiv.p4.clase20.ui.consola.utils.EntradaTecladoUtils;
import co.edu.utp.isc.progiv.p4.clase20.logica.UniversidadFacade;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.NoEncontradoException;

/**
 *
 * @author DanielG
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
            System.out.println("1. Listado de Cursos");
            System.out.println("2. Consultar un Curso por ID");
            System.out.println("3. Agregar un Curso");
            System.out.println("4. Modificar un Curso");
            System.out.println("5. Eliminar un Curso");
            System.out.println("0. Salir");
            System.out.println("========================================================");
            opcion = EntradaTecladoUtils.obtenerOpcion("1,2,3,4,5,0".split(","));
            switch (opcion) {
                case "1":
                    listarCursos();
                    break;
                case "2":
                    consultarCurso();
                    break;
                case "3":
                    agregarCurso();
                    break;
                case "4":
                    modificarCurso();
                    break;
                case "5":
                    eliminarCurso();
                    break;
            }
        } while (!opcion.equals("0"));
        System.out.println("Hasta luego!");
    }

    private static void listarCursos() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Listado de Cursos");
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
        String valor;
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la identificación del curso: ");

            if (valor == null
                    || valor.trim().isBlank()
                    || !valor.matches("[0-9]+")) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);

        var id = Long.valueOf(valor);
        try {
            System.out.println(facade.consultarCurso(id));
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }

    private static void eliminarCurso() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Eliminar curso");
        System.out.println("========================================================");
        String valor;
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la identificación del curso: ");

            if (valor == null
                    || valor.trim().isBlank()
                    || !valor.matches("[0-9]+")) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);

        var id = Long.valueOf(valor);
        try {
            facade.eliminarCurso(id);
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }

    private static void agregarCurso() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Agregar un curso");
        System.out.println("========================================================");
        String nombre = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre del curso:");
        String nombreProfesor = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre del profesor:");
        String grupo = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el número del grupo:");
        String creditos = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el número de creditos:");
        String numeroAlumnos = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el número de alumnos:");

        try {
            var curso = facade.guardarCurso(nombre, nombreProfesor, grupo, creditos, numeroAlumnos);
            System.out.println("Se ha creado el curso:\n" + curso);
        } catch (BaseDatosException ex) {
            System.err.println("Error al guardar el curso: " + ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }

    public static void modificarCurso() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Modificar curso");
        System.out.println("========================================================");
        String valor;
        String opcion;
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la identificación del curso: ");

            if (valor == null
                    || valor.trim().isBlank()
                    || !valor.matches("[0-9]+")) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);

        var id = Long.valueOf(valor);
        try {
            System.out.println(facade.consultarCurso(id));
            System.out.println("========================================================");
            System.out.println(" Opciones de Edición");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Editar Nombre del Curso");
            System.out.println("2. Editar Nombre del Profesor");
            System.out.println("3. Editar N° de Grupo");
            System.out.println("4. Editar N° de Creditos");
            System.out.println("5. Editar N° de Alumnos");
            System.out.println("========================================================");
            opcion = EntradaTecladoUtils.obtenerOpcion("1,2,3,4,5".split(","));
            switch (opcion) {
                case "1":
                    String nombre = EntradaTecladoUtils.obtenerCadena("Ingrese el nuevo nombre del curso");
                    try {
                        System.out.println(facade.modificarCurso(id, 1, nombre));
                    } catch (NoEncontradoException ex) {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case "2":
                    String nombreProfesor = EntradaTecladoUtils.obtenerCadena("Ingrese el nuevo nombre del profesor del curso");
                    try {
                        System.out.println( facade.modificarCurso(id, 2, nombreProfesor));
                    } catch (NoEncontradoException ex) {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case "3":
                    String grupo = EntradaTecladoUtils.obtenerCadena("Ingrese el nuevo número del grupo");
                    try {
                        System.out.println( facade.modificarCurso(id, 3, grupo));
                    } catch (NoEncontradoException ex) {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case "4":
                    String creditos = EntradaTecladoUtils.obtenerCadena("Ingrese el nuevo número de creditos");
                    try {
                        System.out.println( facade.modificarCurso(id, 4, creditos));
                    } catch (NoEncontradoException ex) {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case "5":
                    String numeroAlumnos = EntradaTecladoUtils.obtenerCadena("Ingrese el nuevo número de alumnos");
                    try {
                        System.out.println( facade.modificarCurso(id, 5, numeroAlumnos));
                    } catch (NoEncontradoException ex) {
                        System.err.println(ex.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opcion inválida");
            }
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }

}
