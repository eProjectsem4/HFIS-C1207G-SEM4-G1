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
import org.jfree.data.general.DefaultPieDataset;
import wtw.biz.ProjectManager;

/**
 *
 * @author Admin
 */
public class PieChartAction extends ActionSupport {

    ProjectManager projectManager = lookupProjectManagerBean();
    private float all;
    private float mobile;
    private float web;
    private float design;
    private float data;
    private float other;
    private float software;
    private JFreeChart chart;

    public PieChartAction() {
    }

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
        DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("Mobile "+(mobile/all)*100+"%", (mobile/all)*100);
        dataSet.setValue("Website "+(web/all)*100+"%", (web/all)*100);
        dataSet.setValue("Software "+(software/all)*100+"%", (software/all)*100);
        dataSet.setValue("Design "+(design/all)*100+"%", (design/all)*100);
        dataSet.setValue("Data Entry "+(data/all)*100+"%", (data/all)*100);
        dataSet.setValue("Other "+(other/all)*100+"%", (other/all)*100);

        chart = ChartFactory.createPieChart3D(
                "Project Report By Category", dataSet, true, true, false);
        
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
