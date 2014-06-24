/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.biz;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import wtw.da.RateCustomerJpaController;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Account;
import wtw.entities.RateCustomer;

/**
 *
 * @author Khanh
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class RateCustomerManager {

    @PersistenceUnit(unitName = "WorkToWorkerPU")
    private EntityManagerFactory emf;
    
    @Resource
    private UserTransaction utx;
    
    private RateCustomerJpaController daController;
    
    private RateCustomerJpaController getController(){
        if(daController == null){
            daController = new RateCustomerJpaController(utx, emf);
        }
        return daController;
    }
    
    public float starCustomer(Account customer){
        return getController().starCustomer(customer);
    }
    
    public void createStarCustomer(RateCustomer rc){
        try {
            getController().create(rc);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(RateCustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RateCustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public RateCustomer getRated(Account customer,Account worker){
        return getController().getRated(customer, worker);
    }
    
    public void edit(RateCustomer rc){
        try {
            getController().edit(rc);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(RateCustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RateCustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
