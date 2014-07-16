/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.biz;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import wtw.da.BalanceJpaController;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Account;
import wtw.entities.Balance;

/**
 *
 * @author viet cuong
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BalanceManager {

    @PersistenceUnit(unitName = "WorkToWorkerPU")
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction utx;

    private BalanceJpaController daController;

    private BalanceJpaController getController() {
        if (daController == null) {
            daController = new BalanceJpaController(utx, emf);
        }
        return daController;
    }

    public void payment(Account acc, double money) throws RollbackFailureException, Exception {
        Balance balanceByIdAccount = getController().getBalanceByIdAccount(acc);
        if (balanceByIdAccount != null) {
            balanceByIdAccount.setMoney(balanceByIdAccount.getMoney() + money);
            getController().edit(balanceByIdAccount);
        } else {
            Balance newBalance = new Balance();
            newBalance.setIdAccount(acc);
            newBalance.setMoney(money);
            getController().create(newBalance);
        }
    }

    public boolean checkBalance(Account acc) {
        double checkBalance = getController().checkBalance(acc);
        if (checkBalance > 10) {
            return true;
        } else {
            return false;
        }
    }

    public void sold(Account acc, double money) throws RollbackFailureException, Exception {
        Balance balanceByIdAccount = getController().getBalanceByIdAccount(acc);
        balanceByIdAccount.setMoney(balanceByIdAccount.getMoney() - money);
        getController().edit(balanceByIdAccount);
    }
}
