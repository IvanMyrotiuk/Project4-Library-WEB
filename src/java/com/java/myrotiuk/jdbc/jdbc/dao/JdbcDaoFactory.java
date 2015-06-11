/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.jdbc.dao;

import com.java.myrotiuk.jdbc.dao.BaseDAO;
import com.java.myrotiuk.jdbc.dao.DaoFactory;
import com.java.myrotiuk.jdbc.entities.dto.BaseEntity;

/**
 * Class<code> JdbcDaoFactory</code> for creating specific DAO
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class JdbcDaoFactory extends DaoFactory{

    @Override
    public BaseDAO getDAO(TypeDAO type) {
        
        switch(type){
            case USER: return new JdbcUserDAO();
            case BOOK: return new JdbcBookDAO();
            case ORDER: return new JdbcOrderDAO();
            case AUTHOR: return new JdbcAuthorDAO();
            default: throw new IllegalArgumentException();
        }
    }
    
}
