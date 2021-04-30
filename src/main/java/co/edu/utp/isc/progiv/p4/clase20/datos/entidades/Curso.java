package co.edu.utp.isc.progiv.p4.clase20.datos.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author DanielG
 */
@Entity
@Table(name = "Course")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "C_TITLLE")
    private String nombre;

    @Column(name = "P_NAME")
    private String nombreProfesor;

    @Column(name = "C_GROUPNAME")
    private String grupo;

    @Column(name = "C_CREDITS")
    private String creditos;

    @Column(name = "NO_STUDENTS")
    private String numeroAlumnos;

    public Curso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

        public String getNumeroAlumnos() {
        return numeroAlumnos;
    }

    public void setNumeroAlumnos(String numeroAlumnos) {
        this.numeroAlumnos = numeroAlumnos;
    }


    @Override
    public String toString() {
        return "Curso{" 
                + "\n\tid=" + id 
                + "\n, \tnombre=" + nombre 
                + "\n, \tnombre del maestro=" + nombreProfesor 
                + "\n, \tgrupo=" + grupo
                + "\n, \tcreditos= " + creditos
                + "\n, \tnumero de alumnos= " + numeroAlumnos
                + "\n}";
    }

}
