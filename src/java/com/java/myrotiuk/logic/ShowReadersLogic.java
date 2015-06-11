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
import java.util.List;

/**
 * Class<code> ShowReadersLogic</code> is a logic for showing readers
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class ShowReadersLogic {
    
    public static List<User> getAllUsers(){
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        UserDAO userDAO = (UserDAO) factory.getDAO(TypeDAO.USER);
        return userDAO.getAll();
    }
    
}
