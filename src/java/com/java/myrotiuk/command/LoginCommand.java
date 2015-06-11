/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import com.java.myrotiuk.jdbc.entities.dto.User;
import com.java.myrotiuk.logic.AccountLogic;
import com.java.myrotiuk.resource.MessageManager;
import com.java.myrotiuk.resource.PathManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class<code> LoginCommand</code> command for log in 
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        AccountLogic account = new AccountLogic();

        User user = account.login(userEmail, userPassword);

        HttpSession session = request.getSession();

        String page = "";
        if (user != null && user.getUserType() == 0) {
            session.setAttribute("user", user);
            request.setAttribute("messageForFilter", " Login SUCCESSFUL for reader with email: ");
            page = PathManager.WELCOME_READER_PAGE;//.LOG_IN_SUCCESS_PAGE;
        } else if (user != null && user.getUserType() == 1) {
            session.setAttribute("user", user);
            request.setAttribute("messageForFilter", " Login SUCCESSFUL for librarian with email: ");
            page = PathManager.WELCOME_LIBRARIAN_PAGE;
        } else {
            request.setAttribute("loginMessage", MessageManager.getProperty("message.fail.login"));
            //session.setAttribute("forFilterUserEmail",userEmail);
            request.setAttribute("messageForFilter", " Login was FAILD to email:");
            request.setAttribute("userEmail", userEmail);
            page = PathManager.LOG_IN_PAGE;
        }

//        request.setAttribute("userEmail", userEmail);
//        request.setAttribute("userPassword", userPassword);
//        request.setAttribute("command", "login");
        request.setAttribute("currentPage", page);
        return page;
    }

}
