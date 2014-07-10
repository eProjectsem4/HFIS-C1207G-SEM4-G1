/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.ui;

import com.opensymphony.xwork2.ActionSupport;
import java.math.BigInteger;
import java.security.MessageDigest;
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
public class RegisterAction extends ActionSupport{
    AccountManager accountManager = lookupAccountManagerBean();
       
    private String fullName;
    private String email;
    private String password;
    private String repassword;
    private String role;
    
    private List<String> messRegister;

    @Override
    public String execute() throws Exception {
        messRegister = new ArrayList<String>();
        if(!wtw.validate.Validator.checkStringEmpty(new String[]{ fullName,email,password,repassword,role })){
            messRegister.add("You must insert all field");
        }
        if(!wtw.validate.Validator.checkLengthFullName(fullName)){
            messRegister.add("Full Name is invalid (6-50 characters).");
        }
        if(!wtw.validate.Validator.checkLengthPassword(password)){
            messRegister.add("Password is invalid (6-20 characters).");
        }
        if(!wtw.validate.Validator.checkRepassword(repassword, password)){
            messRegister.add("Re-Password not match password.");
        }
        
        if(accountManager.checkEmailExits(email)){
            messRegister.add("Email already exists");
        }
        
        if(messRegister.size() > 0){
            return "error";
        }
        
        MessageDigest digest =MessageDigest.getInstance("MD5");
        digest.reset();
        digest.update(password.getBytes());
        byte[] dBs=digest.digest();
        BigInteger bi =new BigInteger(1,dBs);
        String pasMD5 =bi.toString(16);
        Account account= new Account();
        account.setFullname(fullName);
        account.setEmail(email);
        account.setRole(role);
        account.setPass(pasMD5);
        messRegister= new ArrayList<String>();
        accountManager.createAccount(account);
        messRegister.add("Success !");
        return SUCCESS;
    }
    
    

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getMessRegister() {
        return messRegister;
    }

    public void setMessRegister(List<String> messRegister) {
        this.messRegister = messRegister;
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
