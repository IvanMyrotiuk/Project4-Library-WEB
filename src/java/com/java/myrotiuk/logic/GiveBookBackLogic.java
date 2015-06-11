/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.logic;

import com.java.myrotiuk.jdbc.dao.BookDAO;
import com.java.myrotiuk.jdbc.dao.DaoFactory;
import com.java.myrotiuk.jdbc.dao.OrderDAO;
import com.java.myrotiuk.jdbc.entities.dto.Book;
import com.java.myrotiuk.jdbc.entities.dto.Order;
import com.java.myrotiuk.jdbc.jdbc.dao.TypeDAO;
import java.util.LinkedList;
import java.util.List;

/**
 * Class<code> GiveBookBackLogic</code> is a logic for giving book back
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class GiveBookBackLogic {
    
    private Order giveOrder(int id){
        
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        OrderDAO orderDAO = (OrderDAO) factory.getDAO(TypeDAO.ORDER);
        
        return orderDAO.find(id);
    }
    
    public void giveBookBack(List<String> numberOfOrders){
        
        List<Integer> ordersOfBooks = new LinkedList<>();
        
        for(String sn: numberOfOrders){   
            ordersOfBooks.add(Integer.parseInt(sn));
        }
        
        List<Order> orders = new LinkedList<>();
        
        for(Integer id : ordersOfBooks){
            Order order = this.giveOrder(id);
            orders.add(order);
        }

        
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        OrderDAO orderDAO = (OrderDAO) factory.getDAO(TypeDAO.ORDER);
        for(Order orderToDelete : orders){
            orderDAO.delete(orderToDelete.getId());
            this.changeQuantity(orderToDelete.getBook());
        }
        
    
        
    }
    
    private void changeQuantity( Book book){
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        BookDAO bookDAO = (BookDAO) factory.getDAO(TypeDAO.BOOK);
        Integer oldQuantity = bookDAO.find(book.getId()).getBookQuantity();
        book.setBookQuantity(oldQuantity+1);
        bookDAO.changeQuantity(book);
              
    }
    
}
