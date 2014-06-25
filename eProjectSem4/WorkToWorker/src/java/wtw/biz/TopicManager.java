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
import wtw.da.TopicJpaController;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Topic;

/**
 *
 * @author Khanh
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TopicManager {

    @PersistenceUnit(unitName = "WorkToWorkerPU")
    private EntityManagerFactory emf;
    
    @Resource
    private UserTransaction utx;
    
    private TopicJpaController daController;
    
    private TopicJpaController getController(){
        if(daController == null){
            daController = new TopicJpaController(utx, emf);
        }
        return daController;
    }
    
    public List<Topic> getAll(){
        return getController().getAll();
    }
    
    public List<Topic> getById(int id){
        return getController().getById(id);
    }
    
    public void create(Topic tp){
        try {
            getController().create(tp);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(TopicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TopicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
