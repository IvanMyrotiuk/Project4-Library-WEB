/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.logic;

import com.java.myrotiuk.jdbc.dao.DaoFactory;
import com.java.myrotiuk.jdbc.dao.OrderDAO;
import com.java.myrotiuk.jdbc.entities.dto.Order;
import com.java.myrotiuk.jdbc.jdbc.dao.TypeDAO;
import java.util.List;

/**
 * Class<code> ShowOwingReadersLogic</code> is a logic for showing readers that
 * took a book
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class ShowOwingReadersLogic {
    
    public static List<Order> getAllOrders(){
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        OrderDAO orderDAO = (OrderDAO) factory.getDAO(TypeDAO.ORDER);
        return orderDAO.getAll();
    }
    
}
