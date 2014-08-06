/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newclinicprojec.controllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import newclinicprojec.entities.DetailTreatment;
import newclinicprojec.entities.Treatment;
import newclinicprojec.exceptions.NonexistentEntityException;

/**
 *
 * @author Johm_93
 */
public class TreatmentJpaController implements Serializable {

    private DetailTreatmentJpaController detailTreatmentJpaController;
    
    public TreatmentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
        this.detailTreatmentJpaController = new DetailTreatmentJpaController(emf);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void save(Treatment treatment) throws Exception{
        
        for (DetailTreatment detailTreatment : treatment.getDetailTreatments()) {
            detailTreatmentJpaController.save(detailTreatment);
        }
        
        if (treatment.getId() == null){
            create(treatment);
        }else {
            edit(treatment);
        }
    }

    public void create(Treatment treatment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(treatment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Treatment treatment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            treatment = em.merge(treatment);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = treatment.getId();
                if (findTreatment(id) == null) {
                    throw new NonexistentEntityException("The treatment with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Treatment treatment;
            try {
                treatment = em.getReference(Treatment.class, id);
                treatment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The treatment with id " + id + " no longer exists.", enfe);
            }
            em.remove(treatment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Treatment> findTreatmentEntities() {
        return findTreatmentEntities(true, -1, -1);
    }

    public List<Treatment> findTreatmentEntities(int maxResults, int firstResult) {
        return findTreatmentEntities(false, maxResults, firstResult);
    }

    private List<Treatment> findTreatmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Treatment.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public  List<Treatment> findTreatmentsByCustomerId(int customerId){
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root root = cq.from(Treatment.class);
            cq.select(root);
            
            cq.where(cb.equal(root.get("customerId"), customerId));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Treatment findTreatment(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Treatment.class, id);
        } finally {
            em.close();
        }
    }

    public int getTreatmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Treatment> rt = cq.from(Treatment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
