package co.edu.utp.isc.progiv.p4.clase20.datos.dao;

import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Curso;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author DanielG
 */
public class CursoDao {

    private static CursoDao instancia;

    public static CursoDao getInstance() {
        if (instancia == null) {
            instancia = new CursoDao();
        }
        return instancia;
    }

    private final EntityManagerFactory emf;

    private CursoDao() {
        emf = Persistence.createEntityManagerFactory("clase20-pu");
    }

    public Curso guardar(String nombre, String nombreProfesor, String grupo, String creditos, String numeroAlumnos ) throws BaseDatosException {
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            Curso curso = new Curso();
            curso.setNombre(nombre);
            curso.setNombreProfesor(nombreProfesor);
            curso.setGrupo(grupo);
            curso.setCreditos(creditos);
            curso.setNumeroAlumnos(numeroAlumnos);


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

    public List<Curso> listar() throws BaseDatosException {
        var em = emf.createEntityManager();
        var query = em.createQuery("select e from Curso e", Curso.class);

        try {
            return query.getResultList();
        } catch (Exception ex) {
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public Curso consultar(Long id) throws BaseDatosException {
        var em = emf.createEntityManager();
        Curso curso = null;
        try {
            var query = em.createQuery("select e from Curso e where e.id = :id", Curso.class);
            query.setParameter("id", id);
            
            curso = query.getSingleResult();
        } catch (Exception ex) {
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
        return curso;
    }

    public void eliminar(Long id) throws BaseDatosException {
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            Curso curso = em.find(Curso.class, id);
            em.remove(curso);
            et.commit();

        } catch (Exception ex) {
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public Curso modificar(Long id, Integer option, String nuevaInfo) throws BaseDatosException {
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            Curso curso = em.find(Curso.class, id);

            if(option == 1){
                curso.setNombre(nuevaInfo);
            }
            if(option == 2){
                curso.setNombreProfesor(nuevaInfo);
            }
            if(option == 3){
                curso.setGrupo(nuevaInfo);
            }
            if(option == 4){
                curso.setCreditos(nuevaInfo);
            }
            if(option == 5){
                curso.setNumeroAlumnos(nuevaInfo);
            }

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
}
