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
 * Class<code> SingupCommand</code> command for sing up
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class SingupCommand implements Command {
    
    private static final String MESSAGE_PASSWORD_NOT_MATCH = "message.password.not.match";
    private static final String MESSAGE_ACCOUNT_EXIST = "message.account.exist";
    private static final String MESSAGE_INVALID_SINGUP = "message.invalid.singup";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userFirstName = request.getParameter("userFirstName");
        String userLastName = request.getParameter("userLastName");
        String userAddress = request.getParameter("userAddress");
        String userPhoneNumber = request.getParameter("userPhoneNumber");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        String userRepeatPassword = request.getParameter("userRepeatPassword");

        request.setAttribute("userFirstName", userFirstName);
        request.setAttribute("userLastName", userLastName);
        request.setAttribute("userAddress", userAddress);
        request.setAttribute("userPhoneNumber", userPhoneNumber);
        request.setAttribute("userEmail", userEmail);

        AccountLogic account = new AccountLogic();

        String page = null;

        if (!userPassword.equals(userRepeatPassword)) {
            page = PathManager.SING_UP_PAGE;
            request.setAttribute("singupMessage", MessageManager.getProperty(MESSAGE_PASSWORD_NOT_MATCH));
            request.setAttribute("currentPage", PathManager.SING_UP_PAGE);
            return page;
        } else if (!account.validate(userEmail, userPassword)) {
            request.setAttribute("singupMessage", account.getAccountMessage());
            request.setAttribute("currentPage", PathManager.SING_UP_PAGE);
            return PathManager.SING_UP_PAGE;
        } else if (account.exist(userEmail)) {
            request.setAttribute("singupMessage", MessageManager.getProperty(MESSAGE_ACCOUNT_EXIST));
            request.setAttribute("currentPage", PathManager.SING_UP_PAGE);
            return PathManager.SING_UP_PAGE;
        } else {
            User user = new User(userFirstName, userLastName, userAddress, userPhoneNumber, userEmail, userPassword);
            System.out.println("User = " +user);
            account.createAccount(user);

            if (user.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                request.setAttribute("currentPage", PathManager.WELCOME_READER_PAGE);
                return PathManager.WELCOME_READER_PAGE;//.SING_UP_SUCCESS_PAGE;
            } else {
                request.setAttribute("singupMessage", MessageManager.getProperty(MESSAGE_INVALID_SINGUP));
                request.setAttribute("currentPage", PathManager.SING_UP_PAGE);
                //request.setAttribute("command", "singup");
                return PathManager.SING_UP_PAGE;
            }
            
        }

    }

}
