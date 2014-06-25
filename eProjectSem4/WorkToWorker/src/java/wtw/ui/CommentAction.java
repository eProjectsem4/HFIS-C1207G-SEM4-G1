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
import wtw.biz.CommentManager;
import wtw.biz.TopicManager;
import wtw.entities.Account;
import wtw.entities.Comment;
import wtw.entities.Topic;

/**
 *
 * @author Khanh
 */
public class CommentAction extends ActionSupport {

    CommentManager commentManager = lookupCommentManagerBean();
    TopicManager topicManager = lookupTopicManagerBean();

    private String idTopic;
    private String content;
    private List<String> messComment;
    private Topic tp;
    private List<Comment> listComment;

    @Override
    public String execute() throws Exception {
        messComment = new ArrayList<String>();
        Account accLog = (Account) ActionContext.getContext().getSession().get("accLog");
        if (accLog == null) {
            messComment.add("You must login.");
        }
        if (content.length() <= 0 || content.length() > 1000) {
            messComment.add("Content is invalid (0-1000 characters)");
        }
        if (messComment.size() > 0) {
            return "success";
        }
        tp = topicManager.getById(Integer.parseInt(idTopic)).get(0);
        Comment cm = new Comment();
        cm.setContent(content);
        cm.setIdAccount(accLog);
        cm.setIdTopic(tp);
        cm.setPostDate(new Date());

        commentManager.create(cm);

        listComment = commentManager.getByIdTopic(tp);

        return "success";
    }

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getMessComment() {
        return messComment;
    }

    public void setMessComment(List<String> messComment) {
        this.messComment = messComment;
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
