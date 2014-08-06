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
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import newclinicprojec.entities.DetailTreatment;
import newclinicprojec.exceptions.NonexistentEntityException;

/**
 *
 * @author Johm_93
 */
public class DetailTreatmentJpaController implements Serializable {

    public DetailTreatmentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void save(DetailTreatment detailTreatment) throws Exception{
        
        if(detailTreatment.getId() == null){
            create(detailTreatment);
        }else {
            edit(detailTreatment);
        }
        
        
    }

    public void create(DetailTreatment detailTreatment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detailTreatment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetailTreatment detailTreatment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detailTreatment = em.merge(detailTreatment);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detailTreatment.getId();
                if (findDetailTreatment(id) == null) {
                    throw new NonexistentEntityException("The detailTreatment with id " + id + " no longer exists.");
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
            DetailTreatment detailTreatment;
            try {
                detailTreatment = em.getReference(DetailTreatment.class, id);
                detailTreatment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detailTreatment with id " + id + " no longer exists.", enfe);
            }
            em.remove(detailTreatment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetailTreatment> findDetailTreatmentEntities() {
        return findDetailTreatmentEntities(true, -1, -1);
    }

    public List<DetailTreatment> findDetailTreatmentEntities(int maxResults, int firstResult) {
        return findDetailTreatmentEntities(false, maxResults, firstResult);
    }

    private List<DetailTreatment> findDetailTreatmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetailTreatment.class));
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

    public DetailTreatment findDetailTreatment(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetailTreatment.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetailTreatmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetailTreatment> rt = cq.from(DetailTreatment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
