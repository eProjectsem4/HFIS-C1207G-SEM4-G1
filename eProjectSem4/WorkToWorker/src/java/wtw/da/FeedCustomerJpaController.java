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
import wtw.entities.FeedCustomer;

/**
 *
 * @author Khanh
 */
public class FeedCustomerJpaController implements Serializable {

    public FeedCustomerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FeedCustomer feedCustomer) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account idWorker = feedCustomer.getIdWorker();
            if (idWorker != null) {
                idWorker = em.getReference(idWorker.getClass(), idWorker.getId());
                feedCustomer.setIdWorker(idWorker);
            }
            Account idCustomer = feedCustomer.getIdCustomer();
            if (idCustomer != null) {
                idCustomer = em.getReference(idCustomer.getClass(), idCustomer.getId());
                feedCustomer.setIdCustomer(idCustomer);
            }
            em.persist(feedCustomer);
            if (idWorker != null) {
                idWorker.getFeedCustomerCollection().add(feedCustomer);
                idWorker = em.merge(idWorker);
            }
            if (idCustomer != null) {
                idCustomer.getFeedCustomerCollection().add(feedCustomer);
                idCustomer = em.merge(idCustomer);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findFeedCustomer(feedCustomer.getId()) != null) {
                throw new PreexistingEntityException("FeedCustomer " + feedCustomer + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FeedCustomer feedCustomer) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            FeedCustomer persistentFeedCustomer = em.find(FeedCustomer.class, feedCustomer.getId());
            Account idWorkerOld = persistentFeedCustomer.getIdWorker();
            Account idWorkerNew = feedCustomer.getIdWorker();
            Account idCustomerOld = persistentFeedCustomer.getIdCustomer();
            Account idCustomerNew = feedCustomer.getIdCustomer();
            if (idWorkerNew != null) {
                idWorkerNew = em.getReference(idWorkerNew.getClass(), idWorkerNew.getId());
                feedCustomer.setIdWorker(idWorkerNew);
            }
            if (idCustomerNew != null) {
                idCustomerNew = em.getReference(idCustomerNew.getClass(), idCustomerNew.getId());
                feedCustomer.setIdCustomer(idCustomerNew);
            }
            feedCustomer = em.merge(feedCustomer);
            if (idWorkerOld != null && !idWorkerOld.equals(idWorkerNew)) {
                idWorkerOld.getFeedCustomerCollection().remove(feedCustomer);
                idWorkerOld = em.merge(idWorkerOld);
            }
            if (idWorkerNew != null && !idWorkerNew.equals(idWorkerOld)) {
                idWorkerNew.getFeedCustomerCollection().add(feedCustomer);
                idWorkerNew = em.merge(idWorkerNew);
            }
            if (idCustomerOld != null && !idCustomerOld.equals(idCustomerNew)) {
                idCustomerOld.getFeedCustomerCollection().remove(feedCustomer);
                idCustomerOld = em.merge(idCustomerOld);
            }
            if (idCustomerNew != null && !idCustomerNew.equals(idCustomerOld)) {
                idCustomerNew.getFeedCustomerCollection().add(feedCustomer);
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
                Integer id = feedCustomer.getId();
                if (findFeedCustomer(id) == null) {
                    throw new NonexistentEntityException("The feedCustomer with id " + id + " no longer exists.");
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
            FeedCustomer feedCustomer;
            try {
                feedCustomer = em.getReference(FeedCustomer.class, id);
                feedCustomer.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The feedCustomer with id " + id + " no longer exists.", enfe);
            }
            Account idWorker = feedCustomer.getIdWorker();
            if (idWorker != null) {
                idWorker.getFeedCustomerCollection().remove(feedCustomer);
                idWorker = em.merge(idWorker);
            }
            Account idCustomer = feedCustomer.getIdCustomer();
            if (idCustomer != null) {
                idCustomer.getFeedCustomerCollection().remove(feedCustomer);
                idCustomer = em.merge(idCustomer);
            }
            em.remove(feedCustomer);
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

    public List<FeedCustomer> findFeedCustomerEntities() {
        return findFeedCustomerEntities(true, -1, -1);
    }

    public List<FeedCustomer> findFeedCustomerEntities(int maxResults, int firstResult) {
        return findFeedCustomerEntities(false, maxResults, firstResult);
    }

    private List<FeedCustomer> findFeedCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FeedCustomer.class));
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

    public FeedCustomer findFeedCustomer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FeedCustomer.class, id);
        } finally {
            em.close();
        }
    }

    public int getFeedCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FeedCustomer> rt = cq.from(FeedCustomer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
