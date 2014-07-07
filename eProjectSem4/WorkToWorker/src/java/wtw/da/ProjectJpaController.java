/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.da;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import wtw.entities.Account;
import wtw.entities.OrderProject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import wtw.da.exceptions.NonexistentEntityException;
import wtw.da.exceptions.PreexistingEntityException;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Project;

/**
 *
 * @author Khanh
 */
public class ProjectJpaController implements Serializable {

    public ProjectJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Project> getAll(){
        String queryString = "SELECT p FROM Project p";
        TypedQuery<Project> query = getEntityManager().createQuery(queryString,Project.class);
        return query.getResultList();
    }
    
     public List<Project> searchProject(String keywork){
        String queryString = "SELECT p FROM Project p where p.name like :name or p.category like :category or p.price = :price or p.status = :status";
        TypedQuery<Project> query = getEntityManager().createQuery(queryString,Project.class);
        query.setParameter("name", "%" + keywork + "%");
        query.setParameter("category", "%"+keywork+"%");
        query.setParameter("status", keywork);
        try{
            query.setParameter("price", Integer.parseInt(keywork));
        }catch(Exception e){
            query.setParameter("price", null);
        }
        return query.getResultList();
    }
    
    public Project getById(int id){
        String queryString = "SELECT p FROM Project p WHERE p.id = :id";
        TypedQuery<Project> query = getEntityManager().createQuery(queryString, Project.class);
        query.setParameter("id", id);
        return query.getResultList().get(0);
    }
    
     public List<Project> getByIdCustomer(Account customer){
        String queryString = "SELECT p FROM Project p WHERE p.idCustomer = :idCustomer";
        TypedQuery<Project> query = getEntityManager().createQuery(queryString, Project.class);
        query.setParameter("idCustomer", customer);
        return query.getResultList();
    }

