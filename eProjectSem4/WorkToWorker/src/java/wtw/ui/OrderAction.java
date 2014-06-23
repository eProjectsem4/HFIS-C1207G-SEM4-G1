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
import wtw.biz.OrderManager;
import wtw.entities.Account;
import wtw.entities.OrderProject;
import wtw.entities.Project;

/**
 *
 * @author Khanh
 */
public class OrderAction extends ActionSupport{
    OrderManager orderManager = lookupOrderManagerBean();
    
    private Project project;
    private Account accLog;
    private List<String> messError;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Account getAccLog() {
        return accLog;
    }

    public void setAccLog(Account accLog) {
        this.accLog = accLog;
    }

    public List<String> getMessError() {
        return messError;
    }

    public void setMessError(List<String> messError) {
        this.messError = messError;
    }
    
    

    @Override
    public String execute() throws Exception {
        messError = new ArrayList<String>();
        accLog = (Account) ActionContext.getContext().getSession().get("accLog");
        project = (Project) ActionContext.getContext().getSession().get("project");
        if(project.getIdWorker() != null){
            messError.add("Worker already exists");
        }
        if(project.getStatus().equals("Done")){
            messError.add("Project done");
        }
        if(!accLog.getRole().equals("Worker")){
            messError.add("You are not a worker");
        }
        
        if(orderManager.getByIdAccPro(accLog, project).size() > 0){
            messError.add("you ordered");
        }
        
        if(messError.size() > 0){
            return "success";
        }
                
        OrderProject order = new OrderProject();
        order.setIdAccount(accLog);
        order.setIdProject(project);
        orderManager.createOrder(order);
        messError.add("Success!");
        return "success";
    }

    
    
    private OrderManager lookupOrderManagerBean() {
        try {
            Context c = new InitialContext();
            return (OrderManager) c.lookup("java:global/WorkToWorker/OrderManager!wtw.biz.OrderManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
