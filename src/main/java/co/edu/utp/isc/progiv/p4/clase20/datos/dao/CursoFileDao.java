/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.datos.dao;

import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Curso;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author cdiaz
 */
public class CursoFileDao extends CursoDao {

    private final String FILE_NAME = "./Curso.txt";

    @Override
    public List<Curso> listar() throws BaseDatosException {
        var respuesta = new ArrayList<Curso>();
        try (var br = new BufferedReader(new FileReader(FILE_NAME))) {
            br.lines()
                    .map(cadena -> cadena.split(";"))
                    .forEach(datos -> {
                        var curso = new Curso();
                        curso.setCodigo(datos[0]);
                        curso.setNombre(datos[1]);
                        curso.setSemestre(Integer.valueOf(datos[2]));
                        respuesta.add(curso);
                    });
        } catch (IOException ex) {
            throw new BaseDatosException("Error al listar: " + ex.getMessage());
        }
        return respuesta;
    }

    @Override
    public Curso agregar(String codigo, String nombre, Integer semestre) throws BaseDatosException {
        var curso = new Curso();
        try (var pw = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            pw.printf("%s;%s;%d%n", codigo, nombre, semestre);

            curso.setCodigo(codigo);
            curso.setNombre(nombre);
            curso.setSemestre(semestre);
        } catch (IOException ex) {
            throw new BaseDatosException("Error al guardar: " + ex.getMessage());
        }

        return curso;
    }

    @Override
    public Curso consultar(String codigo) throws BaseDatosException {
        Curso respuesta = null;
        try (var br = new BufferedReader(new FileReader(FILE_NAME))) {
            respuesta = br.lines()
                    .map(cadena -> cadena.split(";"))
                    .filter(datos -> datos[0].equals(codigo))
                    .map(datos -> {
                        var curso = new Curso();
                        curso.setCodigo(datos[0]);
                        curso.setNombre(datos[1]);
                        curso.setSemestre(Integer.valueOf(datos[2]));
                        return curso;
                    })
                    .findAny()
                    .orElseThrow(() -> new BaseDatosException(""));
        } catch (IOException ex) {
            throw new BaseDatosException(ex.getMessage());
        }
        return respuesta;
    }

}
