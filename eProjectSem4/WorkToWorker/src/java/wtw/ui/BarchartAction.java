/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import wtw.biz.ProjectManager;
import wtw.entities.Project;
import wtw.entities.ReportDetailsEntity;

/**
 *
 * @author Admin
 */
public class BarchartAction extends ActionSupport {

    ProjectManager projectManager = lookupProjectManagerBean();

    public String startdate;
    public String enddate;
    private Map<String, Object> session;
    private List<ReportDetailsEntity> listReport = new ArrayList<ReportDetailsEntity>();
    private List<Project> listProject = new ArrayList<Project>();
    private int totalProject;
    private String titleDate;
    private String total;
    private String title;
    private String listTitle;
    private String mobileS;
    private String webS;
    private String softS;
    private String deS;
    private String daS;
    private String otS;
    private String colum1;
    private String colum2;
    private String colum3;
    private String colum4;

    public BarchartAction() {
    }

    @Override
    public String execute() throws Exception {
        session = ActionContext.getContext().getSession();
        if (startdate == null || enddate == null) {
            if (session != null) {
                startdate = (String) session.get("start");
                enddate = (String) session.get("end");
            }
        } else {
            session.put("start", startdate);
            session.put("end", enddate);
        }
        try {
            Date date;
            Date date2;
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            date = (Date) formater.parse(startdate);
            date2 = (Date) formater.parse(enddate);
            totalProject = projectManager.getCountProjectInMonth(date, date2);
            int all = projectManager.getCountProjectInMonth(date, date2);
            int mobile = projectManager.getCountProjectInMonthAndCategory(date, date2, " Mobile");
            int web = projectManager.getCountProjectInMonthAndCategory(date, date2, " Website");
            int software = projectManager.getCountProjectInMonthAndCategory(date, date2, " Software");
            int design = projectManager.getCountProjectInMonthAndCategory(date, date2, " Design");
            int data = projectManager.getCountProjectInMonthAndCategory(date, date2, " Data Entry");
            int other = projectManager.getCountProjectInMonthAndCategory(date, date2, " Other");
            title = "Mobile Project " + mobile + " Website Project " + web + " SoftWare Project " + software + " Design Project " + design + " Data Project " + data + " Other Project " + other;
            listProject = projectManager.getProjectInMonth(date, date2);
            titleDate = "Details Report  from " + startdate + " to " + enddate + "";
            total = "Total Project: " + totalProject + "p";
            listTitle = "Detail Project";
            mobileS = "Mobile Project :" + mobile + "p - ";
            webS = "Website Project :" + web + "p";
            softS = "SoftWare Project :" + software + "p - ";
            deS = "Design Project :" + design + "p";
            daS = "Data Project :" + data + "p - ";
            otS = "Other Project :" + other + "p";
            colum1 = "Project Name";
            colum2 = "Category";
            colum3 = "Price";
            colum4 = "Status";
        } catch (ParseException ex) {
            Logger.getLogger(BarchartAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public List<ReportDetailsEntity> getListReport() {
        return listReport;
    }

    public void setListReport(List<ReportDetailsEntity> listReport) {
        this.listReport = listReport;
    }

    public List<Project> getListProject() {
        return listProject;
    }

    public void setListProject(List<Project> listProject) {
        this.listProject = listProject;
    }

    public int getTotalProject() {
        return totalProject;
    }

    public void setTotalProject(int totalProject) {
        this.totalProject = totalProject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleDate() {
        return titleDate;
    }

    public void setTitleDate(String titleDate) {
        this.titleDate = titleDate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getMobileS() {
        return mobileS;
    }

    public void setMobileS(String mobileS) {
        this.mobileS = mobileS;
    }

    public String getWebS() {
        return webS;
    }

    public void setWebS(String webS) {
        this.webS = webS;
    }

    public String getSoftS() {
        return softS;
    }

    public void setSoftS(String softS) {
        this.softS = softS;
    }

    public String getDeS() {
        return deS;
    }

    public void setDeS(String deS) {
        this.deS = deS;
    }

    public String getDaS() {
        return daS;
    }

    public void setDaS(String daS) {
        this.daS = daS;
    }

    public String getOtS() {
        return otS;
    }

    public void setOtS(String otS) {
        this.otS = otS;
    }

    public String getColum1() {
        return colum1;
    }

    public void setColum1(String colum1) {
        this.colum1 = colum1;
    }

    public String getColum2() {
        return colum2;
    }

    public void setColum2(String colum2) {
        this.colum2 = colum2;
    }

    public String getColum3() {
        return colum3;
    }

    public void setColum3(String colum3) {
        this.colum3 = colum3;
    }

    public String getColum4() {
        return colum4;
    }

    public void setColum4(String colum4) {
        this.colum4 = colum4;
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
