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
import wtw.entities.Account;

/**
 *
 * @author Khanh
 */
public class EditProfileAction extends ActionSupport{
    
    
    private Account accLog;
    private List<String> messEdit;

    public Account getAccLog() {
        return accLog;
    }

    public void setAccLog(Account accLog) {
        this.accLog = accLog;
    }

    public List<String> getMessEdit() {
        return messEdit;
    }

    public void setMessEdit(List<String> messEdit) {
        this.messEdit = messEdit;
    }

    @Override
    public String execute() throws Exception {
        accLog = (Account) ActionContext.getContext().getSession().get("accLog");
        messEdit = new ArrayList<String>();
        
        if(accLog == null){
            messEdit.add("You must login.");
        }
        return "success";
    }
    
    
    
}
