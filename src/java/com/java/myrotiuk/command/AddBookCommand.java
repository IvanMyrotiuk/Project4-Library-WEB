/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import com.java.myrotiuk.jdbc.entities.dto.Author;
import com.java.myrotiuk.jdbc.entities.dto.Book;
import com.java.myrotiuk.logic.AddBookLogic;
import com.java.myrotiuk.logic.ShowCatalogLogic;
import com.java.myrotiuk.resource.MessageManager;
import com.java.myrotiuk.resource.PathManager;
import java.io.IOException;
import java.sql.Date;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class<code> AddBookCommand</code> command for adding a book to the catalog
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class AddBookCommand implements Command {
    private static final String MESSAGE_BOOK_QUANTITY = "message.book.quantity";
    private static final String MESSAGE_QUANTITY_CHANGED = "message.quantity.changed";
    private static final String MESSAGE_BOOK_WAS_ADDED = "message.book.was.added";
    private static final String MESSAGE_BOOK_ADDED_FAILD = "message.book.added.faild";
    private static final String ADD_BOOK = "addbook";
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nameOfBook = request.getParameter("nameOfBook");
        String nameOfAuthor = request.getParameter("nameOfAuthor");
        String birthdayOfAuthor = request.getParameter("birthdayOfAuthor");
        String bookQuantity = request.getParameter("bookQuantity");

        int quantity = 0;
        try {
            quantity = Integer.parseInt(bookQuantity);
        } catch (NumberFormatException ex) {
            request.setAttribute("addBookMessage", MessageManager.getProperty(MESSAGE_BOOK_QUANTITY));
            List<Book> catalog = ShowCatalogLogic.getCatolog();
            request.setAttribute("catalog", catalog);
            request.setAttribute("command", ADD_BOOK);
            return PathManager.CATALOG_PAGE;
        }

        AddBookLogic addBookLogic = new AddBookLogic();
        
        if(!addBookLogic.validateDate(birthdayOfAuthor)){
            request.setAttribute("addBookMessage", addBookLogic.getMessage());
            List<Book> catalog = ShowCatalogLogic.getCatolog();
            request.setAttribute("catalog", catalog);
            request.setAttribute("command", ADD_BOOK);
            return PathManager.CATALOG_PAGE;
        }
        
        Date date = Date.valueOf(birthdayOfAuthor);
        
        Author author = new Author(nameOfAuthor, date);
        Book book = new Book(0, author, nameOfBook, quantity);

        

        if (!addBookLogic.validateAddingBook(book)) {
            request.setAttribute("addBookMessage", addBookLogic.getMessage());
            List<Book> catalog = ShowCatalogLogic.getCatolog();
            request.setAttribute("catalog", catalog);
            request.setAttribute("command", ADD_BOOK);
            return PathManager.CATALOG_PAGE;
        }

        int quantityToAdd = book.getBookQuantity();
        //old book quantity at BD
        Book bookWithNameAndAuthor = addBookLogic.getBookByNameWithAuthor(book);

        if (bookWithNameAndAuthor != null) {
            bookWithNameAndAuthor.setBookQuantity(quantityToAdd + bookWithNameAndAuthor.getBookQuantity());
            addBookLogic.setNewQuantity(bookWithNameAndAuthor);

           // List<Book> catalog = ShowCatalogLogic.getCatolog();
            //request.setAttribute("catalog", catalog);
            request.setAttribute("addBookMessage", MessageManager.getProperty(MESSAGE_QUANTITY_CHANGED));
            //return PathManager.CATALOG_PAGE;
        } else {
            author = addBookLogic.getAuthor(author);//v object book have another reference to the author -->therefore author when = to null object's author has value not null

            if (author != null) {
                addBookLogic.addBook(book);

                //List<Book> catalog = ShowCatalogLogic.getCatolog();
                //request.setAttribute("catalog", catalog);
                request.setAttribute("addBookMessage", MessageManager.getProperty(MESSAGE_BOOK_WAS_ADDED));
                //return PathManager.CATALOG_PAGE;
            } else {
                //add author and book
                if(addBookLogic.addBookWithAuthor(book)){
                    request.setAttribute("addBookMessage", MessageManager.getProperty(MESSAGE_BOOK_WAS_ADDED));
                }else{
                    request.setAttribute("addBookMessage", MessageManager.getProperty(MESSAGE_BOOK_ADDED_FAILD));
                }
                
            }

        }

        List<Book> catalog = ShowCatalogLogic.getCatolog();
        request.setAttribute("catalog", catalog);
        request.setAttribute("command", ADD_BOOK);
        //request.setAttribute("currentPage", PathManager.CATALOG_PAGE);
        return PathManager.CATALOG_PAGE;
    }

}
