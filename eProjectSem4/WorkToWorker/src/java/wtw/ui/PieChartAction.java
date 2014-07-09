/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.ui;

import com.opensymphony.xwork2.ActionSupport;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Admin
 */
public class PieChartAction extends ActionSupport {
    private JFreeChart chart;
    public PieChartAction() {
    }
    
    @Override
    public String execute() throws Exception {
       DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("China", 19.64);
        dataSet.setValue("India", 17.3);
        dataSet.setValue("United States", 4.54);
        dataSet.setValue("Indonesia", 3.4);
        dataSet.setValue("Brazil", 2.83);
        dataSet.setValue("Pakistan", 2.48);
        dataSet.setValue("Bangladesh", 2.38);
 
        chart = ChartFactory.createPieChart3D(
                "World Population by countries", dataSet, true, true, false);
        return SUCCESS;
    }

    public JFreeChart getChart() {
        return chart;
    }
    
}