    public void create(Project project) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (project.getOrderProjectCollection() == null) {
            project.setOrderProjectCollection(new ArrayList<OrderProject>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account idWorker = project.getIdWorker();
            if (idWorker != null) {
                idWorker = em.getReference(idWorker.getClass(), idWorker.getId());
                project.setIdWorker(idWorker);
            }
            Account idCustomer = project.getIdCustomer();
            if (idCustomer != null) {
                idCustomer = em.getReference(idCustomer.getClass(), idCustomer.getId());
                project.setIdCustomer(idCustomer);
            }
            Collection<OrderProject> attachedOrderProjectCollection = new ArrayList<OrderProject>();
            for (OrderProject orderProjectCollectionOrderProjectToAttach : project.getOrderProjectCollection()) {
                orderProjectCollectionOrderProjectToAttach = em.getReference(orderProjectCollectionOrderProjectToAttach.getClass(), orderProjectCollectionOrderProjectToAttach.getId());
                attachedOrderProjectCollection.add(orderProjectCollectionOrderProjectToAttach);
            }
            project.setOrderProjectCollection(attachedOrderProjectCollection);
            em.persist(project);
            if (idWorker != null) {
                idWorker.getProjectCollection().add(project);
                idWorker = em.merge(idWorker);
            }
            if (idCustomer != null) {
                idCustomer.getProjectCollection().add(project);
                idCustomer = em.merge(idCustomer);
            }
            for (OrderProject orderProjectCollectionOrderProject : project.getOrderProjectCollection()) {
                Project oldIdProjectOfOrderProjectCollectionOrderProject = orderProjectCollectionOrderProject.getIdProject();
                orderProjectCollectionOrderProject.setIdProject(project);
                orderProjectCollectionOrderProject = em.merge(orderProjectCollectionOrderProject);
                if (oldIdProjectOfOrderProjectCollectionOrderProject != null) {
                    oldIdProjectOfOrderProjectCollectionOrderProject.getOrderProjectCollection().remove(orderProjectCollectionOrderProject);
                    oldIdProjectOfOrderProjectCollectionOrderProject = em.merge(oldIdProjectOfOrderProjectCollectionOrderProject);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProject(project.getId()) != null) {
                throw new PreexistingEntityException("Project " + project + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Project project) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Project persistentProject = em.find(Project.class, project.getId());
            Account idWorkerOld = persistentProject.getIdWorker();
            Account idWorkerNew = project.getIdWorker();
            Account idCustomerOld = persistentProject.getIdCustomer();
            Account idCustomerNew = project.getIdCustomer();
           // Collection<OrderProject> orderProjectCollectionOld = persistentProject.getOrderProjectCollection();
           // Collection<OrderProject> orderProjectCollectionNew = project.getOrderProjectCollection();
            if (idWorkerNew != null) {
                idWorkerNew = em.getReference(idWorkerNew.getClass(), idWorkerNew.getId());
                project.setIdWorker(idWorkerNew);
            }
            if (idCustomerNew != null) {
                idCustomerNew = em.getReference(idCustomerNew.getClass(), idCustomerNew.getId());
                project.setIdCustomer(idCustomerNew);
            }
          //  Collection<OrderProject> attachedOrderProjectCollectionNew = new ArrayList<OrderProject>();
           // for (OrderProject orderProjectCollectionNewOrderProjectToAttach : orderProjectCollectionNew) {
            //    orderProjectCollectionNewOrderProjectToAttach = em.getReference(orderProjectCollectionNewOrderProjectToAttach.getClass(), orderProjectCollectionNewOrderProjectToAttach.getId());
           //     attachedOrderProjectCollectionNew.add(orderProjectCollectionNewOrderProjectToAttach);
           // }
           // orderProjectCollectionNew = attachedOrderProjectCollectionNew;
         //   project.setOrderProjectCollection(orderProjectCollectionNew);
            project = em.merge(project);
            if (idWorkerOld != null && !idWorkerOld.equals(idWorkerNew)) {
                idWorkerOld.getProjectCollection().remove(project);
                idWorkerOld = em.merge(idWorkerOld);
            }
            if (idWorkerNew != null && !idWorkerNew.equals(idWorkerOld)) {
                idWorkerNew.getProjectCollection().add(project);
                idWorkerNew = em.merge(idWorkerNew);
            }
            if (idCustomerOld != null && !idCustomerOld.equals(idCustomerNew)) {
                idCustomerOld.getProjectCollection().remove(project);
                idCustomerOld = em.merge(idCustomerOld);
            }
            if (idCustomerNew != null && !idCustomerNew.equals(idCustomerOld)) {
                idCustomerNew.getProjectCollection().add(project);
                idCustomerNew = em.merge(idCustomerNew);
            }
           // for (OrderProject orderProjectCollectionOldOrderProject : orderProjectCollectionOld) {
           //     if (!orderProjectCollectionNew.contains(orderProjectCollectionOldOrderProject)) {
            //        orderProjectCollectionOldOrderProject.setIdProject(null);
             //       orderProjectCollectionOldOrderProject = em.merge(orderProjectCollectionOldOrderProject);
            //    }
          //  }
          //  for (OrderProject orderProjectCollectionNewOrderProject : orderProjectCollectionNew) {
            //    if (!orderProjectCollectionOld.contains(orderProjectCollectionNewOrderProject)) {
              //      Project oldIdProjectOfOrderProjectCollectionNewOrderProject = orderProjectCollectionNewOrderProject.getIdProject();
               //     orderProjectCollectionNewOrderProject.setIdProject(project);
                //    orderProjectCollectionNewOrderProject = em.merge(orderProjectCollectionNewOrderProject);
                 //   if (oldIdProjectOfOrderProjectCollectionNewOrderProject != null && !oldIdProjectOfOrderProjectCollectionNewOrderProject.equals(project)) {
                  //      oldIdProjectOfOrderProjectCollectionNewOrderProject.getOrderProjectCollection().remove(orderProjectCollectionNewOrderProject);
                   //     oldIdProjectOfOrderProjectCollectionNewOrderProject = em.merge(oldIdProjectOfOrderProjectCollectionNewOrderProject);
                  //  }
              //  }
          //  }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = project.getId();
                if (findProject(id) == null) {
                    throw new NonexistentEntityException("The project with id " + id + " no longer exists.");
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
            Project project;
            try {
                project = em.getReference(Project.class, id);
                project.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The project with id " + id + " no longer exists.", enfe);
            }
            Account idWorker = project.getIdWorker();
            if (idWorker != null) {
                idWorker.getProjectCollection().remove(project);
                idWorker = em.merge(idWorker);
            }
            Account idCustomer = project.getIdCustomer();
            if (idCustomer != null) {
                idCustomer.getProjectCollection().remove(project);
                idCustomer = em.merge(idCustomer);
            }
            Collection<OrderProject> orderProjectCollection = project.getOrderProjectCollection();
            for (OrderProject orderProjectCollectionOrderProject : orderProjectCollection) {
                orderProjectCollectionOrderProject.setIdProject(null);
                orderProjectCollectionOrderProject = em.merge(orderProjectCollectionOrderProject);
            }
            em.remove(project);
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

    public List<Project> findProjectEntities() {
        return findProjectEntities(true, -1, -1);
    }

    public List<Project> findProjectEntities(int maxResults, int firstResult) {
        return findProjectEntities(false, maxResults, firstResult);
    }

    private List<Project> findProjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Project.class));
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

    public Project findProject(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Project.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Project> rt = cq.from(Project.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
