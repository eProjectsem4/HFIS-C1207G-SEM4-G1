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
import wtw.entities.FeedWorker;

/**
 *
 * @author Khanh
 */
public class FeedWorkerJpaController implements Serializable {

    public FeedWorkerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FeedWorker feedWorker) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account idWorker = feedWorker.getIdWorker();
            if (idWorker != null) {
                idWorker = em.getReference(idWorker.getClass(), idWorker.getId());
                feedWorker.setIdWorker(idWorker);
            }
            Account idCustomer = feedWorker.getIdCustomer();
            if (idCustomer != null) {
                idCustomer = em.getReference(idCustomer.getClass(), idCustomer.getId());
                feedWorker.setIdCustomer(idCustomer);
            }
            em.persist(feedWorker);
            if (idWorker != null) {
                idWorker.getFeedWorkerCollection().add(feedWorker);
                idWorker = em.merge(idWorker);
            }
            if (idCustomer != null) {
                idCustomer.getFeedWorkerCollection().add(feedWorker);
                idCustomer = em.merge(idCustomer);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findFeedWorker(feedWorker.getId()) != null) {
                throw new PreexistingEntityException("FeedWorker " + feedWorker + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FeedWorker feedWorker) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            FeedWorker persistentFeedWorker = em.find(FeedWorker.class, feedWorker.getId());
            Account idWorkerOld = persistentFeedWorker.getIdWorker();
            Account idWorkerNew = feedWorker.getIdWorker();
            Account idCustomerOld = persistentFeedWorker.getIdCustomer();
            Account idCustomerNew = feedWorker.getIdCustomer();
            if (idWorkerNew != null) {
                idWorkerNew = em.getReference(idWorkerNew.getClass(), idWorkerNew.getId());
                feedWorker.setIdWorker(idWorkerNew);
            }
            if (idCustomerNew != null) {
                idCustomerNew = em.getReference(idCustomerNew.getClass(), idCustomerNew.getId());
                feedWorker.setIdCustomer(idCustomerNew);
            }
            feedWorker = em.merge(feedWorker);
            if (idWorkerOld != null && !idWorkerOld.equals(idWorkerNew)) {
                idWorkerOld.getFeedWorkerCollection().remove(feedWorker);
                idWorkerOld = em.merge(idWorkerOld);
            }
            if (idWorkerNew != null && !idWorkerNew.equals(idWorkerOld)) {
                idWorkerNew.getFeedWorkerCollection().add(feedWorker);
                idWorkerNew = em.merge(idWorkerNew);
            }
            if (idCustomerOld != null && !idCustomerOld.equals(idCustomerNew)) {
                idCustomerOld.getFeedWorkerCollection().remove(feedWorker);
                idCustomerOld = em.merge(idCustomerOld);
            }
            if (idCustomerNew != null && !idCustomerNew.equals(idCustomerOld)) {
                idCustomerNew.getFeedWorkerCollection().add(feedWorker);
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
                Integer id = feedWorker.getId();
                if (findFeedWorker(id) == null) {
                    throw new NonexistentEntityException("The feedWorker with id " + id + " no longer exists.");
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
            FeedWorker feedWorker;
            try {
                feedWorker = em.getReference(FeedWorker.class, id);
                feedWorker.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The feedWorker with id " + id + " no longer exists.", enfe);
            }
            Account idWorker = feedWorker.getIdWorker();
            if (idWorker != null) {
                idWorker.getFeedWorkerCollection().remove(feedWorker);
                idWorker = em.merge(idWorker);
            }
            Account idCustomer = feedWorker.getIdCustomer();
            if (idCustomer != null) {
                idCustomer.getFeedWorkerCollection().remove(feedWorker);
                idCustomer = em.merge(idCustomer);
            }
            em.remove(feedWorker);
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

    public List<FeedWorker> findFeedWorkerEntities() {
        return findFeedWorkerEntities(true, -1, -1);
    }

    public List<FeedWorker> findFeedWorkerEntities(int maxResults, int firstResult) {
        return findFeedWorkerEntities(false, maxResults, firstResult);
    }

    private List<FeedWorker> findFeedWorkerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FeedWorker.class));
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

    public FeedWorker findFeedWorker(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FeedWorker.class, id);
        } finally {
            em.close();
        }
    }

    public int getFeedWorkerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FeedWorker> rt = cq.from(FeedWorker.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
