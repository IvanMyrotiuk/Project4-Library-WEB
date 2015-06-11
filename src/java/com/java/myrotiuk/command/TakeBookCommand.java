/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import com.java.myrotiuk.jdbc.entities.dto.Book;
import com.java.myrotiuk.jdbc.entities.dto.User;
import com.java.myrotiuk.logic.ShowCatalogLogic;
import com.java.myrotiuk.logic.TakeBookLogic;
import com.java.myrotiuk.resource.MessageManager;
import com.java.myrotiuk.resource.PathManager;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class<code> TakeBookCommand</code> command for taking books
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class TakeBookCommand implements Command {

    private static final String MESSAGE_CHOOSE_BOOK = "message.choose.book";
    private static final String MESSAGE_FAIL_TO_TAKE_BOOK = "message.fail.to.take.book";
    private static final String TAKE_BOOK = "takebook";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] takenBooks = request.getParameterValues("takenbook");

        if (takenBooks == null) {
            List<Book> catalog = ShowCatalogLogic.getCatolog();
            request.setAttribute("catalog", catalog);
            request.setAttribute("catalogFoReadersMessage", MessageManager.getProperty(MESSAGE_CHOOSE_BOOK));
            request.setAttribute("command", TAKE_BOOK);
            //request.setAttribute("currentPage", PathManager.CATALOG_FOR_READERS_PAGE);
            return PathManager.CATALOG_FOR_READERS_PAGE;
        }

        List<String> serialNumber = Arrays.asList(takenBooks);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        TakeBookLogic takeBookLogic = new TakeBookLogic();

        boolean resultOfMakingOrder = takeBookLogic.makeOrders(serialNumber, user);

        if (!resultOfMakingOrder) {
            request.setAttribute("catalogFoReadersMessage", MessageManager.getProperty(MESSAGE_FAIL_TO_TAKE_BOOK));
            request.setAttribute("command", TAKE_BOOK);
           // request.setAttribute("currentPage", PathManager.CATALOG_FOR_READERS_PAGE);
            return PathManager.CATALOG_FOR_READERS_PAGE;
        }

        List<Book> catalog = ShowCatalogLogic.getCatolog();
        request.setAttribute("catalog", catalog);

        request.setAttribute("command", TAKE_BOOK);
        //request.setAttribute("currentPage", PathManager.CATALOG_FOR_READERS_PAGE);
        return PathManager.CATALOG_FOR_READERS_PAGE;
    }

}
