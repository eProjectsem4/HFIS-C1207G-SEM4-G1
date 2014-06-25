/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import wtw.biz.TopicManager;
import wtw.entities.Account;
import wtw.entities.Topic;

/**
 *
 * @author Khanh
 */
public class CreateTopicAction extends ActionSupport{
    TopicManager topicManager = lookupTopicManagerBean();
    
    
    private String title;
    private String content;
    private List<String> messCreate;

    @Override
    public String execute() throws Exception {
        messCreate = new ArrayList<String>();
        Account accLog = (Account) ActionContext.getContext().getSession().get("accLog");
        if(accLog == null){
            messCreate.add("You must login.");
        }
        if(title.length() <= 0 || title.length() > 100){
            messCreate.add("Title is invalid (0-100 characters)");
        }
        
        if(content.length() <= 0 || content.length() > 1000){
            messCreate.add("Content is invalid (0-1000 characters)");
        }
        
        if(messCreate.size() > 0){
            return "success";
        }
        Topic tp = new Topic();
        tp.setTitle(title);
        tp.setContent(content);
        tp.setIdAccount(accLog);
        tp.setPostDate(new Date());
        
        topicManager.create(tp);
        messCreate.add("Success !");
        return "success";
    }

    public List<String> getMessCreate() {
        return messCreate;
    }

    public void setMessCreate(List<String> messCreate) {
        this.messCreate = messCreate;
    }

    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private TopicManager lookupTopicManagerBean() {
        try {
            Context c = new InitialContext();
            return (TopicManager) c.lookup("java:global/WorkToWorker/TopicManager!wtw.biz.TopicManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}
