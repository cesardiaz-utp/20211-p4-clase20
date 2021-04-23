/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.logica;

import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.NoEncontradoException;
import co.edu.utp.isc.progiv.p4.clase20.datos.dao.EstudianteDao;
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

    public UniversidadFacade() {
        estudianteDao = EstudianteDao.getInstance();
    }

    public List<Estudiante> listarEstudiantes() throws BaseDatosException, NoEncontradoException {
        var listado = estudianteDao.listar();

        if (listado == null || listado.isEmpty()) {
            throw new NoEncontradoException("No existen estudiates en la base de datos");
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

    public Estudiante guardarEstudiante(String nombre, String apellido, String telefono) throws BaseDatosException {
        return estudianteDao.guardar(nombre, apellido, telefono);
    }

}
