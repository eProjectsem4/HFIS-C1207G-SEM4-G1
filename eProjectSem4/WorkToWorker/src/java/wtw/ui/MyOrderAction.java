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
public class MyOrderAction extends ActionSupport{
    OrderManager orderManager = lookupOrderManagerBean();
    
     private List<Project> listProject;

    public List<Project> getListProject() {
        return listProject;
    }

    public void setListProject(List<Project> listProject) {
        this.listProject = listProject;
    }

    @Override
    public String execute() throws Exception {
        Account accLog = (Account) ActionContext.getContext().getSession().get("accLog");
        List<OrderProject> listOrder = orderManager.getByIdAcc(accLog);
        listProject = new ArrayList<Project>();
        for (int i = 0; i < listOrder.size(); i++) {
            listProject.add(listOrder.get(i).getIdProject());
        }
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
