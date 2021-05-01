/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.datos.dao;

import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Curso;
import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Estudiante;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author cdiaz
 */
public class CursoDBDao extends CursoDao {

    private final EntityManagerFactory emf;

    public CursoDBDao() {
        emf = Persistence.createEntityManagerFactory("clase20-pu");
    }

    @Override
    public Curso agregar(String codigo, String nombre, Integer semestre) throws BaseDatosException {
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            Curso curso = new Curso();
            curso.setCodigo(codigo);
            curso.setNombre(nombre);
            curso.setSemestre(semestre);

            em.persist(curso);
            et.commit();

            return curso;
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Curso> listar() throws BaseDatosException {
        var em = emf.createEntityManager();
        var query = em.createQuery("select e from Curso e", Curso.class); // JPQL

        try {
            return query.getResultList();
        } catch (Exception ex) {
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
    }
}
