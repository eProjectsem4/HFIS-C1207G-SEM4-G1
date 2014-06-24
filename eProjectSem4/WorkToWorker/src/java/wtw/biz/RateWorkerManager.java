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
import wtw.da.RateWorkerJpaController;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Account;
import wtw.entities.RateWorker;

/**
 *
 * @author Khanh
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class RateWorkerManager {

   @PersistenceUnit(unitName = "WorkToWorkerPU")
   private EntityManagerFactory emf;
   
   @Resource
   private UserTransaction utx;
   
   private RateWorkerJpaController daController;
   
   private RateWorkerJpaController getController(){
       if(daController == null){
           daController = new RateWorkerJpaController(utx, emf);
        }
       return daController;
   }
   
   public float starWorker(Account worker){
       return getController().starWorker(worker);
   }
   
   public void createRateWorker(RateWorker rw){
       try {
           getController().create(rw);
       } catch (RollbackFailureException ex) {
           Logger.getLogger(RateWorkerManager.class.getName()).log(Level.SEVERE, null, ex);
       } catch (Exception ex) {
           Logger.getLogger(RateWorkerManager.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public RateWorker getRated(Account customer,Account worker){
       return getController().getRated(customer, worker);
   }
   
   public void edit(RateWorker rw){
       try {
           getController().edit(rw);
       } catch (RollbackFailureException ex) {
           Logger.getLogger(RateWorkerManager.class.getName()).log(Level.SEVERE, null, ex);
       } catch (Exception ex) {
           Logger.getLogger(RateWorkerManager.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}
