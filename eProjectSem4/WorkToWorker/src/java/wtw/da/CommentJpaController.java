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
import wtw.entities.Topic;
import wtw.entities.Account;
import wtw.entities.Comment;

/**
 *
 * @author Khanh
 */
public class CommentJpaController implements Serializable {

    public CommentJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Comment> getByIdTopic(Topic tp){
        String queryString = "SELECT c FROM Comment c WHERE c.idTopic = :idTopic";
        TypedQuery<Comment> query = getEntityManager().createQuery(queryString, Comment.class);
        query.setParameter("idTopic", tp);
        return query.getResultList();
    }

    public void create(Comment comment) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Topic idTopic = comment.getIdTopic();
            if (idTopic != null) {
                idTopic = em.getReference(idTopic.getClass(), idTopic.getId());
                comment.setIdTopic(idTopic);
            }
            Account idAccount = comment.getIdAccount();
            if (idAccount != null) {
                idAccount = em.getReference(idAccount.getClass(), idAccount.getId());
                comment.setIdAccount(idAccount);
            }
            em.persist(comment);
            if (idTopic != null) {
                idTopic.getCommentCollection().add(comment);
                idTopic = em.merge(idTopic);
            }
            if (idAccount != null) {
                idAccount.getCommentCollection().add(comment);
                idAccount = em.merge(idAccount);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findComment(comment.getId()) != null) {
                throw new PreexistingEntityException("Comment " + comment + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comment comment) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Comment persistentComment = em.find(Comment.class, comment.getId());
            Topic idTopicOld = persistentComment.getIdTopic();
            Topic idTopicNew = comment.getIdTopic();
            Account idAccountOld = persistentComment.getIdAccount();
            Account idAccountNew = comment.getIdAccount();
            if (idTopicNew != null) {
                idTopicNew = em.getReference(idTopicNew.getClass(), idTopicNew.getId());
                comment.setIdTopic(idTopicNew);
            }
            if (idAccountNew != null) {
                idAccountNew = em.getReference(idAccountNew.getClass(), idAccountNew.getId());
                comment.setIdAccount(idAccountNew);
            }
            comment = em.merge(comment);
            if (idTopicOld != null && !idTopicOld.equals(idTopicNew)) {
                idTopicOld.getCommentCollection().remove(comment);
                idTopicOld = em.merge(idTopicOld);
            }
            if (idTopicNew != null && !idTopicNew.equals(idTopicOld)) {
                idTopicNew.getCommentCollection().add(comment);
                idTopicNew = em.merge(idTopicNew);
            }
            if (idAccountOld != null && !idAccountOld.equals(idAccountNew)) {
                idAccountOld.getCommentCollection().remove(comment);
                idAccountOld = em.merge(idAccountOld);
            }
            if (idAccountNew != null && !idAccountNew.equals(idAccountOld)) {
                idAccountNew.getCommentCollection().add(comment);
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
                Integer id = comment.getId();
                if (findComment(id) == null) {
                    throw new NonexistentEntityException("The comment with id " + id + " no longer exists.");
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
            Comment comment;
            try {
                comment = em.getReference(Comment.class, id);
                comment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comment with id " + id + " no longer exists.", enfe);
            }
            Topic idTopic = comment.getIdTopic();
            if (idTopic != null) {
                idTopic.getCommentCollection().remove(comment);
                idTopic = em.merge(idTopic);
            }
            Account idAccount = comment.getIdAccount();
            if (idAccount != null) {
                idAccount.getCommentCollection().remove(comment);
                idAccount = em.merge(idAccount);
            }
            em.remove(comment);
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

    public List<Comment> findCommentEntities() {
        return findCommentEntities(true, -1, -1);
    }

    public List<Comment> findCommentEntities(int maxResults, int firstResult) {
        return findCommentEntities(false, maxResults, firstResult);
    }

    private List<Comment> findCommentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comment.class));
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

    public Comment findComment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comment.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comment> rt = cq.from(Comment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
