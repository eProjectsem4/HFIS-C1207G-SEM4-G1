/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.da;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import wtw.da.exceptions.NonexistentEntityException;
import wtw.da.exceptions.PreexistingEntityException;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Account;
import wtw.entities.RateWorker;

/**
 *
 * @author Khanh
 */
public class RateWorkerJpaController implements Serializable {

    public RateWorkerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RateWorker rateWorker) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account idWorker = rateWorker.getIdWorker();
            if (idWorker != null) {
                idWorker = em.getReference(idWorker.getClass(), idWorker.getId());
                rateWorker.setIdWorker(idWorker);
            }
            Account idCustomer = rateWorker.getIdCustomer();
            if (idCustomer != null) {
                idCustomer = em.getReference(idCustomer.getClass(), idCustomer.getId());
                rateWorker.setIdCustomer(idCustomer);
            }
            em.persist(rateWorker);
            if (idWorker != null) {
                idWorker.getRateWorkerCollection().add(rateWorker);
                idWorker = em.merge(idWorker);
            }
            if (idCustomer != null) {
                idCustomer.getRateWorkerCollection().add(rateWorker);
                idCustomer = em.merge(idCustomer);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRateWorker(rateWorker.getId()) != null) {
                throw new PreexistingEntityException("RateWorker " + rateWorker + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RateWorker rateWorker) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            RateWorker persistentRateWorker = em.find(RateWorker.class, rateWorker.getId());
            Account idWorkerOld = persistentRateWorker.getIdWorker();
            Account idWorkerNew = rateWorker.getIdWorker();
            Account idCustomerOld = persistentRateWorker.getIdCustomer();
            Account idCustomerNew = rateWorker.getIdCustomer();
            if (idWorkerNew != null) {
                idWorkerNew = em.getReference(idWorkerNew.getClass(), idWorkerNew.getId());
                rateWorker.setIdWorker(idWorkerNew);
            }
            if (idCustomerNew != null) {
                idCustomerNew = em.getReference(idCustomerNew.getClass(), idCustomerNew.getId());
                rateWorker.setIdCustomer(idCustomerNew);
            }
            rateWorker = em.merge(rateWorker);
            if (idWorkerOld != null && !idWorkerOld.equals(idWorkerNew)) {
                idWorkerOld.getRateWorkerCollection().remove(rateWorker);
                idWorkerOld = em.merge(idWorkerOld);
            }
            if (idWorkerNew != null && !idWorkerNew.equals(idWorkerOld)) {
                idWorkerNew.getRateWorkerCollection().add(rateWorker);
                idWorkerNew = em.merge(idWorkerNew);
            }
            if (idCustomerOld != null && !idCustomerOld.equals(idCustomerNew)) {
                idCustomerOld.getRateWorkerCollection().remove(rateWorker);
                idCustomerOld = em.merge(idCustomerOld);
            }
            if (idCustomerNew != null && !idCustomerNew.equals(idCustomerOld)) {
                idCustomerNew.getRateWorkerCollection().add(rateWorker);
                idCustomerNew = em.merge(idCustomerNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rateWorker.getId();
                if (findRateWorker(id) == null) {
                    throw new NonexistentEntityException("The rateWorker with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            RateWorker rateWorker;
            try {
                rateWorker = em.getReference(RateWorker.class, id);
                rateWorker.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rateWorker with id " + id + " no longer exists.", enfe);
            }
            Account idWorker = rateWorker.getIdWorker();
            if (idWorker != null) {
                idWorker.getRateWorkerCollection().remove(rateWorker);
                idWorker = em.merge(idWorker);
            }
            Account idCustomer = rateWorker.getIdCustomer();
            if (idCustomer != null) {
                idCustomer.getRateWorkerCollection().remove(rateWorker);
                idCustomer = em.merge(idCustomer);
            }
            em.remove(rateWorker);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RateWorker> findRateWorkerEntities() {
        return findRateWorkerEntities(true, -1, -1);
    }

    public List<RateWorker> findRateWorkerEntities(int maxResults, int firstResult) {
        return findRateWorkerEntities(false, maxResults, firstResult);
    }

    private List<RateWorker> findRateWorkerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RateWorker.class));
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

    public RateWorker findRateWorker(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RateWorker.class, id);
        } finally {
            em.close();
        }
    }

    public int getRateWorkerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RateWorker> rt = cq.from(RateWorker.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
