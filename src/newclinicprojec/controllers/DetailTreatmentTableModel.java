/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newclinicprojec.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import newclinicprojec.entities.DetailTreatment;

/**
 *
 * @author Johm_93
 */
public class DetailTreatmentTableModel extends AbstractTableModel {

    String[] columnName = {"TeethPosition", "Treament", "Cost"};
    List<DetailTreatment> detailTreatment;

    
    
    public DetailTreatmentTableModel(List<DetailTreatment> detailTreatments) {
        if (detailTreatments == null) {
            this.detailTreatment = new ArrayList<>();
        } else {
            this.detailTreatment = detailTreatments;
        }

        this.fireTableDataChanged();
    }

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

    public List<DetailTreatment> getDetailtreatment() {
        return detailTreatment;
    }

    public void setDetailtreatment(List<DetailTreatment> detailTreatment) {
        this.detailTreatment = detailTreatment;
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
        return detailTreatment.size();
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public String getColumnName(int i) {
        return columnName[i];
    }

    @Override
    public Object getValueAt(int i, int i1) {
        DetailTreatment detailtreat = detailTreatment.get(i);
        switch (i1) {
            case 0:
                return detailtreat.getTeethPosition();
            case 1:
                return detailtreat.getTreat();
            case 2:
                return detailtreat.getCost();
            default:
                return null;
        }
    }

}
