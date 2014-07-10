/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import wtw.biz.AccountManager;
import wtw.biz.ProjectManager;
import wtw.entities.Account;
import wtw.entities.Project;

/**
 *
 * @author Khanh
 */
public class DetailsAction extends ActionSupport{
    AccountManager accountManager = lookupAccountManagerBean();
    ProjectManager projectManager = lookupProjectManagerBean();
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Account getAccProject() {
        return accProject;
    }

    public void setAccProject(Account accProject) {
        this.accProject = accProject;
    }
    
    
    private String id;
    private Project project;
    private Account accProject;

    @Override
    public String execute() throws Exception {
        project = projectManager.getById(Integer.parseInt(id));
        accProject = accountManager.getById(project.getIdCustomer().getId());
        ActionContext.getContext().getSession().put("project", project);
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
