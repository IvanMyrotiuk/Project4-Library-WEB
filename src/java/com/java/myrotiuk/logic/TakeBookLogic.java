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
import com.java.myrotiuk.jdbc.entities.dto.User;
import com.java.myrotiuk.jdbc.jdbc.dao.TypeDAO;
import java.util.LinkedList;
import java.util.List;

/**
 * Class<code> TakeBookLogic</code> is a logic for taking books
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class TakeBookLogic {
    
    private List<Book> orderedBooks = null;
    
    private Book getBook(int id){
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        BookDAO bookDAO = (BookDAO) factory.getDAO(TypeDAO.BOOK);
        return bookDAO.find(id);
    }
    
    public boolean makeOrders(List<String> serialNumber, User user){
        
        List<Integer> serialNumberOfBook = new LinkedList<>();
        
        for(String sn: serialNumber){
            try{
            serialNumberOfBook.add(Integer.parseInt(sn));
            }catch(NumberFormatException ex){
               return false;
            }
        }
        
        orderedBooks = new LinkedList<>();
        for(Integer b: serialNumberOfBook){
            Book book = getBook(b);
            orderedBooks.add(book);
        }
        
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        OrderDAO orderDAO = (OrderDAO) factory.getDAO(TypeDAO.ORDER);
        
        List<Order> orders = new LinkedList<>();
        for(Book book: orderedBooks){
            Byte b = 3;
            orders.add(new Order(user, book, b, 0));
        }
        
        for(Order order: orders){
            orderDAO.insert(order);
        }
        
        changeQuantity();
        
        return true;
        
    }
    
    private void changeQuantity(){
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        BookDAO bookDAO = (BookDAO) factory.getDAO(TypeDAO.BOOK);
        for(Book book : orderedBooks){
            book.setBookQuantity(book.getBookQuantity()-1);
            bookDAO.changeQuantity(book);
        }
        
    }

}
