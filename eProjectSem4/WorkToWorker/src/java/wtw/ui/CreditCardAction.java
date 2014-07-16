/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import wtw.biz.BalanceManager;
import wtw.entities.Account;

/**
 *
 * @author viet cuong
 */
public class CreditCardAction extends ActionSupport {
    BalanceManager balanceManager = lookupBalanceManagerBean();
    private String creaditCard;
    private String money;
    private String mess;

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }     

    public String getCreaditCard() {
        return creaditCard;
    }

    public void setCreaditCard(String creaditCard) {
        this.creaditCard = creaditCard;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    
    
    
    
    public CreditCardAction() {
    }
    
    public String execute() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        balanceManager.payment((Account)session.get("accLog"), Double.parseDouble(money));
        mess = "Success , you can create project now";
        return SUCCESS;
    }

    private BalanceManager lookupBalanceManagerBean() {
        try {
            Context c = new InitialContext();
            return (BalanceManager) c.lookup("java:global/WorkToWorker/BalanceManager!wtw.biz.BalanceManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}
