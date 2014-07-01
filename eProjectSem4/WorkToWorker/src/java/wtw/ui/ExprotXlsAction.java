package wtw.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import wtw.entities.ReportEntity;

/**
 *
 * @author Admin
 */
@SuppressWarnings("serial")
public class ExprotXlsAction extends ActionSupport {

    private List<ReportEntity> list = new ArrayList<ReportEntity>();
    private InputStream inputStream;
    private String reportFile;

    public String fetchName() {
        return SUCCESS;
    }

    private List<ReportEntity> fetchList() {
        {
            list.add(new ReportEntity(1, "Ngoc Nam"));
            list.add(new ReportEntity(2, "Ngoc Nam2"));
            list.add(new ReportEntity(3, "Ngoc Nam3"));
            list.add(new ReportEntity(4, "Ngoc Nam4"));
        }
        return list;
    }

    public ExprotXlsAction() {
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
        final String[] errorSource = {"Id", "Name"};
        try {
            Row header = mySheet.createRow(1);
            for (int i = 0; i < errorSource.length; i++) {
                Cell monthCell = header.createCell(i);
                monthCell.setCellValue(errorSource[i]);
            }
            for (ReportEntity report : reportList) {
                myRow = mySheet.createRow(rowNum++);
                myRow.createCell(0).setCellValue(report.getId());
                myRow.createCell(1).setCellValue(report.getName());
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

}
