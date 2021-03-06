/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import wtw.biz.BalanceManager;
import wtw.biz.ProjectManager;
import wtw.entities.Account;
import wtw.entities.Project;

/**
 *
 * @author Khanh
 */
public class CreateProjectAction extends ActionSupport implements ServletRequestAware {
    BalanceManager balanceManager = lookupBalanceManagerBean();

    ProjectManager projectManager = lookupProjectManagerBean();

    private String name;
    private String category;
    private String skills;
    private String description;
    private String price;
    private File fileUpload;
    private String fileUploadFileName;
    private String from;
    private String to;

    private HttpServletRequest servletRequest;

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

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    

    @Override
    public String execute() throws Exception {

        Account accLog = (Account) ActionContext.getContext().getSession().get("accLog");
        messCreate = new ArrayList<String>();
        boolean checkBalance = balanceManager.checkBalance(accLog);
        if(!checkBalance) {
            messCreate.add("You not enough money in account to create project ");
        }
        if (!wtw.validate.Validator.checkStringEmpty(new String[]{name, category, skills, description, price})) {
            messCreate.add("You must insert all field");
        }

        if (!wtw.validate.Validator.checkLengthName(name)) {
            messCreate.add("Name is invalid (0-50 characters).");
        }

        if (!wtw.validate.Validator.checkLengthNameSkills(skills)) {
            messCreate.add("Skills is invalid (0-100 characters).");
        }

        if (!wtw.validate.Validator.checkLengthDesctiption(description)) {
            messCreate.add("Description is invalid (0-500 characters).");
        }

        if (!accLog.getRole().equals("Customer")) {
            messCreate.add("Not Permission");
        }

        if (messCreate.size() > 0) {
            return "error";
        }
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Project p = new Project();
        p.setName(name);
        p.setCategory(category);
        p.setIdCustomer(accLog);
        p.setNameSkills(skills);
        p.setDescriptions(description);
        p.setPrice(Integer.parseInt(price));
        p.setStartDate(simpleDateFormat.parse(from));
        p.setEndDate(simpleDateFormat.parse(to));
        p.setStatus("Started");

        if (fileUpload != null) {
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/").concat("fileProject");
            String fileName = name + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-S").format(new Date()) + fileUploadFileName;
            File fileToCreate = new File(filePath, fileName);
            FileUtils.copyFile(this.fileUpload, fileToCreate);//copying image in the new file 
            p.setAttFile("fileProject/" + fileName);
        }

        projectManager.createProject(p);
        balanceManager.sold(accLog, 10);
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

    private BalanceManager lookupBalanceManagerBean() {
        try {
            Context c = new InitialContext();
            return (BalanceManager) c.lookup("java:global/WorkToWorker/BalanceManager!wtw.biz.BalanceManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    
}
