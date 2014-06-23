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
import wtw.entities.Project;
import wtw.entities.Account;
import wtw.entities.OrderProject;

/**
 *
 * @author Khanh
 */
public class OrderProjectJpaController implements Serializable {

    public OrderProjectJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<OrderProject> getByIdAccPro(Account idAcc,Project idPro){
        String queryString = "SELECT o FROM OrderProject o WHERE o.idProject = :idProject And o.idAccount = :idAccount";
        TypedQuery<OrderProject> query = getEntityManager().createQuery(queryString, OrderProject.class);
        query.setParameter("idProject", idPro);
        query.setParameter("idAccount", idAcc);
        return query.getResultList();
    }
    
    public List<OrderProject> getByIdPro(Project idPro){
        String queryString = "SELECT o FROM OrderProject o WHERE o.idProject = :idProject";
        TypedQuery<OrderProject> query = getEntityManager().createQuery(queryString, OrderProject.class);
        query.setParameter("idProject", idPro);
        return query.getResultList();
    }    
    
        public List<OrderProject> getByIdAcc(Account idAcc){
        String queryString = "SELECT o FROM OrderProject o WHERE o.idProject = :idProject And o.idAccount = :idAccount";
        TypedQuery<OrderProject> query = getEntityManager().createQuery(queryString, OrderProject.class);
        query.setParameter("idAccount", idAcc);
        return query.getResultList();
    }

    public void create(OrderProject orderProject) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Project idProject = orderProject.getIdProject();
            if (idProject != null) {
                idProject = em.getReference(idProject.getClass(), idProject.getId());
                orderProject.setIdProject(idProject);
            }
            Account idAccount = orderProject.getIdAccount();
            if (idAccount != null) {
                idAccount = em.getReference(idAccount.getClass(), idAccount.getId());
                orderProject.setIdAccount(idAccount);
            }
            em.persist(orderProject);
            if (idProject != null) {
                idProject.getOrderProjectCollection().add(orderProject);
                idProject = em.merge(idProject);
            }
            if (idAccount != null) {
                idAccount.getOrderProjectCollection().add(orderProject);
                idAccount = em.merge(idAccount);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findOrderProject(orderProject.getId()) != null) {
                throw new PreexistingEntityException("OrderProject " + orderProject + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrderProject orderProject) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrderProject persistentOrderProject = em.find(OrderProject.class, orderProject.getId());
            Project idProjectOld = persistentOrderProject.getIdProject();
            Project idProjectNew = orderProject.getIdProject();
            Account idAccountOld = persistentOrderProject.getIdAccount();
            Account idAccountNew = orderProject.getIdAccount();
            if (idProjectNew != null) {
                idProjectNew = em.getReference(idProjectNew.getClass(), idProjectNew.getId());
                orderProject.setIdProject(idProjectNew);
            }
            if (idAccountNew != null) {
                idAccountNew = em.getReference(idAccountNew.getClass(), idAccountNew.getId());
                orderProject.setIdAccount(idAccountNew);
            }
            orderProject = em.merge(orderProject);
            if (idProjectOld != null && !idProjectOld.equals(idProjectNew)) {
                idProjectOld.getOrderProjectCollection().remove(orderProject);
                idProjectOld = em.merge(idProjectOld);
            }
            if (idProjectNew != null && !idProjectNew.equals(idProjectOld)) {
                idProjectNew.getOrderProjectCollection().add(orderProject);
                idProjectNew = em.merge(idProjectNew);
            }
            if (idAccountOld != null && !idAccountOld.equals(idAccountNew)) {
                idAccountOld.getOrderProjectCollection().remove(orderProject);
                idAccountOld = em.merge(idAccountOld);
            }
            if (idAccountNew != null && !idAccountNew.equals(idAccountOld)) {
                idAccountNew.getOrderProjectCollection().add(orderProject);
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
                Integer id = orderProject.getId();
                if (findOrderProject(id) == null) {
                    throw new NonexistentEntityException("The orderProject with id " + id + " no longer exists.");
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
            OrderProject orderProject;
            try {
                orderProject = em.getReference(OrderProject.class, id);
                orderProject.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderProject with id " + id + " no longer exists.", enfe);
            }
            Project idProject = orderProject.getIdProject();
            if (idProject != null) {
                idProject.getOrderProjectCollection().remove(orderProject);
                idProject = em.merge(idProject);
            }
            Account idAccount = orderProject.getIdAccount();
            if (idAccount != null) {
                idAccount.getOrderProjectCollection().remove(orderProject);
                idAccount = em.merge(idAccount);
            }
            em.remove(orderProject);
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

    public List<OrderProject> findOrderProjectEntities() {
        return findOrderProjectEntities(true, -1, -1);
    }

    public List<OrderProject> findOrderProjectEntities(int maxResults, int firstResult) {
        return findOrderProjectEntities(false, maxResults, firstResult);
    }

    private List<OrderProject> findOrderProjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrderProject.class));
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

    public OrderProject findOrderProject(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderProject.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderProjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderProject> rt = cq.from(OrderProject.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
