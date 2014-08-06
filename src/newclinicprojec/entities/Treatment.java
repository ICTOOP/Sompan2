/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newclinicprojec.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Johm_93
 */
@Entity
public class Treatment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer treatmentId;
    private Integer customerId;
    private Integer doctorId;
    private String   date;
    public Double totalCost;
    @OneToMany
    private List<DetailTreatment> detailTreatments;

    public List<DetailTreatment> getDetailTreatments() {
        return detailTreatments;
    }

    public void setDetailTreatments(List<DetailTreatment> detailTreatments) {
        this.detailTreatments = detailTreatments;
    }
    
            
            

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
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
        if (!(object instanceof Treatment)) {
            return false;
        }
        Treatment other = (Treatment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newclinicprojec.entities.Treatment[ id=" + id + " ]";
    }

    public Double calculateCost(){
        if (this.detailTreatments == null || this.detailTreatments.size() ==0){
            return new Double(0);
        }else{
            Double d = 0.0;
            for (DetailTreatment detailTreatment : detailTreatments) {
                d += detailTreatment.getCost();
            }
            return d;
        }
    }
    
}
