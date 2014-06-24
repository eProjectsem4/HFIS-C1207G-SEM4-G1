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
import wtw.da.FeedWorkerJpaController;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Account;
import wtw.entities.FeedWorker;

/**
 *
 * @author Khanh
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FeedWorkerManager {

    @PersistenceUnit(unitName = "WorkToWorkerPU")
    private EntityManagerFactory emf;
    
    @Resource
    private UserTransaction utx;
    
    private FeedWorkerJpaController daController;
    
    private FeedWorkerJpaController getController(){
        if(daController == null){
            daController = new FeedWorkerJpaController(utx, emf);
        }
        return daController;
    }
    
    public List<FeedWorker> getFeedWorker(Account worker){
        return getController().getFeedWorker(worker);
    }
    
    public void createFeedWorker(FeedWorker fw){
        try {
            getController().create(fw);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(FeedWorkerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FeedWorkerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
