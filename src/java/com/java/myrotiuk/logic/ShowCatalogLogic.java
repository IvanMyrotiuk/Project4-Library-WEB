/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.logic;

import com.java.myrotiuk.jdbc.dao.BookDAO;
import com.java.myrotiuk.jdbc.dao.DaoFactory;
import com.java.myrotiuk.jdbc.entities.dto.Book;
import com.java.myrotiuk.jdbc.jdbc.dao.TypeDAO;
import java.util.List;

/**
 * Class<code> ShowCatalogLogic</code> is a logic for showing catalog of books
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class ShowCatalogLogic {
    public static List<Book> getCatolog(){
        DaoFactory factory = DaoFactory.getFactory(DaoFactory.JDBC);
        BookDAO bookDAO = (BookDAO) factory.getDAO(TypeDAO.BOOK);
        List<Book> catalog = bookDAO.getAll();
        return catalog;
    }
}
