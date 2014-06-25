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
import wtw.biz.CommentManager;
import wtw.biz.TopicManager;
import wtw.entities.Comment;
import wtw.entities.Topic;

/**
 *
 * @author Khanh
 */
public class ShowTopicAction extends ActionSupport{
    CommentManager commentManager = lookupCommentManagerBean();
    TopicManager topicManager = lookupTopicManagerBean();
    
    private String id;
    private Topic tp;
    private List<Comment> listComment;

    @Override
    public String execute() throws Exception {
        tp = topicManager.getById(Integer.parseInt(id)).get(0);
        listComment = commentManager.getByIdTopic(tp);
        return "success";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public Topic getTp() {
        return tp;
    }

    public void setTp(Topic tp) {
        this.tp = tp;
    }

    public List<Comment> getListComment() {
        return listComment;
    }

    public void setListComment(List<Comment> listComment) {
        this.listComment = listComment;
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

    private CommentManager lookupCommentManagerBean() {
        try {
            Context c = new InitialContext();
            return (CommentManager) c.lookup("java:global/WorkToWorker/CommentManager!wtw.biz.CommentManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}
