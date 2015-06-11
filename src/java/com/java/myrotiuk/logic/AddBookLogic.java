/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.logic;

import com.java.myrotiuk.jdbc.dao.AuthorDAO;
import com.java.myrotiuk.jdbc.dao.BookDAO;
import com.java.myrotiuk.jdbc.dao.DaoFactory;
import com.java.myrotiuk.jdbc.entities.dto.Author;
import com.java.myrotiuk.jdbc.entities.dto.Book;
import com.java.myrotiuk.jdbc.jdbc.dao.TypeDAO;
import com.java.myrotiuk.resource.MessageManager;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class<code> AddBookLogic</code> is a logic for Adding Books
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class AddBookLogic {

    private String message;
    private static final String MESSAGE_EMPTY_BOOK_NAME = "message.empty.book.name";
    private static final String MESSAGE_EMPTY_AUTHOR_NAME = "message.empty.author.name";
    private static final String MESSAGE_WRONG_FORMAT_OF_DATE = "message.wrong.format.of.date";
    private static final Pattern VALIDATION_DATE = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    public boolean validateAddingBook(Book book) {

        if (book.getBookName() == null || book.getBookName().equals("")) {
            message = MessageManager.getProperty(MESSAGE_EMPTY_BOOK_NAME);
            return false;
        }

        if (book.getAuthor().getAuthorName() == null || book.getAuthor().getAuthorName().equals("")) {
            message = MessageManager.getProperty(MESSAGE_EMPTY_AUTHOR_NAME);
            return false;
        }

        return true;
    }

    public boolean validateDate(String date) {
//        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
//            message = MessageManager.getProperty(MESSAGE_WRONG_FORMAT_OF_DATE);
//            return false;
//        }
        if(!validateDateInput(date)){
            message = MessageManager.getProperty(MESSAGE_WRONG_FORMAT_OF_DATE);
            return false;
        }
        return true;
    }
    
    private boolean validateDateInput(String date){
        Matcher m = VALIDATION_DATE.matcher(date);
        return m.matches();
    }
    

    public String getMessage() {
        return message;
    }

    public Book getBookByNameWithAuthor(Book book) {

        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        BookDAO bookDAO = (BookDAO) factory.getDAO(TypeDAO.BOOK);

        Book resultBook = bookDAO.getBookByNameAndAuthor(book);

        return resultBook;
    }

    public void setNewQuantity(Book book) {
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        BookDAO bookDAO = (BookDAO) factory.getDAO(TypeDAO.BOOK);
        bookDAO.changeQuantity(book);
    }

    public Author getAuthor(Author author) {
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        AuthorDAO authorDAO = (AuthorDAO) factory.getDAO(TypeDAO.AUTHOR);
        return authorDAO.getAuthorByNameAndBirthday(author);
    }

    public int addBook(Book book) {

        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        BookDAO bookDAO = (BookDAO) factory.getDAO(TypeDAO.BOOK);
        return bookDAO.insert(book);
    }

    public boolean addBookWithAuthor(Book book) {
        Author author = book.getAuthor();
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        AuthorDAO authorDAO = (AuthorDAO) factory.getDAO(TypeDAO.AUTHOR);
        if (authorDAO.insert(author) != 0) {
            BookDAO bookDAO = (BookDAO) factory.getDAO(TypeDAO.BOOK);
            if (bookDAO.insert(book) != 0) {
                return true;
            }
        }
        return false;
    }

}
