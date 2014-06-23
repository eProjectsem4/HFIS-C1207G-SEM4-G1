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
import wtw.entities.RateCustomer;

/**
 *
 * @author Khanh
 */
public class RateCustomerJpaController implements Serializable {

    public RateCustomerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RateCustomer rateCustomer) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account idWorker = rateCustomer.getIdWorker();
            if (idWorker != null) {
                idWorker = em.getReference(idWorker.getClass(), idWorker.getId());
                rateCustomer.setIdWorker(idWorker);
            }
            Account idCustomer = rateCustomer.getIdCustomer();
            if (idCustomer != null) {
                idCustomer = em.getReference(idCustomer.getClass(), idCustomer.getId());
                rateCustomer.setIdCustomer(idCustomer);
            }
            em.persist(rateCustomer);
            if (idWorker != null) {
                idWorker.getRateCustomerCollection().add(rateCustomer);
                idWorker = em.merge(idWorker);
            }
            if (idCustomer != null) {
                idCustomer.getRateCustomerCollection().add(rateCustomer);
                idCustomer = em.merge(idCustomer);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRateCustomer(rateCustomer.getId()) != null) {
                throw new PreexistingEntityException("RateCustomer " + rateCustomer + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RateCustomer rateCustomer) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            RateCustomer persistentRateCustomer = em.find(RateCustomer.class, rateCustomer.getId());
            Account idWorkerOld = persistentRateCustomer.getIdWorker();
            Account idWorkerNew = rateCustomer.getIdWorker();
            Account idCustomerOld = persistentRateCustomer.getIdCustomer();
            Account idCustomerNew = rateCustomer.getIdCustomer();
            if (idWorkerNew != null) {
                idWorkerNew = em.getReference(idWorkerNew.getClass(), idWorkerNew.getId());
                rateCustomer.setIdWorker(idWorkerNew);
            }
            if (idCustomerNew != null) {
                idCustomerNew = em.getReference(idCustomerNew.getClass(), idCustomerNew.getId());
                rateCustomer.setIdCustomer(idCustomerNew);
            }
            rateCustomer = em.merge(rateCustomer);
            if (idWorkerOld != null && !idWorkerOld.equals(idWorkerNew)) {
                idWorkerOld.getRateCustomerCollection().remove(rateCustomer);
                idWorkerOld = em.merge(idWorkerOld);
            }
            if (idWorkerNew != null && !idWorkerNew.equals(idWorkerOld)) {
                idWorkerNew.getRateCustomerCollection().add(rateCustomer);
                idWorkerNew = em.merge(idWorkerNew);
            }
            if (idCustomerOld != null && !idCustomerOld.equals(idCustomerNew)) {
                idCustomerOld.getRateCustomerCollection().remove(rateCustomer);
                idCustomerOld = em.merge(idCustomerOld);
            }
            if (idCustomerNew != null && !idCustomerNew.equals(idCustomerOld)) {
                idCustomerNew.getRateCustomerCollection().add(rateCustomer);
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
                Integer id = rateCustomer.getId();
                if (findRateCustomer(id) == null) {
                    throw new NonexistentEntityException("The rateCustomer with id " + id + " no longer exists.");
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
            RateCustomer rateCustomer;
            try {
                rateCustomer = em.getReference(RateCustomer.class, id);
                rateCustomer.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rateCustomer with id " + id + " no longer exists.", enfe);
            }
            Account idWorker = rateCustomer.getIdWorker();
            if (idWorker != null) {
                idWorker.getRateCustomerCollection().remove(rateCustomer);
                idWorker = em.merge(idWorker);
            }
            Account idCustomer = rateCustomer.getIdCustomer();
            if (idCustomer != null) {
                idCustomer.getRateCustomerCollection().remove(rateCustomer);
                idCustomer = em.merge(idCustomer);
            }
            em.remove(rateCustomer);
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

    public List<RateCustomer> findRateCustomerEntities() {
        return findRateCustomerEntities(true, -1, -1);
    }

    public List<RateCustomer> findRateCustomerEntities(int maxResults, int firstResult) {
        return findRateCustomerEntities(false, maxResults, firstResult);
    }

    private List<RateCustomer> findRateCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RateCustomer.class));
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

    public RateCustomer findRateCustomer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RateCustomer.class, id);
        } finally {
            em.close();
        }
    }

    public int getRateCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RateCustomer> rt = cq.from(RateCustomer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
