/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.da;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import wtw.entities.FeedCustomer;
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
import wtw.entities.Account;
import wtw.entities.Comment;
import wtw.entities.Topic;
import wtw.entities.Project;
import wtw.entities.RateCustomer;
import wtw.entities.FeedWorker;
import wtw.entities.OrderProject;
import wtw.entities.RateWorker;

/**
 *
 * @author Khanh
 */
public class AccountJpaController implements Serializable {

    public AccountJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Account getById(int id){
        String queryString = "SELECT a FROM Account a WHERE a.id = :id";
        TypedQuery<Account> query = getEntityManager().createQuery(queryString, Account.class);
        query.setParameter("id", id);
        return query.getResultList().get(0);
    }

    public List<Account> checkLogin(String email, String password) throws NoSuchAlgorithmException {
        String queryString = "SELECT a FROM Account a WHERE a.email = :email And a.pass = :pass";
        TypedQuery<Account> query = getEntityManager().createQuery(queryString, Account.class);
        query.setParameter("email", email);
        query.setParameter("pass", password);
        return query.getResultList();
    }
    
    public boolean checkEmailExits(String email){
         String queryString = "SELECT a FROM Account a WHERE a.email = :email";
        TypedQuery<Account> query = getEntityManager().createQuery(queryString, Account.class);
        query.setParameter("email", email);
        return query.getResultList().size() > 0;
    }

