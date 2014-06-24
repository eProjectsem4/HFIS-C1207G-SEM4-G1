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
import wtw.da.FeedCustomerJpaController;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Account;
import wtw.entities.FeedCustomer;

/**
 *
 * @author Khanh
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FeedCustomerManager {

    @PersistenceUnit(unitName = "WorkToWorkerPU")
    private EntityManagerFactory emf;
    
    @Resource
    private UserTransaction utx;
    
    private FeedCustomerJpaController daController;
    
    private FeedCustomerJpaController getController(){
        if(daController == null){
            daController = new FeedCustomerJpaController(utx, emf);
        }
        return daController;
    }
    
    public List<FeedCustomer> getFeedCustomer(Account customer){
        return getController().getFeedCustomer(customer);
    }
    
    public void createFeedCustomer(FeedCustomer fc){
        try {
            getController().create(fc);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(FeedCustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FeedCustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
