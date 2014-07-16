/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.biz;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import wtw.da.ProjectJpaController;
import wtw.da.exceptions.RollbackFailureException;
import wtw.entities.Account;
import wtw.entities.Project;

/**
 *
 * @author Khanh
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ProjectManager {

    @PersistenceUnit(unitName = "WorkToWorkerPU")
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction utx;

    private ProjectJpaController daController;

    private ProjectJpaController getController() {
        if (daController == null) {
            daController = new ProjectJpaController(utx, emf);
        }
        return daController;
    }

    public List<Project> searchProject(String name,String price , String skills, String category) {
        return getController().findBykeyword(name, price , skills,  category);
    }

    public void createProject(Project p) {
        try {
            getController().create(p);
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Project> getAll() {
        return getController().getAll();
    }

    public List<Project> getByIdCustomer(Account customer) {
        return getController().getByIdCustomer(customer);
    }

    public Project getById(int id) {
        return getController().getById(id);
    }

    public void editProject(Project p) {
        try {
            getController().edit(p);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCountProjectInMonth(Date start, Date end) {
        return getController().getCountProjectInMonth(start, end);
    }

    public int getCountProjectInMonthAndStatus(Date start, Date end, String status) {
        return getController().getCountProjectInMonthAndStatus(start, end, status);
    }

    public int getCountProjectbyStartDate(Date start) {
        return getController().getProjectbyStartDate(start);
    }

    public int getCountProjectbyStartDateAndCategory(Date start, String category) {
        return getController().getProjectbyCategory(start, category);
    }
}
