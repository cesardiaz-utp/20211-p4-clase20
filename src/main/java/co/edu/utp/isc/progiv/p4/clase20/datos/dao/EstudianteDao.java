/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.datos.dao;

import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Estudiante;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author cdiaz
 */
public class EstudianteDao {

    private static EstudianteDao instancia;

    public static EstudianteDao getInstance() {
        if (instancia == null) {
            instancia = new EstudianteDao();
        }
        return instancia;
    }

    private final EntityManagerFactory emf;

    private EstudianteDao() {
        emf = Persistence.createEntityManagerFactory("clase20-pu");
    }

    public Estudiante guardar(String nombres, String apellidos, String telefono) throws BaseDatosException {
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            Estudiante estudiante = new Estudiante();
            estudiante.setNombres(nombres);
            estudiante.setApellidos(apellidos);
            estudiante.setTelefono(telefono);

            em.persist(estudiante);
            et.commit();

            return estudiante;
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Estudiante> listar() throws BaseDatosException {
        var em = emf.createEntityManager();
        var query = em.createQuery("select e from Estudiante e", Estudiante.class);

        try {
            return query.getResultList();
        } catch (Exception ex) {
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public Estudiante consultar(Long id) throws BaseDatosException {
        var em = emf.createEntityManager();
        Estudiante estudiante = null;
        try {
            var query = em.createQuery("select e from Estudiante e where e.id = :id", Estudiante.class);
            query.setParameter("id", id);
            
            estudiante = query.getSingleResult();
        } catch (Exception ex) {
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
        return estudiante;
    }
}
