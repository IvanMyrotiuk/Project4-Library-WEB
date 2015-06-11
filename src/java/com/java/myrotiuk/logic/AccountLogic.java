/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.logic;

import com.java.myrotiuk.jdbc.dao.DaoFactory;
import com.java.myrotiuk.jdbc.dao.UserDAO;
import com.java.myrotiuk.jdbc.entities.dto.User;
import com.java.myrotiuk.jdbc.jdbc.dao.TypeDAO;
import com.java.myrotiuk.resource.MessageManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class<code> AccountLogic</code> is a logic for account 
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class AccountLogic {

    private String accountMessage;
    private static final String MESSAGE_NULL_EMAIL = "message.nullemail";
    private static final String MESSAGE_NULL_PASSWORD = "message.nullpassword";
    private static final String MESSAGE_INAPPROPRIATE_EMAIL = "message.inappropriate.email";
    private static final String MESSAGE_PASSWORD_LENGTH = "message.password.length";
    private static final String MESSAGE_PASSWORD_WITH_SPACE = "message.password.with.space";
    private static final Pattern EMAIL_VALIDATION_INPUT = Pattern.compile("\\w+(\\.\\w+)*@\\w+(\\.\\w+)+");
    private static final Pattern PASSWORD_VALIDATION_INPUT = Pattern.compile("(\\w+\\s+\\w+)||(\\s+\\w+\\s+\\w+\\s+)");

    public AccountLogic() {
    }

    public String getAccountMessage() {
        return accountMessage;
    }

    public boolean validate(String userEmail, String userPassword) {

        if (userEmail == null || userEmail.equals("")) {
            accountMessage = MessageManager.getProperty(MESSAGE_NULL_EMAIL);//"Email was not input.";
            return false;
        } else if (userPassword == null || userPassword.equals("")) {
            accountMessage = MessageManager.getProperty(MESSAGE_NULL_PASSWORD);//"Password was not set.";
            return false;
        }


        if (!validateEmailInput(userEmail)) {
            accountMessage = MessageManager.getProperty(MESSAGE_INAPPROPRIATE_EMAIL);// "Invalide email address.";
            return false;
        }

        if (userPassword.length() < 8) {
            accountMessage = MessageManager.getProperty(MESSAGE_PASSWORD_LENGTH);//"Password mast be at least 8 characters";
            return false;
        } else if (validatePasswordInput(userPassword)) {
            accountMessage = MessageManager.getProperty(MESSAGE_PASSWORD_WITH_SPACE);//"Password can not contain spaces";
            return false;
        }

        return true;
    }

    private boolean validateEmailInput(String userEmail) {
        Matcher m = EMAIL_VALIDATION_INPUT.matcher(userEmail);
        return m.matches();
    }

    private boolean validatePasswordInput(String userPassword) {
        Matcher m = PASSWORD_VALIDATION_INPUT.matcher(userPassword);
        return m.matches();
    }

    private UserDAO getUserDAO() {
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        UserDAO userDAO = (UserDAO) factory.getDAO(TypeDAO.USER);
        return userDAO;
    }

    public User login(String userEmail, String userPassword) {

        UserDAO userDAO = this.getUserDAO();
        User user = userDAO.login(userEmail, userPassword);

        return user;
    }

    public boolean exist(String userEmail) {

        UserDAO userDAO = this.getUserDAO();
        boolean exist = userDAO.exist(userEmail);
        return exist;
    }

    public User createAccount(User user) {
        UserDAO userDAO = this.getUserDAO();
        int generatedId = userDAO.insert(user);
        if (generatedId != 0) {
            user.setId(generatedId);
        }
        return user;
    }

}
