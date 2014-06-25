/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.biz;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import wtw.da.CommentJpaController;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Comment;
import wtw.entities.Topic;

/**
 *
 * @author Khanh
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CommentManager {

    @PersistenceUnit(unitName = "WorkToWorkerPU")
    private EntityManagerFactory emf;
    
    @Resource
    private UserTransaction utx;
    
    private CommentJpaController daController;
    
    private CommentJpaController getController(){
        if(daController == null){
            daController = new CommentJpaController(utx, emf);
        }
        return daController;
    }
    
    public List<Comment> getByIdTopic(Topic tp){
        return getController().getByIdTopic(tp);
    }
    
    public void create(Comment cm){
        try {
            getController().create(cm);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(CommentManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CommentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
