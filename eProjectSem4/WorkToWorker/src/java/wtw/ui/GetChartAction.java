/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.ui;

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
    private JFreeChart chart;

    @Override
    public String execute() throws Exception {
        String datevalue = "2014-07-10";
        Date date;
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        date = (Date) formater.parse(datevalue);

        all = projectManager.getCountProjectbyStartDate(date);
        mobile = projectManager.getCountProjectbyStartDateAndCategory(date, " Mobile");
        web = projectManager.getCountProjectbyStartDateAndCategory(date, " Website");
        software = projectManager.getCountProjectbyStartDateAndCategory(date, " Software");
        design = projectManager.getCountProjectbyStartDateAndCategory(date, " Design");
        data = projectManager.getCountProjectbyStartDateAndCategory(date, " Data Entry");
        other = projectManager.getCountProjectbyStartDateAndCategory(date, " Other");
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
