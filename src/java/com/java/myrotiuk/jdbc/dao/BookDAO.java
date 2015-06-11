/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.dao;

import com.java.myrotiuk.jdbc.entities.dto.Book;


/**
 * Interface<code> BookDAO</code> contain specific methods that 
 * concrete realization has to implement
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public interface BookDAO extends BaseDAO<Book>{
    Book getPopular();
    Book getBookByNameAndAuthor(Book book);
    void changeQuantity(Book book);
    
}
