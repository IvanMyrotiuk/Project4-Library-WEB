/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.dao;

import com.java.myrotiuk.jdbc.entities.dto.Author;

/**
 * Interface<code> AuthorDAO</code> contain specific methods that 
 * concrete realization has to implement
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public interface AuthorDAO extends BaseDAO<Author>{
    Author getAuthorByNameAndBirthday(Author author);
}
