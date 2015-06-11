/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.logic;

import com.java.myrotiuk.jdbc.dao.DaoFactory;
import com.java.myrotiuk.jdbc.dao.OrderDAO;
import com.java.myrotiuk.jdbc.entities.dto.Book;
import com.java.myrotiuk.jdbc.entities.dto.Order;
import com.java.myrotiuk.jdbc.entities.dto.User;
import com.java.myrotiuk.jdbc.jdbc.dao.TypeDAO;
import java.util.List;

/**
 * Class<code> TakenBookLogic</code> is a logic for showing books that was took by readers
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class TakenBookLogic {
    
    public static List<Order> getTakenBooks(User user){
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        OrderDAO orderDAO = (OrderDAO) factory.getDAO(TypeDAO.ORDER);
        
        List<Order> orders = orderDAO.getUsersBooks(user);
        
        return orders;
        
    }
    
}
