/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.ui;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Khanh
 */
public class LogoutAction extends ActionSupport{

    @Override
    public String execute() throws Exception {
        ActionContext.getContext().getSession().clear();
        return "success";
    }
    
}