    public void create(Account account) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (account.getFeedCustomerCollection() == null) {
            account.setFeedCustomerCollection(new ArrayList<FeedCustomer>());
        }
        if (account.getFeedCustomerCollection1() == null) {
            account.setFeedCustomerCollection1(new ArrayList<FeedCustomer>());
        }
        if (account.getCommentCollection() == null) {
            account.setCommentCollection(new ArrayList<Comment>());
        }
        if (account.getTopicCollection() == null) {
            account.setTopicCollection(new ArrayList<Topic>());
        }
        if (account.getProjectCollection() == null) {
            account.setProjectCollection(new ArrayList<Project>());
        }
        if (account.getProjectCollection1() == null) {
            account.setProjectCollection1(new ArrayList<Project>());
        }
        if (account.getRateCustomerCollection() == null) {
            account.setRateCustomerCollection(new ArrayList<RateCustomer>());
        }
        if (account.getRateCustomerCollection1() == null) {
            account.setRateCustomerCollection1(new ArrayList<RateCustomer>());
        }
        if (account.getFeedWorkerCollection() == null) {
            account.setFeedWorkerCollection(new ArrayList<FeedWorker>());
        }
        if (account.getFeedWorkerCollection1() == null) {
            account.setFeedWorkerCollection1(new ArrayList<FeedWorker>());
        }
        if (account.getOrderProjectCollection() == null) {
            account.setOrderProjectCollection(new ArrayList<OrderProject>());
        }
        if (account.getRateWorkerCollection() == null) {
            account.setRateWorkerCollection(new ArrayList<RateWorker>());
        }
        if (account.getRateWorkerCollection1() == null) {
            account.setRateWorkerCollection1(new ArrayList<RateWorker>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<FeedCustomer> attachedFeedCustomerCollection = new ArrayList<FeedCustomer>();
            for (FeedCustomer feedCustomerCollectionFeedCustomerToAttach : account.getFeedCustomerCollection()) {
                feedCustomerCollectionFeedCustomerToAttach = em.getReference(feedCustomerCollectionFeedCustomerToAttach.getClass(), feedCustomerCollectionFeedCustomerToAttach.getId());
                attachedFeedCustomerCollection.add(feedCustomerCollectionFeedCustomerToAttach);
            }
            account.setFeedCustomerCollection(attachedFeedCustomerCollection);
            Collection<FeedCustomer> attachedFeedCustomerCollection1 = new ArrayList<FeedCustomer>();
            for (FeedCustomer feedCustomerCollection1FeedCustomerToAttach : account.getFeedCustomerCollection1()) {
                feedCustomerCollection1FeedCustomerToAttach = em.getReference(feedCustomerCollection1FeedCustomerToAttach.getClass(), feedCustomerCollection1FeedCustomerToAttach.getId());
                attachedFeedCustomerCollection1.add(feedCustomerCollection1FeedCustomerToAttach);
            }
            account.setFeedCustomerCollection1(attachedFeedCustomerCollection1);
            Collection<Comment> attachedCommentCollection = new ArrayList<Comment>();
            for (Comment commentCollectionCommentToAttach : account.getCommentCollection()) {
                commentCollectionCommentToAttach = em.getReference(commentCollectionCommentToAttach.getClass(), commentCollectionCommentToAttach.getId());
                attachedCommentCollection.add(commentCollectionCommentToAttach);
            }
            account.setCommentCollection(attachedCommentCollection);
            Collection<Topic> attachedTopicCollection = new ArrayList<Topic>();
            for (Topic topicCollectionTopicToAttach : account.getTopicCollection()) {
                topicCollectionTopicToAttach = em.getReference(topicCollectionTopicToAttach.getClass(), topicCollectionTopicToAttach.getId());
                attachedTopicCollection.add(topicCollectionTopicToAttach);
            }
            account.setTopicCollection(attachedTopicCollection);
            Collection<Project> attachedProjectCollection = new ArrayList<Project>();
            for (Project projectCollectionProjectToAttach : account.getProjectCollection()) {
                projectCollectionProjectToAttach = em.getReference(projectCollectionProjectToAttach.getClass(), projectCollectionProjectToAttach.getId());
                attachedProjectCollection.add(projectCollectionProjectToAttach);
            }
            account.setProjectCollection(attachedProjectCollection);
            Collection<Project> attachedProjectCollection1 = new ArrayList<Project>();
            for (Project projectCollection1ProjectToAttach : account.getProjectCollection1()) {
                projectCollection1ProjectToAttach = em.getReference(projectCollection1ProjectToAttach.getClass(), projectCollection1ProjectToAttach.getId());
                attachedProjectCollection1.add(projectCollection1ProjectToAttach);
            }
            account.setProjectCollection1(attachedProjectCollection1);
            Collection<RateCustomer> attachedRateCustomerCollection = new ArrayList<RateCustomer>();
            for (RateCustomer rateCustomerCollectionRateCustomerToAttach : account.getRateCustomerCollection()) {
                rateCustomerCollectionRateCustomerToAttach = em.getReference(rateCustomerCollectionRateCustomerToAttach.getClass(), rateCustomerCollectionRateCustomerToAttach.getId());
                attachedRateCustomerCollection.add(rateCustomerCollectionRateCustomerToAttach);
            }
            account.setRateCustomerCollection(attachedRateCustomerCollection);
            Collection<RateCustomer> attachedRateCustomerCollection1 = new ArrayList<RateCustomer>();
            for (RateCustomer rateCustomerCollection1RateCustomerToAttach : account.getRateCustomerCollection1()) {
                rateCustomerCollection1RateCustomerToAttach = em.getReference(rateCustomerCollection1RateCustomerToAttach.getClass(), rateCustomerCollection1RateCustomerToAttach.getId());
                attachedRateCustomerCollection1.add(rateCustomerCollection1RateCustomerToAttach);
            }
            account.setRateCustomerCollection1(attachedRateCustomerCollection1);
            Collection<FeedWorker> attachedFeedWorkerCollection = new ArrayList<FeedWorker>();
            for (FeedWorker feedWorkerCollectionFeedWorkerToAttach : account.getFeedWorkerCollection()) {
                feedWorkerCollectionFeedWorkerToAttach = em.getReference(feedWorkerCollectionFeedWorkerToAttach.getClass(), feedWorkerCollectionFeedWorkerToAttach.getId());
                attachedFeedWorkerCollection.add(feedWorkerCollectionFeedWorkerToAttach);
            }
            account.setFeedWorkerCollection(attachedFeedWorkerCollection);
            Collection<FeedWorker> attachedFeedWorkerCollection1 = new ArrayList<FeedWorker>();
            for (FeedWorker feedWorkerCollection1FeedWorkerToAttach : account.getFeedWorkerCollection1()) {
                feedWorkerCollection1FeedWorkerToAttach = em.getReference(feedWorkerCollection1FeedWorkerToAttach.getClass(), feedWorkerCollection1FeedWorkerToAttach.getId());
                attachedFeedWorkerCollection1.add(feedWorkerCollection1FeedWorkerToAttach);
            }
            account.setFeedWorkerCollection1(attachedFeedWorkerCollection1);
            Collection<OrderProject> attachedOrderProjectCollection = new ArrayList<OrderProject>();
            for (OrderProject orderProjectCollectionOrderProjectToAttach : account.getOrderProjectCollection()) {
                orderProjectCollectionOrderProjectToAttach = em.getReference(orderProjectCollectionOrderProjectToAttach.getClass(), orderProjectCollectionOrderProjectToAttach.getId());
                attachedOrderProjectCollection.add(orderProjectCollectionOrderProjectToAttach);
            }
            account.setOrderProjectCollection(attachedOrderProjectCollection);
            Collection<RateWorker> attachedRateWorkerCollection = new ArrayList<RateWorker>();
            for (RateWorker rateWorkerCollectionRateWorkerToAttach : account.getRateWorkerCollection()) {
                rateWorkerCollectionRateWorkerToAttach = em.getReference(rateWorkerCollectionRateWorkerToAttach.getClass(), rateWorkerCollectionRateWorkerToAttach.getId());
                attachedRateWorkerCollection.add(rateWorkerCollectionRateWorkerToAttach);
            }
            account.setRateWorkerCollection(attachedRateWorkerCollection);
            Collection<RateWorker> attachedRateWorkerCollection1 = new ArrayList<RateWorker>();
            for (RateWorker rateWorkerCollection1RateWorkerToAttach : account.getRateWorkerCollection1()) {
                rateWorkerCollection1RateWorkerToAttach = em.getReference(rateWorkerCollection1RateWorkerToAttach.getClass(), rateWorkerCollection1RateWorkerToAttach.getId());
                attachedRateWorkerCollection1.add(rateWorkerCollection1RateWorkerToAttach);
            }
            account.setRateWorkerCollection1(attachedRateWorkerCollection1);
            em.persist(account);
            for (FeedCustomer feedCustomerCollectionFeedCustomer : account.getFeedCustomerCollection()) {
                Account oldIdWorkerOfFeedCustomerCollectionFeedCustomer = feedCustomerCollectionFeedCustomer.getIdWorker();
                feedCustomerCollectionFeedCustomer.setIdWorker(account);
                feedCustomerCollectionFeedCustomer = em.merge(feedCustomerCollectionFeedCustomer);
                if (oldIdWorkerOfFeedCustomerCollectionFeedCustomer != null) {
                    oldIdWorkerOfFeedCustomerCollectionFeedCustomer.getFeedCustomerCollection().remove(feedCustomerCollectionFeedCustomer);
                    oldIdWorkerOfFeedCustomerCollectionFeedCustomer = em.merge(oldIdWorkerOfFeedCustomerCollectionFeedCustomer);
                }
            }
            for (FeedCustomer feedCustomerCollection1FeedCustomer : account.getFeedCustomerCollection1()) {
                Account oldIdCustomerOfFeedCustomerCollection1FeedCustomer = feedCustomerCollection1FeedCustomer.getIdCustomer();
                feedCustomerCollection1FeedCustomer.setIdCustomer(account);
                feedCustomerCollection1FeedCustomer = em.merge(feedCustomerCollection1FeedCustomer);
                if (oldIdCustomerOfFeedCustomerCollection1FeedCustomer != null) {
                    oldIdCustomerOfFeedCustomerCollection1FeedCustomer.getFeedCustomerCollection1().remove(feedCustomerCollection1FeedCustomer);
                    oldIdCustomerOfFeedCustomerCollection1FeedCustomer = em.merge(oldIdCustomerOfFeedCustomerCollection1FeedCustomer);
                }
            }
            for (Comment commentCollectionComment : account.getCommentCollection()) {
                Account oldIdAccountOfCommentCollectionComment = commentCollectionComment.getIdAccount();
                commentCollectionComment.setIdAccount(account);
                commentCollectionComment = em.merge(commentCollectionComment);
                if (oldIdAccountOfCommentCollectionComment != null) {
                    oldIdAccountOfCommentCollectionComment.getCommentCollection().remove(commentCollectionComment);
                    oldIdAccountOfCommentCollectionComment = em.merge(oldIdAccountOfCommentCollectionComment);
                }
            }
            for (Topic topicCollectionTopic : account.getTopicCollection()) {
                Account oldIdAccountOfTopicCollectionTopic = topicCollectionTopic.getIdAccount();
                topicCollectionTopic.setIdAccount(account);
                topicCollectionTopic = em.merge(topicCollectionTopic);
                if (oldIdAccountOfTopicCollectionTopic != null) {
                    oldIdAccountOfTopicCollectionTopic.getTopicCollection().remove(topicCollectionTopic);
                    oldIdAccountOfTopicCollectionTopic = em.merge(oldIdAccountOfTopicCollectionTopic);
                }
            }
            for (Project projectCollectionProject : account.getProjectCollection()) {
                Account oldIdWorkerOfProjectCollectionProject = projectCollectionProject.getIdWorker();
                projectCollectionProject.setIdWorker(account);
                projectCollectionProject = em.merge(projectCollectionProject);
                if (oldIdWorkerOfProjectCollectionProject != null) {
                    oldIdWorkerOfProjectCollectionProject.getProjectCollection().remove(projectCollectionProject);
                    oldIdWorkerOfProjectCollectionProject = em.merge(oldIdWorkerOfProjectCollectionProject);
                }
            }
            for (Project projectCollection1Project : account.getProjectCollection1()) {
                Account oldIdCustomerOfProjectCollection1Project = projectCollection1Project.getIdCustomer();
                projectCollection1Project.setIdCustomer(account);
                projectCollection1Project = em.merge(projectCollection1Project);
                if (oldIdCustomerOfProjectCollection1Project != null) {
                    oldIdCustomerOfProjectCollection1Project.getProjectCollection1().remove(projectCollection1Project);
                    oldIdCustomerOfProjectCollection1Project = em.merge(oldIdCustomerOfProjectCollection1Project);
                }
            }
            for (RateCustomer rateCustomerCollectionRateCustomer : account.getRateCustomerCollection()) {
                Account oldIdWorkerOfRateCustomerCollectionRateCustomer = rateCustomerCollectionRateCustomer.getIdWorker();
                rateCustomerCollectionRateCustomer.setIdWorker(account);
                rateCustomerCollectionRateCustomer = em.merge(rateCustomerCollectionRateCustomer);
                if (oldIdWorkerOfRateCustomerCollectionRateCustomer != null) {
                    oldIdWorkerOfRateCustomerCollectionRateCustomer.getRateCustomerCollection().remove(rateCustomerCollectionRateCustomer);
                    oldIdWorkerOfRateCustomerCollectionRateCustomer = em.merge(oldIdWorkerOfRateCustomerCollectionRateCustomer);
                }
            }
            for (RateCustomer rateCustomerCollection1RateCustomer : account.getRateCustomerCollection1()) {
                Account oldIdCustomerOfRateCustomerCollection1RateCustomer = rateCustomerCollection1RateCustomer.getIdCustomer();
                rateCustomerCollection1RateCustomer.setIdCustomer(account);
                rateCustomerCollection1RateCustomer = em.merge(rateCustomerCollection1RateCustomer);
                if (oldIdCustomerOfRateCustomerCollection1RateCustomer != null) {
                    oldIdCustomerOfRateCustomerCollection1RateCustomer.getRateCustomerCollection1().remove(rateCustomerCollection1RateCustomer);
                    oldIdCustomerOfRateCustomerCollection1RateCustomer = em.merge(oldIdCustomerOfRateCustomerCollection1RateCustomer);
                }
            }
            for (FeedWorker feedWorkerCollectionFeedWorker : account.getFeedWorkerCollection()) {
                Account oldIdWorkerOfFeedWorkerCollectionFeedWorker = feedWorkerCollectionFeedWorker.getIdWorker();
                feedWorkerCollectionFeedWorker.setIdWorker(account);
                feedWorkerCollectionFeedWorker = em.merge(feedWorkerCollectionFeedWorker);
                if (oldIdWorkerOfFeedWorkerCollectionFeedWorker != null) {
                    oldIdWorkerOfFeedWorkerCollectionFeedWorker.getFeedWorkerCollection().remove(feedWorkerCollectionFeedWorker);
                    oldIdWorkerOfFeedWorkerCollectionFeedWorker = em.merge(oldIdWorkerOfFeedWorkerCollectionFeedWorker);
                }
            }
            for (FeedWorker feedWorkerCollection1FeedWorker : account.getFeedWorkerCollection1()) {
                Account oldIdCustomerOfFeedWorkerCollection1FeedWorker = feedWorkerCollection1FeedWorker.getIdCustomer();
                feedWorkerCollection1FeedWorker.setIdCustomer(account);
                feedWorkerCollection1FeedWorker = em.merge(feedWorkerCollection1FeedWorker);
                if (oldIdCustomerOfFeedWorkerCollection1FeedWorker != null) {
                    oldIdCustomerOfFeedWorkerCollection1FeedWorker.getFeedWorkerCollection1().remove(feedWorkerCollection1FeedWorker);
                    oldIdCustomerOfFeedWorkerCollection1FeedWorker = em.merge(oldIdCustomerOfFeedWorkerCollection1FeedWorker);
                }
            }
            for (OrderProject orderProjectCollectionOrderProject : account.getOrderProjectCollection()) {
                Account oldIdAccountOfOrderProjectCollectionOrderProject = orderProjectCollectionOrderProject.getIdAccount();
                orderProjectCollectionOrderProject.setIdAccount(account);
                orderProjectCollectionOrderProject = em.merge(orderProjectCollectionOrderProject);
                if (oldIdAccountOfOrderProjectCollectionOrderProject != null) {
                    oldIdAccountOfOrderProjectCollectionOrderProject.getOrderProjectCollection().remove(orderProjectCollectionOrderProject);
                    oldIdAccountOfOrderProjectCollectionOrderProject = em.merge(oldIdAccountOfOrderProjectCollectionOrderProject);
                }
            }
            for (RateWorker rateWorkerCollectionRateWorker : account.getRateWorkerCollection()) {
                Account oldIdWorkerOfRateWorkerCollectionRateWorker = rateWorkerCollectionRateWorker.getIdWorker();
                rateWorkerCollectionRateWorker.setIdWorker(account);
                rateWorkerCollectionRateWorker = em.merge(rateWorkerCollectionRateWorker);
                if (oldIdWorkerOfRateWorkerCollectionRateWorker != null) {
                    oldIdWorkerOfRateWorkerCollectionRateWorker.getRateWorkerCollection().remove(rateWorkerCollectionRateWorker);
                    oldIdWorkerOfRateWorkerCollectionRateWorker = em.merge(oldIdWorkerOfRateWorkerCollectionRateWorker);
                }
            }
            for (RateWorker rateWorkerCollection1RateWorker : account.getRateWorkerCollection1()) {
                Account oldIdCustomerOfRateWorkerCollection1RateWorker = rateWorkerCollection1RateWorker.getIdCustomer();
                rateWorkerCollection1RateWorker.setIdCustomer(account);
                rateWorkerCollection1RateWorker = em.merge(rateWorkerCollection1RateWorker);
                if (oldIdCustomerOfRateWorkerCollection1RateWorker != null) {
                    oldIdCustomerOfRateWorkerCollection1RateWorker.getRateWorkerCollection1().remove(rateWorkerCollection1RateWorker);
                    oldIdCustomerOfRateWorkerCollection1RateWorker = em.merge(oldIdCustomerOfRateWorkerCollection1RateWorker);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAccount(account.getId()) != null) {
                throw new PreexistingEntityException("Account " + account + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Account account) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account persistentAccount = em.find(Account.class, account.getId());
            Collection<FeedCustomer> feedCustomerCollectionOld = persistentAccount.getFeedCustomerCollection();
            Collection<FeedCustomer> feedCustomerCollectionNew = account.getFeedCustomerCollection();
            Collection<FeedCustomer> feedCustomerCollection1Old = persistentAccount.getFeedCustomerCollection1();
            Collection<FeedCustomer> feedCustomerCollection1New = account.getFeedCustomerCollection1();
            Collection<Comment> commentCollectionOld = persistentAccount.getCommentCollection();
            Collection<Comment> commentCollectionNew = account.getCommentCollection();
            Collection<Topic> topicCollectionOld = persistentAccount.getTopicCollection();
            Collection<Topic> topicCollectionNew = account.getTopicCollection();
            Collection<Project> projectCollectionOld = persistentAccount.getProjectCollection();
            Collection<Project> projectCollectionNew = account.getProjectCollection();
            Collection<Project> projectCollection1Old = persistentAccount.getProjectCollection1();
            Collection<Project> projectCollection1New = account.getProjectCollection1();
            Collection<RateCustomer> rateCustomerCollectionOld = persistentAccount.getRateCustomerCollection();
            Collection<RateCustomer> rateCustomerCollectionNew = account.getRateCustomerCollection();
            Collection<RateCustomer> rateCustomerCollection1Old = persistentAccount.getRateCustomerCollection1();
            Collection<RateCustomer> rateCustomerCollection1New = account.getRateCustomerCollection1();
            Collection<FeedWorker> feedWorkerCollectionOld = persistentAccount.getFeedWorkerCollection();
            Collection<FeedWorker> feedWorkerCollectionNew = account.getFeedWorkerCollection();
            Collection<FeedWorker> feedWorkerCollection1Old = persistentAccount.getFeedWorkerCollection1();
            Collection<FeedWorker> feedWorkerCollection1New = account.getFeedWorkerCollection1();
            Collection<OrderProject> orderProjectCollectionOld = persistentAccount.getOrderProjectCollection();
            Collection<OrderProject> orderProjectCollectionNew = account.getOrderProjectCollection();
            Collection<RateWorker> rateWorkerCollectionOld = persistentAccount.getRateWorkerCollection();
            Collection<RateWorker> rateWorkerCollectionNew = account.getRateWorkerCollection();
            Collection<RateWorker> rateWorkerCollection1Old = persistentAccount.getRateWorkerCollection1();
            Collection<RateWorker> rateWorkerCollection1New = account.getRateWorkerCollection1();
            Collection<FeedCustomer> attachedFeedCustomerCollectionNew = new ArrayList<FeedCustomer>();
            for (FeedCustomer feedCustomerCollectionNewFeedCustomerToAttach : feedCustomerCollectionNew) {
                feedCustomerCollectionNewFeedCustomerToAttach = em.getReference(feedCustomerCollectionNewFeedCustomerToAttach.getClass(), feedCustomerCollectionNewFeedCustomerToAttach.getId());
                attachedFeedCustomerCollectionNew.add(feedCustomerCollectionNewFeedCustomerToAttach);
            }
            feedCustomerCollectionNew = attachedFeedCustomerCollectionNew;
            account.setFeedCustomerCollection(feedCustomerCollectionNew);
            Collection<FeedCustomer> attachedFeedCustomerCollection1New = new ArrayList<FeedCustomer>();
            for (FeedCustomer feedCustomerCollection1NewFeedCustomerToAttach : feedCustomerCollection1New) {
                feedCustomerCollection1NewFeedCustomerToAttach = em.getReference(feedCustomerCollection1NewFeedCustomerToAttach.getClass(), feedCustomerCollection1NewFeedCustomerToAttach.getId());
                attachedFeedCustomerCollection1New.add(feedCustomerCollection1NewFeedCustomerToAttach);
            }
            feedCustomerCollection1New = attachedFeedCustomerCollection1New;
            account.setFeedCustomerCollection1(feedCustomerCollection1New);
            Collection<Comment> attachedCommentCollectionNew = new ArrayList<Comment>();
            for (Comment commentCollectionNewCommentToAttach : commentCollectionNew) {
                commentCollectionNewCommentToAttach = em.getReference(commentCollectionNewCommentToAttach.getClass(), commentCollectionNewCommentToAttach.getId());
                attachedCommentCollectionNew.add(commentCollectionNewCommentToAttach);
            }
            commentCollectionNew = attachedCommentCollectionNew;
            account.setCommentCollection(commentCollectionNew);
            Collection<Topic> attachedTopicCollectionNew = new ArrayList<Topic>();
            for (Topic topicCollectionNewTopicToAttach : topicCollectionNew) {
                topicCollectionNewTopicToAttach = em.getReference(topicCollectionNewTopicToAttach.getClass(), topicCollectionNewTopicToAttach.getId());
                attachedTopicCollectionNew.add(topicCollectionNewTopicToAttach);
            }
            topicCollectionNew = attachedTopicCollectionNew;
            account.setTopicCollection(topicCollectionNew);
            Collection<Project> attachedProjectCollectionNew = new ArrayList<Project>();
            for (Project projectCollectionNewProjectToAttach : projectCollectionNew) {
                projectCollectionNewProjectToAttach = em.getReference(projectCollectionNewProjectToAttach.getClass(), projectCollectionNewProjectToAttach.getId());
                attachedProjectCollectionNew.add(projectCollectionNewProjectToAttach);
            }
            projectCollectionNew = attachedProjectCollectionNew;
            account.setProjectCollection(projectCollectionNew);
            Collection<Project> attachedProjectCollection1New = new ArrayList<Project>();
            for (Project projectCollection1NewProjectToAttach : projectCollection1New) {
                projectCollection1NewProjectToAttach = em.getReference(projectCollection1NewProjectToAttach.getClass(), projectCollection1NewProjectToAttach.getId());
                attachedProjectCollection1New.add(projectCollection1NewProjectToAttach);
            }
            projectCollection1New = attachedProjectCollection1New;
            account.setProjectCollection1(projectCollection1New);
            Collection<RateCustomer> attachedRateCustomerCollectionNew = new ArrayList<RateCustomer>();
            for (RateCustomer rateCustomerCollectionNewRateCustomerToAttach : rateCustomerCollectionNew) {
                rateCustomerCollectionNewRateCustomerToAttach = em.getReference(rateCustomerCollectionNewRateCustomerToAttach.getClass(), rateCustomerCollectionNewRateCustomerToAttach.getId());
                attachedRateCustomerCollectionNew.add(rateCustomerCollectionNewRateCustomerToAttach);
            }
            rateCustomerCollectionNew = attachedRateCustomerCollectionNew;
            account.setRateCustomerCollection(rateCustomerCollectionNew);
            Collection<RateCustomer> attachedRateCustomerCollection1New = new ArrayList<RateCustomer>();
            for (RateCustomer rateCustomerCollection1NewRateCustomerToAttach : rateCustomerCollection1New) {
                rateCustomerCollection1NewRateCustomerToAttach = em.getReference(rateCustomerCollection1NewRateCustomerToAttach.getClass(), rateCustomerCollection1NewRateCustomerToAttach.getId());
                attachedRateCustomerCollection1New.add(rateCustomerCollection1NewRateCustomerToAttach);
            }
            rateCustomerCollection1New = attachedRateCustomerCollection1New;
            account.setRateCustomerCollection1(rateCustomerCollection1New);
            Collection<FeedWorker> attachedFeedWorkerCollectionNew = new ArrayList<FeedWorker>();
            for (FeedWorker feedWorkerCollectionNewFeedWorkerToAttach : feedWorkerCollectionNew) {
                feedWorkerCollectionNewFeedWorkerToAttach = em.getReference(feedWorkerCollectionNewFeedWorkerToAttach.getClass(), feedWorkerCollectionNewFeedWorkerToAttach.getId());
                attachedFeedWorkerCollectionNew.add(feedWorkerCollectionNewFeedWorkerToAttach);
            }
            feedWorkerCollectionNew = attachedFeedWorkerCollectionNew;
            account.setFeedWorkerCollection(feedWorkerCollectionNew);
            Collection<FeedWorker> attachedFeedWorkerCollection1New = new ArrayList<FeedWorker>();
            for (FeedWorker feedWorkerCollection1NewFeedWorkerToAttach : feedWorkerCollection1New) {
                feedWorkerCollection1NewFeedWorkerToAttach = em.getReference(feedWorkerCollection1NewFeedWorkerToAttach.getClass(), feedWorkerCollection1NewFeedWorkerToAttach.getId());
                attachedFeedWorkerCollection1New.add(feedWorkerCollection1NewFeedWorkerToAttach);
            }
            feedWorkerCollection1New = attachedFeedWorkerCollection1New;
            account.setFeedWorkerCollection1(feedWorkerCollection1New);
            Collection<OrderProject> attachedOrderProjectCollectionNew = new ArrayList<OrderProject>();
            for (OrderProject orderProjectCollectionNewOrderProjectToAttach : orderProjectCollectionNew) {
                orderProjectCollectionNewOrderProjectToAttach = em.getReference(orderProjectCollectionNewOrderProjectToAttach.getClass(), orderProjectCollectionNewOrderProjectToAttach.getId());
                attachedOrderProjectCollectionNew.add(orderProjectCollectionNewOrderProjectToAttach);
            }
            orderProjectCollectionNew = attachedOrderProjectCollectionNew;
            account.setOrderProjectCollection(orderProjectCollectionNew);
            Collection<RateWorker> attachedRateWorkerCollectionNew = new ArrayList<RateWorker>();
            for (RateWorker rateWorkerCollectionNewRateWorkerToAttach : rateWorkerCollectionNew) {
                rateWorkerCollectionNewRateWorkerToAttach = em.getReference(rateWorkerCollectionNewRateWorkerToAttach.getClass(), rateWorkerCollectionNewRateWorkerToAttach.getId());
                attachedRateWorkerCollectionNew.add(rateWorkerCollectionNewRateWorkerToAttach);
            }
            rateWorkerCollectionNew = attachedRateWorkerCollectionNew;
            account.setRateWorkerCollection(rateWorkerCollectionNew);
            Collection<RateWorker> attachedRateWorkerCollection1New = new ArrayList<RateWorker>();
            for (RateWorker rateWorkerCollection1NewRateWorkerToAttach : rateWorkerCollection1New) {
                rateWorkerCollection1NewRateWorkerToAttach = em.getReference(rateWorkerCollection1NewRateWorkerToAttach.getClass(), rateWorkerCollection1NewRateWorkerToAttach.getId());
                attachedRateWorkerCollection1New.add(rateWorkerCollection1NewRateWorkerToAttach);
            }
            rateWorkerCollection1New = attachedRateWorkerCollection1New;
            account.setRateWorkerCollection1(rateWorkerCollection1New);
            account = em.merge(account);
            for (FeedCustomer feedCustomerCollectionOldFeedCustomer : feedCustomerCollectionOld) {
                if (!feedCustomerCollectionNew.contains(feedCustomerCollectionOldFeedCustomer)) {
                    feedCustomerCollectionOldFeedCustomer.setIdWorker(null);
                    feedCustomerCollectionOldFeedCustomer = em.merge(feedCustomerCollectionOldFeedCustomer);
                }
            }
            for (FeedCustomer feedCustomerCollectionNewFeedCustomer : feedCustomerCollectionNew) {
                if (!feedCustomerCollectionOld.contains(feedCustomerCollectionNewFeedCustomer)) {
                    Account oldIdWorkerOfFeedCustomerCollectionNewFeedCustomer = feedCustomerCollectionNewFeedCustomer.getIdWorker();
                    feedCustomerCollectionNewFeedCustomer.setIdWorker(account);
                    feedCustomerCollectionNewFeedCustomer = em.merge(feedCustomerCollectionNewFeedCustomer);
                    if (oldIdWorkerOfFeedCustomerCollectionNewFeedCustomer != null && !oldIdWorkerOfFeedCustomerCollectionNewFeedCustomer.equals(account)) {
                        oldIdWorkerOfFeedCustomerCollectionNewFeedCustomer.getFeedCustomerCollection().remove(feedCustomerCollectionNewFeedCustomer);
                        oldIdWorkerOfFeedCustomerCollectionNewFeedCustomer = em.merge(oldIdWorkerOfFeedCustomerCollectionNewFeedCustomer);
                    }
                }
            }
            for (FeedCustomer feedCustomerCollection1OldFeedCustomer : feedCustomerCollection1Old) {
                if (!feedCustomerCollection1New.contains(feedCustomerCollection1OldFeedCustomer)) {
                    feedCustomerCollection1OldFeedCustomer.setIdCustomer(null);
                    feedCustomerCollection1OldFeedCustomer = em.merge(feedCustomerCollection1OldFeedCustomer);
                }
            }
            for (FeedCustomer feedCustomerCollection1NewFeedCustomer : feedCustomerCollection1New) {
                if (!feedCustomerCollection1Old.contains(feedCustomerCollection1NewFeedCustomer)) {
                    Account oldIdCustomerOfFeedCustomerCollection1NewFeedCustomer = feedCustomerCollection1NewFeedCustomer.getIdCustomer();
                    feedCustomerCollection1NewFeedCustomer.setIdCustomer(account);
                    feedCustomerCollection1NewFeedCustomer = em.merge(feedCustomerCollection1NewFeedCustomer);
                    if (oldIdCustomerOfFeedCustomerCollection1NewFeedCustomer != null && !oldIdCustomerOfFeedCustomerCollection1NewFeedCustomer.equals(account)) {
                        oldIdCustomerOfFeedCustomerCollection1NewFeedCustomer.getFeedCustomerCollection1().remove(feedCustomerCollection1NewFeedCustomer);
                        oldIdCustomerOfFeedCustomerCollection1NewFeedCustomer = em.merge(oldIdCustomerOfFeedCustomerCollection1NewFeedCustomer);
                    }
                }
            }
            for (Comment commentCollectionOldComment : commentCollectionOld) {
                if (!commentCollectionNew.contains(commentCollectionOldComment)) {
                    commentCollectionOldComment.setIdAccount(null);
                    commentCollectionOldComment = em.merge(commentCollectionOldComment);
                }
            }
            for (Comment commentCollectionNewComment : commentCollectionNew) {
                if (!commentCollectionOld.contains(commentCollectionNewComment)) {
                    Account oldIdAccountOfCommentCollectionNewComment = commentCollectionNewComment.getIdAccount();
                    commentCollectionNewComment.setIdAccount(account);
                    commentCollectionNewComment = em.merge(commentCollectionNewComment);
                    if (oldIdAccountOfCommentCollectionNewComment != null && !oldIdAccountOfCommentCollectionNewComment.equals(account)) {
                        oldIdAccountOfCommentCollectionNewComment.getCommentCollection().remove(commentCollectionNewComment);
                        oldIdAccountOfCommentCollectionNewComment = em.merge(oldIdAccountOfCommentCollectionNewComment);
                    }
                }
            }
            for (Topic topicCollectionOldTopic : topicCollectionOld) {
                if (!topicCollectionNew.contains(topicCollectionOldTopic)) {
                    topicCollectionOldTopic.setIdAccount(null);
                    topicCollectionOldTopic = em.merge(topicCollectionOldTopic);
                }
            }
            for (Topic topicCollectionNewTopic : topicCollectionNew) {
                if (!topicCollectionOld.contains(topicCollectionNewTopic)) {
                    Account oldIdAccountOfTopicCollectionNewTopic = topicCollectionNewTopic.getIdAccount();
                    topicCollectionNewTopic.setIdAccount(account);
                    topicCollectionNewTopic = em.merge(topicCollectionNewTopic);
                    if (oldIdAccountOfTopicCollectionNewTopic != null && !oldIdAccountOfTopicCollectionNewTopic.equals(account)) {
                        oldIdAccountOfTopicCollectionNewTopic.getTopicCollection().remove(topicCollectionNewTopic);
                        oldIdAccountOfTopicCollectionNewTopic = em.merge(oldIdAccountOfTopicCollectionNewTopic);
                    }
                }
            }
            for (Project projectCollectionOldProject : projectCollectionOld) {
                if (!projectCollectionNew.contains(projectCollectionOldProject)) {
                    projectCollectionOldProject.setIdWorker(null);
                    projectCollectionOldProject = em.merge(projectCollectionOldProject);
                }
            }
            for (Project projectCollectionNewProject : projectCollectionNew) {
                if (!projectCollectionOld.contains(projectCollectionNewProject)) {
                    Account oldIdWorkerOfProjectCollectionNewProject = projectCollectionNewProject.getIdWorker();
                    projectCollectionNewProject.setIdWorker(account);
                    projectCollectionNewProject = em.merge(projectCollectionNewProject);
                    if (oldIdWorkerOfProjectCollectionNewProject != null && !oldIdWorkerOfProjectCollectionNewProject.equals(account)) {
                        oldIdWorkerOfProjectCollectionNewProject.getProjectCollection().remove(projectCollectionNewProject);
                        oldIdWorkerOfProjectCollectionNewProject = em.merge(oldIdWorkerOfProjectCollectionNewProject);
                    }
                }
            }
            for (Project projectCollection1OldProject : projectCollection1Old) {
                if (!projectCollection1New.contains(projectCollection1OldProject)) {
                    projectCollection1OldProject.setIdCustomer(null);
                    projectCollection1OldProject = em.merge(projectCollection1OldProject);
                }
            }
            for (Project projectCollection1NewProject : projectCollection1New) {
                if (!projectCollection1Old.contains(projectCollection1NewProject)) {
                    Account oldIdCustomerOfProjectCollection1NewProject = projectCollection1NewProject.getIdCustomer();
                    projectCollection1NewProject.setIdCustomer(account);
                    projectCollection1NewProject = em.merge(projectCollection1NewProject);
                    if (oldIdCustomerOfProjectCollection1NewProject != null && !oldIdCustomerOfProjectCollection1NewProject.equals(account)) {
                        oldIdCustomerOfProjectCollection1NewProject.getProjectCollection1().remove(projectCollection1NewProject);
                        oldIdCustomerOfProjectCollection1NewProject = em.merge(oldIdCustomerOfProjectCollection1NewProject);
                    }
                }
            }
            for (RateCustomer rateCustomerCollectionOldRateCustomer : rateCustomerCollectionOld) {
                if (!rateCustomerCollectionNew.contains(rateCustomerCollectionOldRateCustomer)) {
                    rateCustomerCollectionOldRateCustomer.setIdWorker(null);
                    rateCustomerCollectionOldRateCustomer = em.merge(rateCustomerCollectionOldRateCustomer);
                }
            }
            for (RateCustomer rateCustomerCollectionNewRateCustomer : rateCustomerCollectionNew) {
                if (!rateCustomerCollectionOld.contains(rateCustomerCollectionNewRateCustomer)) {
                    Account oldIdWorkerOfRateCustomerCollectionNewRateCustomer = rateCustomerCollectionNewRateCustomer.getIdWorker();
                    rateCustomerCollectionNewRateCustomer.setIdWorker(account);
                    rateCustomerCollectionNewRateCustomer = em.merge(rateCustomerCollectionNewRateCustomer);
                    if (oldIdWorkerOfRateCustomerCollectionNewRateCustomer != null && !oldIdWorkerOfRateCustomerCollectionNewRateCustomer.equals(account)) {
                        oldIdWorkerOfRateCustomerCollectionNewRateCustomer.getRateCustomerCollection().remove(rateCustomerCollectionNewRateCustomer);
                        oldIdWorkerOfRateCustomerCollectionNewRateCustomer = em.merge(oldIdWorkerOfRateCustomerCollectionNewRateCustomer);
                    }
                }
            }
            for (RateCustomer rateCustomerCollection1OldRateCustomer : rateCustomerCollection1Old) {
                if (!rateCustomerCollection1New.contains(rateCustomerCollection1OldRateCustomer)) {
                    rateCustomerCollection1OldRateCustomer.setIdCustomer(null);
                    rateCustomerCollection1OldRateCustomer = em.merge(rateCustomerCollection1OldRateCustomer);
                }
            }
            for (RateCustomer rateCustomerCollection1NewRateCustomer : rateCustomerCollection1New) {
                if (!rateCustomerCollection1Old.contains(rateCustomerCollection1NewRateCustomer)) {
                    Account oldIdCustomerOfRateCustomerCollection1NewRateCustomer = rateCustomerCollection1NewRateCustomer.getIdCustomer();
                    rateCustomerCollection1NewRateCustomer.setIdCustomer(account);
                    rateCustomerCollection1NewRateCustomer = em.merge(rateCustomerCollection1NewRateCustomer);
                    if (oldIdCustomerOfRateCustomerCollection1NewRateCustomer != null && !oldIdCustomerOfRateCustomerCollection1NewRateCustomer.equals(account)) {
                        oldIdCustomerOfRateCustomerCollection1NewRateCustomer.getRateCustomerCollection1().remove(rateCustomerCollection1NewRateCustomer);
                        oldIdCustomerOfRateCustomerCollection1NewRateCustomer = em.merge(oldIdCustomerOfRateCustomerCollection1NewRateCustomer);
                    }
                }
            }
            for (FeedWorker feedWorkerCollectionOldFeedWorker : feedWorkerCollectionOld) {
                if (!feedWorkerCollectionNew.contains(feedWorkerCollectionOldFeedWorker)) {
                    feedWorkerCollectionOldFeedWorker.setIdWorker(null);
                    feedWorkerCollectionOldFeedWorker = em.merge(feedWorkerCollectionOldFeedWorker);
                }
            }
            for (FeedWorker feedWorkerCollectionNewFeedWorker : feedWorkerCollectionNew) {
                if (!feedWorkerCollectionOld.contains(feedWorkerCollectionNewFeedWorker)) {
                    Account oldIdWorkerOfFeedWorkerCollectionNewFeedWorker = feedWorkerCollectionNewFeedWorker.getIdWorker();
                    feedWorkerCollectionNewFeedWorker.setIdWorker(account);
                    feedWorkerCollectionNewFeedWorker = em.merge(feedWorkerCollectionNewFeedWorker);
                    if (oldIdWorkerOfFeedWorkerCollectionNewFeedWorker != null && !oldIdWorkerOfFeedWorkerCollectionNewFeedWorker.equals(account)) {
                        oldIdWorkerOfFeedWorkerCollectionNewFeedWorker.getFeedWorkerCollection().remove(feedWorkerCollectionNewFeedWorker);
                        oldIdWorkerOfFeedWorkerCollectionNewFeedWorker = em.merge(oldIdWorkerOfFeedWorkerCollectionNewFeedWorker);
                    }
                }
            }
            for (FeedWorker feedWorkerCollection1OldFeedWorker : feedWorkerCollection1Old) {
                if (!feedWorkerCollection1New.contains(feedWorkerCollection1OldFeedWorker)) {
                    feedWorkerCollection1OldFeedWorker.setIdCustomer(null);
                    feedWorkerCollection1OldFeedWorker = em.merge(feedWorkerCollection1OldFeedWorker);
                }
            }
            for (FeedWorker feedWorkerCollection1NewFeedWorker : feedWorkerCollection1New) {
                if (!feedWorkerCollection1Old.contains(feedWorkerCollection1NewFeedWorker)) {
                    Account oldIdCustomerOfFeedWorkerCollection1NewFeedWorker = feedWorkerCollection1NewFeedWorker.getIdCustomer();
                    feedWorkerCollection1NewFeedWorker.setIdCustomer(account);
                    feedWorkerCollection1NewFeedWorker = em.merge(feedWorkerCollection1NewFeedWorker);
                    if (oldIdCustomerOfFeedWorkerCollection1NewFeedWorker != null && !oldIdCustomerOfFeedWorkerCollection1NewFeedWorker.equals(account)) {
                        oldIdCustomerOfFeedWorkerCollection1NewFeedWorker.getFeedWorkerCollection1().remove(feedWorkerCollection1NewFeedWorker);
                        oldIdCustomerOfFeedWorkerCollection1NewFeedWorker = em.merge(oldIdCustomerOfFeedWorkerCollection1NewFeedWorker);
                    }
                }
            }
            for (OrderProject orderProjectCollectionOldOrderProject : orderProjectCollectionOld) {
                if (!orderProjectCollectionNew.contains(orderProjectCollectionOldOrderProject)) {
                    orderProjectCollectionOldOrderProject.setIdAccount(null);
                    orderProjectCollectionOldOrderProject = em.merge(orderProjectCollectionOldOrderProject);
                }
            }
            for (OrderProject orderProjectCollectionNewOrderProject : orderProjectCollectionNew) {
                if (!orderProjectCollectionOld.contains(orderProjectCollectionNewOrderProject)) {
                    Account oldIdAccountOfOrderProjectCollectionNewOrderProject = orderProjectCollectionNewOrderProject.getIdAccount();
                    orderProjectCollectionNewOrderProject.setIdAccount(account);
                    orderProjectCollectionNewOrderProject = em.merge(orderProjectCollectionNewOrderProject);
                    if (oldIdAccountOfOrderProjectCollectionNewOrderProject != null && !oldIdAccountOfOrderProjectCollectionNewOrderProject.equals(account)) {
                        oldIdAccountOfOrderProjectCollectionNewOrderProject.getOrderProjectCollection().remove(orderProjectCollectionNewOrderProject);
                        oldIdAccountOfOrderProjectCollectionNewOrderProject = em.merge(oldIdAccountOfOrderProjectCollectionNewOrderProject);
                    }
                }
            }
            for (RateWorker rateWorkerCollectionOldRateWorker : rateWorkerCollectionOld) {
                if (!rateWorkerCollectionNew.contains(rateWorkerCollectionOldRateWorker)) {
                    rateWorkerCollectionOldRateWorker.setIdWorker(null);
                    rateWorkerCollectionOldRateWorker = em.merge(rateWorkerCollectionOldRateWorker);
                }
            }
            for (RateWorker rateWorkerCollectionNewRateWorker : rateWorkerCollectionNew) {
                if (!rateWorkerCollectionOld.contains(rateWorkerCollectionNewRateWorker)) {
                    Account oldIdWorkerOfRateWorkerCollectionNewRateWorker = rateWorkerCollectionNewRateWorker.getIdWorker();
                    rateWorkerCollectionNewRateWorker.setIdWorker(account);
                    rateWorkerCollectionNewRateWorker = em.merge(rateWorkerCollectionNewRateWorker);
                    if (oldIdWorkerOfRateWorkerCollectionNewRateWorker != null && !oldIdWorkerOfRateWorkerCollectionNewRateWorker.equals(account)) {
                        oldIdWorkerOfRateWorkerCollectionNewRateWorker.getRateWorkerCollection().remove(rateWorkerCollectionNewRateWorker);
                        oldIdWorkerOfRateWorkerCollectionNewRateWorker = em.merge(oldIdWorkerOfRateWorkerCollectionNewRateWorker);
                    }
                }
            }
            for (RateWorker rateWorkerCollection1OldRateWorker : rateWorkerCollection1Old) {
                if (!rateWorkerCollection1New.contains(rateWorkerCollection1OldRateWorker)) {
                    rateWorkerCollection1OldRateWorker.setIdCustomer(null);
                    rateWorkerCollection1OldRateWorker = em.merge(rateWorkerCollection1OldRateWorker);
                }
            }
            for (RateWorker rateWorkerCollection1NewRateWorker : rateWorkerCollection1New) {
                if (!rateWorkerCollection1Old.contains(rateWorkerCollection1NewRateWorker)) {
                    Account oldIdCustomerOfRateWorkerCollection1NewRateWorker = rateWorkerCollection1NewRateWorker.getIdCustomer();
                    rateWorkerCollection1NewRateWorker.setIdCustomer(account);
                    rateWorkerCollection1NewRateWorker = em.merge(rateWorkerCollection1NewRateWorker);
                    if (oldIdCustomerOfRateWorkerCollection1NewRateWorker != null && !oldIdCustomerOfRateWorkerCollection1NewRateWorker.equals(account)) {
                        oldIdCustomerOfRateWorkerCollection1NewRateWorker.getRateWorkerCollection1().remove(rateWorkerCollection1NewRateWorker);
                        oldIdCustomerOfRateWorkerCollection1NewRateWorker = em.merge(oldIdCustomerOfRateWorkerCollection1NewRateWorker);
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
                Integer id = account.getId();
                if (findAccount(id) == null) {
                    throw new NonexistentEntityException("The account with id " + id + " no longer exists.");
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
            Account account;
            try {
                account = em.getReference(Account.class, id);
                account.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The account with id " + id + " no longer exists.", enfe);
            }
            Collection<FeedCustomer> feedCustomerCollection = account.getFeedCustomerCollection();
            for (FeedCustomer feedCustomerCollectionFeedCustomer : feedCustomerCollection) {
                feedCustomerCollectionFeedCustomer.setIdWorker(null);
                feedCustomerCollectionFeedCustomer = em.merge(feedCustomerCollectionFeedCustomer);
            }
            Collection<FeedCustomer> feedCustomerCollection1 = account.getFeedCustomerCollection1();
            for (FeedCustomer feedCustomerCollection1FeedCustomer : feedCustomerCollection1) {
                feedCustomerCollection1FeedCustomer.setIdCustomer(null);
                feedCustomerCollection1FeedCustomer = em.merge(feedCustomerCollection1FeedCustomer);
            }
            Collection<Comment> commentCollection = account.getCommentCollection();
            for (Comment commentCollectionComment : commentCollection) {
                commentCollectionComment.setIdAccount(null);
                commentCollectionComment = em.merge(commentCollectionComment);
            }
            Collection<Topic> topicCollection = account.getTopicCollection();
            for (Topic topicCollectionTopic : topicCollection) {
                topicCollectionTopic.setIdAccount(null);
                topicCollectionTopic = em.merge(topicCollectionTopic);
            }
            Collection<Project> projectCollection = account.getProjectCollection();
            for (Project projectCollectionProject : projectCollection) {
                projectCollectionProject.setIdWorker(null);
                projectCollectionProject = em.merge(projectCollectionProject);
            }
            Collection<Project> projectCollection1 = account.getProjectCollection1();
            for (Project projectCollection1Project : projectCollection1) {
                projectCollection1Project.setIdCustomer(null);
                projectCollection1Project = em.merge(projectCollection1Project);
            }
            Collection<RateCustomer> rateCustomerCollection = account.getRateCustomerCollection();
            for (RateCustomer rateCustomerCollectionRateCustomer : rateCustomerCollection) {
                rateCustomerCollectionRateCustomer.setIdWorker(null);
                rateCustomerCollectionRateCustomer = em.merge(rateCustomerCollectionRateCustomer);
            }
            Collection<RateCustomer> rateCustomerCollection1 = account.getRateCustomerCollection1();
            for (RateCustomer rateCustomerCollection1RateCustomer : rateCustomerCollection1) {
                rateCustomerCollection1RateCustomer.setIdCustomer(null);
                rateCustomerCollection1RateCustomer = em.merge(rateCustomerCollection1RateCustomer);
            }
            Collection<FeedWorker> feedWorkerCollection = account.getFeedWorkerCollection();
            for (FeedWorker feedWorkerCollectionFeedWorker : feedWorkerCollection) {
                feedWorkerCollectionFeedWorker.setIdWorker(null);
                feedWorkerCollectionFeedWorker = em.merge(feedWorkerCollectionFeedWorker);
            }
            Collection<FeedWorker> feedWorkerCollection1 = account.getFeedWorkerCollection1();
            for (FeedWorker feedWorkerCollection1FeedWorker : feedWorkerCollection1) {
                feedWorkerCollection1FeedWorker.setIdCustomer(null);
                feedWorkerCollection1FeedWorker = em.merge(feedWorkerCollection1FeedWorker);
            }
            Collection<OrderProject> orderProjectCollection = account.getOrderProjectCollection();
            for (OrderProject orderProjectCollectionOrderProject : orderProjectCollection) {
                orderProjectCollectionOrderProject.setIdAccount(null);
                orderProjectCollectionOrderProject = em.merge(orderProjectCollectionOrderProject);
            }
            Collection<RateWorker> rateWorkerCollection = account.getRateWorkerCollection();
            for (RateWorker rateWorkerCollectionRateWorker : rateWorkerCollection) {
                rateWorkerCollectionRateWorker.setIdWorker(null);
                rateWorkerCollectionRateWorker = em.merge(rateWorkerCollectionRateWorker);
            }
            Collection<RateWorker> rateWorkerCollection1 = account.getRateWorkerCollection1();
            for (RateWorker rateWorkerCollection1RateWorker : rateWorkerCollection1) {
                rateWorkerCollection1RateWorker.setIdCustomer(null);
                rateWorkerCollection1RateWorker = em.merge(rateWorkerCollection1RateWorker);
            }
            em.remove(account);
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

    public List<Account> findAccountEntities() {
        return findAccountEntities(true, -1, -1);
    }

    public List<Account> findAccountEntities(int maxResults, int firstResult) {
        return findAccountEntities(false, maxResults, firstResult);
    }

    private List<Account> findAccountEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Account.class));
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

    public Account findAccount(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Account.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccountCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Account> rt = cq.from(Account.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
