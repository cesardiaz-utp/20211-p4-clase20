/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.ui.gui.estudiante;

import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Estudiante;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cdiaz
 */
public class EstudianteTableModel extends DefaultTableModel {

    private final String[] columnas = {"Identificacion", "Nombre"};
    private List<Estudiante> datos;

    public EstudianteTableModel(List<Estudiante> datos) {
        this.datos = datos;
        super.setColumnIdentifiers(columnas);
    }

    public void setDatos(List<Estudiante> datos) {
        this.datos = datos;
        actualizarDatos();
    }

    public void actualizarDatos() {
        fireTableDataChanged();
    }

    public Estudiante getDato(int row) {
        return datos.get(row);
    }

    public void addDato(Estudiante dato) {
        datos.add(dato);
        actualizarDatos();
    }

    @Override
    public int getRowCount() {
        return datos == null ? 0 : datos.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        var estudiante = datos.get(row);
        switch (column) {
            case 0: // Identificacion
                return estudiante.getId();
            case 1: // Nombres
                return estudiante.getNombres() + " " + estudiante.getApellidos();
        }
        return super.getValueAt(row, column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0: // Identificacion
                return Long.class;
            case 1: // Nombre
                return String.class;
        }
        return super.getColumnClass(column);
    }
}
