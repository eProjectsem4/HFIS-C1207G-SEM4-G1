package wtw.entities;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class ReportEntity {
    private int no;
    private String name;
    private String category;
    private Date starddate;
    private String status;
    private float price;

    public ReportEntity(int no, String name, String category, Date starddate, String status, float price) {
        this.no = no;
        this.name = name;
        this.category = category;
        this.starddate = starddate;
        this.status = status;
        this.price = price;
    }

    public ReportEntity() {
    }
    
    
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

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

    public Date getStarddate() {
        return starddate;
    }

    public void setStarddate(Date starddate) {
        this.starddate = starddate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
}
