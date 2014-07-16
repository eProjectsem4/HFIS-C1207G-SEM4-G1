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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import wtw.da.exceptions.NonexistentEntityException;
import wtw.da.exceptions.PreexistingEntityException;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Account;
import wtw.entities.Balance;

/**
 *
 * @author viet cuong
 */
public class BalanceJpaController implements Serializable {

    public BalanceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Balance balance) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account idAccount = balance.getIdAccount();
            if (idAccount != null) {
                idAccount = em.getReference(idAccount.getClass(), idAccount.getId());
                balance.setIdAccount(idAccount);
            }
            em.persist(balance);
            if (idAccount != null) {
                idAccount.getBalanceList().add(balance);
                idAccount = em.merge(idAccount);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBalance(balance.getId()) != null) {
                throw new PreexistingEntityException("Balance " + balance + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Balance balance) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Balance persistentBalance = em.find(Balance.class, balance.getId());
            Account idAccountOld = persistentBalance.getIdAccount();
            Account idAccountNew = balance.getIdAccount();
            if (idAccountNew != null) {
                idAccountNew = em.getReference(idAccountNew.getClass(), idAccountNew.getId());
                balance.setIdAccount(idAccountNew);
            }
            balance = em.merge(balance);
            if (idAccountOld != null && !idAccountOld.equals(idAccountNew)) {
                idAccountOld.getBalanceList().remove(balance);
                idAccountOld = em.merge(idAccountOld);
            }
            if (idAccountNew != null && !idAccountNew.equals(idAccountOld)) {
                idAccountNew.getBalanceList().add(balance);
                idAccountNew = em.merge(idAccountNew);
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
                Integer id = balance.getId();
                if (findBalance(id) == null) {
                    throw new NonexistentEntityException("The balance with id " + id + " no longer exists.");
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
            Balance balance;
            try {
                balance = em.getReference(Balance.class, id);
                balance.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The balance with id " + id + " no longer exists.", enfe);
            }
            Account idAccount = balance.getIdAccount();
            if (idAccount != null) {
                idAccount.getBalanceList().remove(balance);
                idAccount = em.merge(idAccount);
            }
            em.remove(balance);
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

    public List<Balance> findBalanceEntities() {
        return findBalanceEntities(true, -1, -1);
    }

    public List<Balance> findBalanceEntities(int maxResults, int firstResult) {
        return findBalanceEntities(false, maxResults, firstResult);
    }

    private List<Balance> findBalanceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Balance.class));
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

    public Balance findBalance(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Balance.class, id);
        } finally {
            em.close();
        }
    }

    public int getBalanceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Balance> rt = cq.from(Balance.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Balance getBalanceByIdAccount(Account id) {
        try {

            String query = "SELECT b FROM Balance b where b.idAccount = :id";
            TypedQuery<Balance> createQuery = getEntityManager().createQuery(query, Balance.class);
            createQuery.setParameter("id", id);
            return createQuery.getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    public double checkBalance(Account id) {
        try {
            String query = "SELECT b FROM Balance b where b.idAccount = :id";
            TypedQuery<Balance> createQuery = getEntityManager().createQuery(query, Balance.class);
            createQuery.setParameter("id", id);
            return createQuery.getSingleResult().getMoney();
        } catch (Exception e) {
            return -1;
        }

    }
}
