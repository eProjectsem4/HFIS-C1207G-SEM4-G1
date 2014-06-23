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
import wtw.da.OrderProjectJpaController;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Account;
import wtw.entities.OrderProject;
import wtw.entities.Project;

/**
 *
 * @author Khanh
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class OrderManager {

    @PersistenceUnit(unitName = "WorkToWorkerPU")
    private EntityManagerFactory emt;

    @Resource
    private UserTransaction utx;

    private OrderProjectJpaController daController;

    private OrderProjectJpaController getController() {
        if (daController == null) {
            daController = new OrderProjectJpaController(utx, emt);
        }
        return daController;
    }

    public void createOrder(OrderProject o) {
        try {
            getController().create(o);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroyOrDer(int id) {
        try {
            getController().destroy(id);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<OrderProject> getByIdAccPro(Account idAcc, Project idPro) {
        return getController().getByIdAccPro(idAcc, idPro);
    }

    public List<OrderProject> getByIdAcc(Account idAcc) {
        return getController().getByIdAcc(idAcc);
    }

    public List<OrderProject> getByIdPro(Project idPro) {
        return getController().getByIdPro(idPro);
    }

    public void destroy(int id) {
        try {
            getController().destroy(id);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
