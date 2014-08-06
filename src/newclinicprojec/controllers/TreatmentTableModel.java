/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newclinicprojec.controllers;

import java.util.List;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import newclinicprojec.entities.Treatment;

/**
 *
 * @author Johm_93
 */
public class TreatmentTableModel extends AbstractTableModel {

    String[] columnName = { "TreatmentID", "CustomerID", "DoctorID", "Date", "TotalCost"};
    List<Treatment> treatment;

    public TreatmentTableModel(List<Treatment> treatments) {
        this.treatment = treatments;
        this.fireTableDataChanged();
    }

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

    public List<Treatment> getTreatment() {
        return treatment;
    }

    public void setTreatment(List<Treatment> treatment) {
        this.treatment = treatment;
        this.fireTableDataChanged();
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    @Override
    public int getRowCount() {
        return treatment.size();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return columnName.length;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int i) {
        return columnName[i];
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Treatment treat = treatment.get(i);
        switch (i1) {
            case 0:
                return treat.getTreatmentId();
            case 1:
                return treat.getCustomerId();
            case 2:
                return treat.getDoctorId();
            case 3:
                return treat.getDate();
            case 4:
                return treat.getTotalCost();
            default:
                return null;
        }
    }

}

