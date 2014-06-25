/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.ui;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import wtw.biz.TopicManager;
import wtw.entities.Topic;

/**
 *
 * @author Khanh
 */
public class ForumAction extends ActionSupport{
    TopicManager topicManager = lookupTopicManagerBean();

    public List<Topic> getListTopic() {
        return listTopic;
    }

    public void setListTopic(List<Topic> listTopic) {
        this.listTopic = listTopic;
    }
    
    private List<Topic> listTopic;

    @Override
    public String execute() throws Exception {
        listTopic = topicManager.getAll();
        return "success";
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
