/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import wtw.biz.AccountManager;
import wtw.biz.FeedCustomerManager;
import wtw.biz.FeedWorkerManager;
import wtw.biz.RateCustomerManager;
import wtw.biz.RateWorkerManager;
import wtw.entities.Account;
import wtw.entities.FeedCustomer;
import wtw.entities.FeedWorker;

/**
 *
 * @author Khanh
 */
public class FeedBackAction extends ActionSupport{
    
     RateWorkerManager rateWorkerManager = lookupRateWorkerManagerBean();
    RateCustomerManager rateCustomerManager = lookupRateCustomerManagerBean();
    FeedWorkerManager feedWorkerManager = lookupFeedWorkerManagerBean();
    FeedCustomerManager feedCustomerManager = lookupFeedCustomerManagerBean();
    AccountManager accountManager = lookupAccountManagerBean();
    
    
    
    private String id;
    private Account accPro;
    private float star;
    private List<FeedCustomer> listFeedCustomer;
    private List<FeedWorker> listFeedWorker;
    private String content;
    private List<String> messFeed;

    @Override
    public String execute() throws Exception {
        messFeed = new ArrayList<String>();
        accPro = accountManager.getById(Integer.parseInt(id));
        Account accLog = (Account) ActionContext.getContext().getSession().get("accLog");
        ActionContext.getContext().getSession().put("accPro", accPro);
        
         if (accLog.getId() == accPro.getId() || accLog.getRole().equals(accPro.getRole())) {
            messFeed.add("Not Permission");
        }
         if(content.length() > 500 || content.length() <= 0){
             messFeed.add("Text is invalid (0-500 characters)");
         }
         if(messFeed.size() > 0){
             return "success";
         }
         
         if(accLog.getRole().equals("Customer")){
             FeedWorker fw = new FeedWorker();
             fw.setContent(content);
             fw.setIdCustomer(accLog);
             fw.setIdWorker(accPro);
             feedWorkerManager.createFeedWorker(fw);
         }
         
         if(accLog.getRole().equals("Worker")){
             FeedCustomer fc = new FeedCustomer();
             fc.setContent(content);
             fc.setIdCustomer(accPro);
             fc.setIdWorker(accLog);
             feedCustomerManager.createFeedCustomer(fc);
         }
        
        if(accPro.getRole().equals("Customer")){
            star = rateCustomerManager.starCustomer(accPro);
            listFeedCustomer = feedCustomerManager.getFeedCustomer(accPro);
        }
        if(accPro.getRole().equals("Worker")){
            star = rateWorkerManager.starWorker(accPro);
            listFeedWorker = feedWorkerManager.getFeedWorker(accPro);
        }
        return "success";
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccPro() {
        return accPro;
    }

    public void setAccPro(Account accPro) {
        this.accPro = accPro;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public List<FeedCustomer> getListFeedCustomer() {
        return listFeedCustomer;
    }

    public void setListFeedCustomer(List<FeedCustomer> listFeedCustomer) {
        this.listFeedCustomer = listFeedCustomer;
    }

    public List<FeedWorker> getListFeedWorker() {
        return listFeedWorker;
    }

    public void setListFeedWorker(List<FeedWorker> listFeedWorker) {
        this.listFeedWorker = listFeedWorker;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getMessFeed() {
        return messFeed;
    }

    public void setMessFeed(List<String> messFeed) {
        this.messFeed = messFeed;
    }
    
    

    private AccountManager lookupAccountManagerBean() {
        try {
            Context c = new InitialContext();
            return (AccountManager) c.lookup("java:global/WorkToWorker/AccountManager!wtw.biz.AccountManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private FeedCustomerManager lookupFeedCustomerManagerBean() {
        try {
            Context c = new InitialContext();
            return (FeedCustomerManager) c.lookup("java:global/WorkToWorker/FeedCustomerManager!wtw.biz.FeedCustomerManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private FeedWorkerManager lookupFeedWorkerManagerBean() {
        try {
            Context c = new InitialContext();
            return (FeedWorkerManager) c.lookup("java:global/WorkToWorker/FeedWorkerManager!wtw.biz.FeedWorkerManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private RateCustomerManager lookupRateCustomerManagerBean() {
        try {
            Context c = new InitialContext();
            return (RateCustomerManager) c.lookup("java:global/WorkToWorker/RateCustomerManager!wtw.biz.RateCustomerManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private RateWorkerManager lookupRateWorkerManagerBean() {
        try {
            Context c = new InitialContext();
            return (RateWorkerManager) c.lookup("java:global/WorkToWorker/RateWorkerManager!wtw.biz.RateWorkerManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    

}
