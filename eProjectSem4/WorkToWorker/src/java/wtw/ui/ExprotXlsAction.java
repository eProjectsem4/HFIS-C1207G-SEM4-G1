package wtw.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import wtw.biz.ProjectManager;
import wtw.entities.Project;
import wtw.entities.ReportEntity;

/**
 *
 * @author Admin
 */
@SuppressWarnings("serial")
public class ExprotXlsAction extends ActionSupport {

    ProjectManager projectManager = lookupProjectManagerBean();
    private Date date;
    private Date date2;
    private List<ReportEntity> list = new ArrayList<ReportEntity>();
    private InputStream inputStream;
    private String reportFile;
    private String startdate;
    private String enddate;
    private List<Project> listProject = new ArrayList<Project>();

    public String fetchName() {
        return SUCCESS;
    }

    public ExprotXlsAction() {
        try {
            Object start = ActionContext.getContext().getSession().get("start");
            Object end = ActionContext.getContext().getSession().get("end");
            if (start != null && end != null) {
                startdate = (String) start;
                enddate = (String) end;
            }
           
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            date = (Date) formater.parse(startdate);
            date2 = (Date) formater.parse(enddate);
            listProject = projectManager.getProjectInMonth(date, date2);
        } catch (ParseException ex) {
            Logger.getLogger(ExprotXlsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<ReportEntity> fetchList() {
        {
            int no = 0;
            for (int i = 0; i < listProject.size(); i++) {
                no++;
                list.add(new ReportEntity(no, listProject.get(i).getName(), listProject.get(i).getCategory(), listProject.get(i).getStartDate(), listProject.get(i).getStatus(), listProject.get(i).getPrice()));
            }
        }
        return list;
    }

    public String export() {
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet();
        try {
            Calendar calendar = Calendar.getInstance();
            List<ReportEntity> listF = fetchList();
            reportFile = "Report " + calendar.get(Calendar.YEAR) + "".concat(".xls");
            Row headerRow = mySheet.createRow(0);
            headerRow.setHeightInPoints(50);
            Cell titleCell = headerRow.createCell(0);
            titleCell.setCellValue("Report" + calendar.get(Calendar.YEAR) + "");
            setStudentAllInfo(mySheet, listF);
            try {
                ByteArrayOutputStream boas = new ByteArrayOutputStream();
                myWorkBook.write(boas);
                setInputStream(new ByteArrayInputStream(boas.toByteArray()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    private void setStudentAllInfo(HSSFSheet mySheet,
            List<ReportEntity> reportList) {
        int rowNum = 2;
        HSSFRow myRow = null;
        final String[] errorSource = {"No", "Name", "Category", "Startdate", "Status", "Price"};
        try {
            Row header = mySheet.createRow(1);
            for (int i = 0; i < errorSource.length; i++) {
                Cell monthCell = header.createCell(i);
                monthCell.setCellValue(errorSource[i]);
            }
            for (ReportEntity report : reportList) {
                myRow = mySheet.createRow(rowNum++);
                myRow.createCell(0).setCellValue(report.getNo());
                myRow.createCell(1).setCellValue(report.getName());
                myRow.createCell(2).setCellValue(report.getCategory());
                myRow.createCell(3).setCellValue(report.getStarddate());
                myRow.createCell(4).setCellValue(report.getStatus());
                myRow.createCell(5).setCellValue(report.getPrice());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<ReportEntity> getList() {
        return list;
    }

    public void setList(List<ReportEntity> list) {
        this.list = list;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public List<Project> getListProject() {
        return listProject;
    }

    public void setListProject(List<Project> listProject) {
        this.listProject = listProject;
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
