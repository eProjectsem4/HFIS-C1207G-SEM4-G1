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
import wtw.entities.Comment;
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
import wtw.entities.Topic;

/**
 *
 * @author Khanh
 */
public class TopicJpaController implements Serializable {

    public TopicJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Topic> getAll(){
        String queryString = "SELECT t FROM Topic t";
        TypedQuery<Topic> query = getEntityManager().createQuery(queryString, Topic.class);
        return query.getResultList();
    }
    
     public List<Topic> getById(int id){
        String queryString = "SELECT t FROM Topic t WHERE t.id = :id";
        TypedQuery<Topic> query = getEntityManager().createQuery(queryString, Topic.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public void create(Topic topic) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (topic.getCommentCollection() == null) {
            topic.setCommentCollection(new ArrayList<Comment>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account idAccount = topic.getIdAccount();
            if (idAccount != null) {
                idAccount = em.getReference(idAccount.getClass(), idAccount.getId());
                topic.setIdAccount(idAccount);
            }
            Collection<Comment> attachedCommentCollection = new ArrayList<Comment>();
            for (Comment commentCollectionCommentToAttach : topic.getCommentCollection()) {
                commentCollectionCommentToAttach = em.getReference(commentCollectionCommentToAttach.getClass(), commentCollectionCommentToAttach.getId());
                attachedCommentCollection.add(commentCollectionCommentToAttach);
            }
            topic.setCommentCollection(attachedCommentCollection);
            em.persist(topic);
            if (idAccount != null) {
                idAccount.getTopicCollection().add(topic);
                idAccount = em.merge(idAccount);
            }
            for (Comment commentCollectionComment : topic.getCommentCollection()) {
                Topic oldIdTopicOfCommentCollectionComment = commentCollectionComment.getIdTopic();
                commentCollectionComment.setIdTopic(topic);
                commentCollectionComment = em.merge(commentCollectionComment);
                if (oldIdTopicOfCommentCollectionComment != null) {
                    oldIdTopicOfCommentCollectionComment.getCommentCollection().remove(commentCollectionComment);
                    oldIdTopicOfCommentCollectionComment = em.merge(oldIdTopicOfCommentCollectionComment);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTopic(topic.getId()) != null) {
                throw new PreexistingEntityException("Topic " + topic + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Topic topic) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Topic persistentTopic = em.find(Topic.class, topic.getId());
            Account idAccountOld = persistentTopic.getIdAccount();
            Account idAccountNew = topic.getIdAccount();
            Collection<Comment> commentCollectionOld = persistentTopic.getCommentCollection();
            Collection<Comment> commentCollectionNew = topic.getCommentCollection();
            if (idAccountNew != null) {
                idAccountNew = em.getReference(idAccountNew.getClass(), idAccountNew.getId());
                topic.setIdAccount(idAccountNew);
            }
            Collection<Comment> attachedCommentCollectionNew = new ArrayList<Comment>();
            for (Comment commentCollectionNewCommentToAttach : commentCollectionNew) {
                commentCollectionNewCommentToAttach = em.getReference(commentCollectionNewCommentToAttach.getClass(), commentCollectionNewCommentToAttach.getId());
                attachedCommentCollectionNew.add(commentCollectionNewCommentToAttach);
            }
            commentCollectionNew = attachedCommentCollectionNew;
            topic.setCommentCollection(commentCollectionNew);
            topic = em.merge(topic);
            if (idAccountOld != null && !idAccountOld.equals(idAccountNew)) {
                idAccountOld.getTopicCollection().remove(topic);
                idAccountOld = em.merge(idAccountOld);
            }
            if (idAccountNew != null && !idAccountNew.equals(idAccountOld)) {
                idAccountNew.getTopicCollection().add(topic);
                idAccountNew = em.merge(idAccountNew);
            }
            for (Comment commentCollectionOldComment : commentCollectionOld) {
                if (!commentCollectionNew.contains(commentCollectionOldComment)) {
                    commentCollectionOldComment.setIdTopic(null);
                    commentCollectionOldComment = em.merge(commentCollectionOldComment);
                }
            }
            for (Comment commentCollectionNewComment : commentCollectionNew) {
                if (!commentCollectionOld.contains(commentCollectionNewComment)) {
                    Topic oldIdTopicOfCommentCollectionNewComment = commentCollectionNewComment.getIdTopic();
                    commentCollectionNewComment.setIdTopic(topic);
                    commentCollectionNewComment = em.merge(commentCollectionNewComment);
                    if (oldIdTopicOfCommentCollectionNewComment != null && !oldIdTopicOfCommentCollectionNewComment.equals(topic)) {
                        oldIdTopicOfCommentCollectionNewComment.getCommentCollection().remove(commentCollectionNewComment);
                        oldIdTopicOfCommentCollectionNewComment = em.merge(oldIdTopicOfCommentCollectionNewComment);
                    }
                }
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
                Integer id = topic.getId();
                if (findTopic(id) == null) {
                    throw new NonexistentEntityException("The topic with id " + id + " no longer exists.");
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
            Topic topic;
            try {
                topic = em.getReference(Topic.class, id);
                topic.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The topic with id " + id + " no longer exists.", enfe);
            }
            Account idAccount = topic.getIdAccount();
            if (idAccount != null) {
                idAccount.getTopicCollection().remove(topic);
                idAccount = em.merge(idAccount);
            }
            Collection<Comment> commentCollection = topic.getCommentCollection();
            for (Comment commentCollectionComment : commentCollection) {
                commentCollectionComment.setIdTopic(null);
                commentCollectionComment = em.merge(commentCollectionComment);
            }
            em.remove(topic);
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

    public List<Topic> findTopicEntities() {
        return findTopicEntities(true, -1, -1);
    }

    public List<Topic> findTopicEntities(int maxResults, int firstResult) {
        return findTopicEntities(false, maxResults, firstResult);
    }

    private List<Topic> findTopicEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Topic.class));
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

    public Topic findTopic(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Topic.class, id);
        } finally {
            em.close();
        }
    }

    public int getTopicCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Topic> rt = cq.from(Topic.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
