/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import wtw.biz.OrderManager;
import wtw.entities.OrderProject;
import wtw.entities.Project;

/**
 *
 * @author Khanh
 */
public class ShowOrderAction extends ActionSupport{
    OrderManager orderManager = lookupOrderManagerBean();
    
    
    private List<OrderProject> listOrder;

    public List<OrderProject> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<OrderProject> listOrder) {
        this.listOrder = listOrder;
    }

    @Override
    public String execute() throws Exception {
        Project project = (Project) ActionContext.getContext().getSession().get("project");
        listOrder = orderManager.getByIdPro(project);
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
