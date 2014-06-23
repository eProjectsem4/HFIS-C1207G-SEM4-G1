/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.biz;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import wtw.da.AccountJpaController;
import wtw.entities.Account;

/**
 *
 * @author Khanh
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AccountManager {

    @PersistenceUnit(unitName = "WorkToWorkerPU")
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction utx;

    private AccountJpaController daController;

    private AccountJpaController getController() {
        if (daController == null) {
            daController = new AccountJpaController(utx, emf);
        }
        return daController;
    }

    public List<Account> checkLogin(String email, String pass) throws NoSuchAlgorithmException {
        return getController().checkLogin(email, pass);
    }

    public void createAccount(Account acc) {
        try {
            getController().create(acc);
        } catch (Exception e) {

        }
    }
    
    public boolean checkEmailExits(String email){
        return getController().checkEmailExits(email);
    }
    
    public Account getById(int id){
        return getController().getById(id);
    }

}
