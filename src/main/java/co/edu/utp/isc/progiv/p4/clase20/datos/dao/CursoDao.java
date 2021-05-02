/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.datos.dao;

import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Curso;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import java.util.List;

/**
 *
 * @author cdiaz
 */
public abstract class CursoDao {

    public static CursoDao getInstance(Boolean desdeArchivo) {
        if (desdeArchivo) {
            return new CursoFileDao();
        } else {
            return new CursoDBDao();
        }
    }

    public abstract List<Curso> listar() throws BaseDatosException;

    public abstract Curso consultar(String codigo) throws BaseDatosException;

    public abstract Curso agregar(String codigo, String nombre, Integer semestre) throws BaseDatosException;

}
