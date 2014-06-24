/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import wtw.biz.OrderManager;
import wtw.biz.ProjectManager;
import wtw.entities.Account;
import wtw.entities.OrderProject;
import wtw.entities.Project;

/**
 *
 * @author Khanh
 */
public class DoneProjectAction extends ActionSupport {

    OrderManager orderManager = lookupOrderManagerBean();
    ProjectManager projectManager = lookupProjectManagerBean();

    private Project project;
    private List<String> messError;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<String> getMessError() {
        return messError;
    }

    public void setMessError(List<String> messError) {
        this.messError = messError;
    }

    @Override
    public String execute() throws Exception {
        Account accLog = (Account) ActionContext.getContext().getSession().get("accLog");
        project = (Project) ActionContext.getContext().getSession().get("project");
        if (accLog == null || accLog.getId() != project.getIdCustomer().getId()) {
            messError.add("Not Permission");
            return "success";
        }

        project.setStatus("Done");
        project.setEndDate(new Date());
        projectManager.editProject(project);
        List<OrderProject> listOrder = orderManager.getByIdPro(project);
        for (int i = 0; i < listOrder.size(); i++) {
            orderManager.destroy(listOrder.get(i).getId());
        }
        return "success";
    }

    private ProjectManager lookupProjectManagerBean() {
        try {
            Context c = new InitialContext();
            return (ProjectManager) c.lookup("java:global/WorkToWorker/ProjectManager!wtw.biz.ProjectManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
