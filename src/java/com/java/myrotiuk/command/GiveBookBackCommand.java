/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import com.java.myrotiuk.jdbc.entities.dto.Order;
import com.java.myrotiuk.jdbc.entities.dto.User;
import com.java.myrotiuk.logic.GiveBookBackLogic;
import com.java.myrotiuk.logic.TakenBookLogic;
import com.java.myrotiuk.resource.MessageManager;
import com.java.myrotiuk.resource.PathManager;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class<code> GiveBookBackCommand</code> command for returning book back to the library
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class GiveBookBackCommand implements Command {

    private static final String MESSAGE_CHOOSE_BOOK_GIVE_BACK = "message.choose.book.give.back";
    private static final String MESSAGE_BOOK_ORDER_EMPTY = "message.book.order.empty";
    private static final String GIVE_BOOK_BACK = "givebookback";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] bookToGiveBack = request.getParameterValues("givenbookback");

        if (bookToGiveBack == null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            List<Order> takenBooks = TakenBookLogic.getTakenBooks(user);
            request.setAttribute("takenBooks", takenBooks);
            request.setAttribute("takenBookMessage", MessageManager.getProperty(MESSAGE_CHOOSE_BOOK_GIVE_BACK));
            request.setAttribute("command", GIVE_BOOK_BACK);
            return PathManager.TAKEN_BOOKS_PAGE;
        }

        List<String> numberOfOrders = Arrays.asList(bookToGiveBack);

        GiveBookBackLogic bookBack = new GiveBookBackLogic();
        bookBack.giveBookBack(numberOfOrders);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> takenBooks = TakenBookLogic.getTakenBooks(user);

        if (takenBooks == null) {
            request.setAttribute("takenBookMessage", MessageManager.getProperty(MESSAGE_BOOK_ORDER_EMPTY));
        }

        request.setAttribute("takenBooks", takenBooks);
        request.setAttribute("command", GIVE_BOOK_BACK);
        //request.setAttribute("currentPage", PathManager.TAKEN_BOOKS_PAGE);
        return PathManager.TAKEN_BOOKS_PAGE;
    }

}
