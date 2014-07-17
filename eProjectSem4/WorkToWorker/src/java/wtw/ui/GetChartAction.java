/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.ui;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import wtw.biz.ProjectManager;

/**
 *
 * @author Admin
 */
public class GetChartAction extends ActionSupport {

    ProjectManager projectManager = lookupProjectManagerBean();

    private float all;
    private float mobile;
    private float web;
    private float design;
    private float data;
    private float other;
    private float software;
    private String startdate;
    private String enddate;
    private JFreeChart chart;

    @Override
    public String execute() throws Exception {
        Object start = ActionContext.getContext().getSession().get("start");
        Object end = ActionContext.getContext().getSession().get("end");
        if (start != null && end != null) {
            startdate = (String) start;
            enddate = (String) end;
        }
        if (start == null || end == null) {
            all = 6;
            mobile = 1;
            web = 1;
            software = 1;
            design = 1;
            data = 1;
            other = 1;
        } else {
            Date date2;
            Date date;
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            date = (Date) formater.parse(startdate);
            date2 = (Date) formater.parse(enddate);
            all = projectManager.getCountProjectInMonth(date, date2);
            mobile = projectManager.getCountProjectInMonthAndCategory(date, date2, " Mobile");
            web = projectManager.getCountProjectInMonthAndCategory(date, date2, " Website");
            software = projectManager.getCountProjectInMonthAndCategory(date, date2, " Software");
            design = projectManager.getCountProjectInMonthAndCategory(date, date2, " Design");
            data = projectManager.getCountProjectInMonthAndCategory(date, date2, " Data Entry");
            other = projectManager.getCountProjectInMonthAndCategory(date, date2, " Other");
        }
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.setValue(all, "Population", "Total");
        dataSet.setValue(mobile, "Population", "Mobile");
        dataSet.setValue(web, "Population", "Website");
        dataSet.setValue(software, "Population", "Software");
        dataSet.setValue(data, "Population", "Data Entry");
        dataSet.setValue(design, "Population", "Design");
        dataSet.setValue(other, "Population", "Other");

        chart = ChartFactory.createBarChart3D(
                "Report Quantity Project By Category", "Project Category", "Quantity of Project",
                dataSet, PlotOrientation.VERTICAL, false, true, false);
        return SUCCESS;
    }

    public JFreeChart getChart() {
        return chart;
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
