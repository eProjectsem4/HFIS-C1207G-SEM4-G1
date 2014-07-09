/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wtw.ui;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author viet cuong
 */
public class LineChartAction extends ActionSupport {

    private JFreeChart chart;

    @Override
    public String execute() throws Exception {

//        DefaultPieDataset dataSet = new DefaultPieDataset();
//        dataSet.setValue("Agriculture", 10);
//        dataSet.setValue("Residential heating", 4);
//        dataSet.setValue("Commercial products", 15);
//        dataSet.setValue("Industry", 42);
//        dataSet.setValue("Transportation", 26);
//        dataSet.setValue("Others", 3);
//
//        chart = ChartFactory.createPieChart3D(
//                "Source of Air Pollution ", // Title
//                dataSet, // Data
//                true, // Display the legend
//                true, // Display tool tips
//                false // No URLs
//        );

//        chart.setBorderVisible(true);
//        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
//        dataSet.setValue(791, "Population", "1750 AD");
//        dataSet.setValue(978, "Population", "1800 AD");
//        dataSet.setValue(1262, "Population", "1850 AD");
//        dataSet.setValue(1650, "Population", "1900 AD");
//        dataSet.setValue(2519, "Population", "1950 AD");
//        dataSet.setValue(6070, "Population", "2000 AD");
//
//        chart = ChartFactory.createBarChart3D(
//                "World Population growth", "Year", "Population in millions",
//                dataSet, PlotOrientation.VERTICAL, false, true, false);
       ValueAxis xAxis = new NumberAxis("Input Increase");//Ngang
        ValueAxis yAxis = new NumberAxis("Production");//Dọc
        XYSeries xySeries = new XYSeries(new Integer(1));
        xySeries.add(0, 791);// dữ liệu truyền vào
        xySeries.add(1, 978);
        xySeries.add(2, 1262);
        xySeries.add(3, 1650);
        xySeries.add(4, 2519);
        xySeries.add(5, 6070);
        XYSeriesCollection xyDataset
                = new XYSeriesCollection(xySeries);
        // create XYPlot
        XYPlot xyPlot = new XYPlot(xyDataset, xAxis, yAxis, new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES_AND_LINES));
        chart = new JFreeChart(xyPlot);
        return SUCCESS;
    }

    // This method will get called if we specify <param name="value">chart</param>
    public JFreeChart getChart() {
        return chart;
    }
}
