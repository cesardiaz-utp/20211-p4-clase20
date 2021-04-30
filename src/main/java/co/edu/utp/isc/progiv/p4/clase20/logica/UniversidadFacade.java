/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.logica;

import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.NoEncontradoException;
import co.edu.utp.isc.progiv.p4.clase20.datos.dao.CursoDao;
import co.edu.utp.isc.progiv.p4.clase20.datos.dao.EstudianteDao;
import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Curso;
import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Estudiante;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdiaz
 */
public class UniversidadFacade {

    private final EstudianteDao estudianteDao;
    private final CursoDao cursoDao;

    public UniversidadFacade() {
        estudianteDao = EstudianteDao.getInstance();
        cursoDao = CursoDao.getInstance();
    }

    public List<Estudiante> listarEstudiantes() throws BaseDatosException, NoEncontradoException {
        var listado = estudianteDao.listar();

        if (listado == null || listado.isEmpty()) {
            throw new NoEncontradoException("No existen estudiates en la base de datos");
        }

        return listado;
    }

    public List<Curso> listarCursos() throws BaseDatosException, NoEncontradoException {
        var listado = cursoDao.listar();

        if (listado == null || listado.isEmpty()) {
            throw new NoEncontradoException("No existen cursos en la base de datos");
        }

        return listado;
    }

    public Estudiante consultarEstudiante(Long id) throws NoEncontradoException {
        try {
            return estudianteDao.consultar(id);
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el estudiante con identificacion " + id);
        }
    }

    public Curso consultarCurso(Long id) throws NoEncontradoException {
        try {
            return cursoDao.consultar(id);
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el curso con identificacion " + id);
        }
    }

    public void eliminarEstudiante(Long id) throws NoEncontradoException {
        try {
            estudianteDao.eliminar(id);
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el estudiante con identificacion " + id);
        }
    }

    public void eliminarCurso(Long id) throws NoEncontradoException {
        try {
            cursoDao.eliminar(id);
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el curso con identificacion " + id);
        }
    }

    public Estudiante guardarEstudiante(String nombre, String apellido, String telefono) throws BaseDatosException {
        return estudianteDao.guardar(nombre, apellido, telefono);
    }

    public Curso guardarCurso(String nombre, String nombreProfesor, String grupo, String creditos, String numeroAlumnos) throws BaseDatosException {
        return cursoDao.guardar(nombre, nombreProfesor, grupo, creditos, numeroAlumnos);
    }

    public Estudiante modificarEstudiante(Long id, Integer option, String nuevaInfo) throws NoEncontradoException{
        try {
            return estudianteDao.modificar(id, option, nuevaInfo);
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el estudiante con identificacion " + id);
        }
    }

    public Curso modificarCurso(Long id, Integer option, String nuevaInfo) throws NoEncontradoException{
        try {
            return cursoDao.modificar(id, option, nuevaInfo);
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el curso con identificacion " + id);
        }
    }

}
