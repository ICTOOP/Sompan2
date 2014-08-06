/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newclinicprojec.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Johm_93
 */
@Entity
public class DetailTreatment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer teethPosition;
    private String treat;
    private Double cost;

   

  public Integer getTeethPosition() {
        return teethPosition;
    }

   public void setTeethPosition(Integer teethPosition) {
        this.teethPosition = teethPosition;
    }

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailTreatment)) {
            return false;
        }
        DetailTreatment other = (DetailTreatment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newclinicprojec.entities.DetialTreatment[ id=" + id + " ]";
    }

 
    
}
