/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.entities;

/**
 *
 * @author Admin
 */
public class ReportDetailsEntity {
    private String category;
    private String title;

    public ReportDetailsEntity(String category, String title) {
        this.category = category;
        this.title = title;
    }

    public ReportDetailsEntity() {
    }

    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
}
