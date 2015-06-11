/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import com.java.myrotiuk.jdbc.entities.dto.Book;
import com.java.myrotiuk.jdbc.entities.dto.Order;
import com.java.myrotiuk.jdbc.entities.dto.User;
import com.java.myrotiuk.logic.TakenBookLogic;
import com.java.myrotiuk.resource.MessageManager;
import com.java.myrotiuk.resource.PathManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Class<code> ShowTakenBooksCommand</code> command for showing books that
 * were taken by readers
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class ShowTakenBooksCommand implements Command{

    private static final String TAKEN_BOOKS = "takenbooks";
    private static final String MESSAGE_BOOK_ORDER_EMPTY = "message.book.order.empty";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> takenBooks = TakenBookLogic.getTakenBooks(user);
        
        if(takenBooks == null){
            request.setAttribute("takenBookMessage", MessageManager.getProperty(MESSAGE_BOOK_ORDER_EMPTY));
        }
        
        request.setAttribute("takenBooks", takenBooks);
        request.setAttribute("command", TAKEN_BOOKS);
        //request.setAttribute("currentPage", PathManager.TAKEN_BOOKS_PAGE);
        return PathManager.TAKEN_BOOKS_PAGE;
    }
    
}
