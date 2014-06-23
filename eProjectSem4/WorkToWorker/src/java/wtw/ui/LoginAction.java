/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigInteger;
import java.security.MessageDigest;
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
public class LoginAction extends ActionSupport {

    AccountManager accountManager = lookupAccountManagerBean();

    private String email;
    private String password;
    private String messLogin;
    private Account accLog;

    @Override
    public String execute() throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(password.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String pwMd5 = bigInt.toString(16);
        try {
            accLog = accountManager.checkLogin(email, pwMd5).get(0);
        }catch(Exception e){
            accLog = null;
            messLogin = "Invalid email or password !";
            return "error";
        }
        ActionContext.getContext().getSession().put("accLog", accLog);
        messLogin = "";
        return "success";
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

    public String getMessLogin() {
        return messLogin;
    }

    public void setMessLogin(String mess) {
        this.messLogin = mess;
    }

    public Account getAccLog() {
        return accLog;
    }

    public void setAccLog(Account accLog) {
        this.accLog = accLog;
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
