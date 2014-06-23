/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import wtw.biz.ProjectManager;
import wtw.entities.Account;
import wtw.entities.Project;

/**
 *
 * @author Khanh
 */
public class CreateProjectAction extends ActionSupport{
    ProjectManager projectManager = lookupProjectManagerBean();
    
    
    
    private String name;
    private String category;
    private String skills;
    private String description;
    private String price;
    
    private List<String> messCreate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getMessCreate() {
        return messCreate;
    }

    public void setMessCreate(List<String> messCreate) {
        this.messCreate = messCreate;
    }

    
    

    @Override
    public String execute() throws Exception {
        Account accLog = (Account) ActionContext.getContext().getSession().get("accLog");
        messCreate = new ArrayList<String>();
        if(!wtw.validate.Validator.checkStringEmpty(new String[]{name,category,skills,description,price})){
            messCreate.add("You must insert all field");
        }
        
        if(!wtw.validate.Validator.checkLengthName(name)){
            messCreate.add("Name is invalid (0-50 characters).");
        }
        
        if(!wtw.validate.Validator.checkLengthNameSkills(skills)){
            messCreate.add("Skills is invalid (0-100 characters).");
        }
        
        if(!wtw.validate.Validator.checkLengthDesctiption(description)){
            messCreate.add("Description is invalid (0-500 characters).");
        }
        
        if(!accLog.getRole().equals("Customer")){
            messCreate.add("Not Permission");
        }
        
        if(messCreate.size() > 0){
            return "error";
        }
       
        Project p = new Project();
        p.setName(name);
        p.setCategory(category);
        p.setIdCustomer(accLog);
        p.setNameSkills(skills);
        p.setDescriptions(description);
        p.setPrice(Integer.parseInt(price));
        p.setStartDate(new Date());
        p.setStatus("Started");
        
        projectManager.createProject(p);
        messCreate = new ArrayList<String>();
        messCreate.add("Create success !");
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

    
    
    
}
