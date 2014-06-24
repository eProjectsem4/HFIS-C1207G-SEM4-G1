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
import wtw.entities.Account;

/**
 *
 * @author Khanh
 */
public class UpdateProfileAction extends ActionSupport{
    AccountManager accountManager = lookupAccountManagerBean();
    
    private Account accLog;
    private String fullname;
    private String phone;
    private String company;
    private String address;
    private String country;
    private List<String> messEdit;

    public Account getAccLog() {
        return accLog;
    }

    public void setAccLog(Account accLog) {
        this.accLog = accLog;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getMessEdit() {
        return messEdit;
    }

    public void setMessUpdate(List<String> messEdit) {
        this.messEdit = messEdit;
    }

    @Override
    public String execute() throws Exception {
        messEdit = new ArrayList<String>();
        accLog = (Account)ActionContext.getContext().getSession().get("accLog");
        if(accLog == null){
            messEdit.add("You must login.");
        }
        if(fullname.length() > 50 || fullname.length() <= 0){
            messEdit.add("Full Name is invalid (0-50 characters).");
        }
        if(phone.length() > 20){
            messEdit.add("Phone is invalid (0-20 characters)");
        }
        if(company.length() > 20){
            messEdit.add("Company is invalid (0-20 characters)");
        }
        if(address.length() > 50){
            messEdit.add("Address is invalid (0-50 characters)");
        }
        if(country.length() > 20){
            messEdit.add("Country is invalid (0-20 characters)");
        }
        
        if(messEdit.size() > 0){
            return "success";
        }
        
        accLog.setFullname(fullname);
        if(phone != null){
            accLog.setPhone(phone);
        }
        if(company != null){
            accLog.setCompany(company);
        }
        if(address != null){
            accLog.setAddresss(address);
        }
        if(country != null){
            accLog.setCountry(country);
        }
        accountManager.edit(accLog);
        messEdit.add("Success !");
        return "success";
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
    
    
}
