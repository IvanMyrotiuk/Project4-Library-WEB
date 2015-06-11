/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.dao;

import com.java.myrotiuk.jdbc.jdbc.dao.JdbcDaoFactory;
import com.java.myrotiuk.jdbc.jdbc.dao.TypeDAO;
import com.java.myrotiuk.stub.stub.dao.StubDaoFactory;

/**
 * Class<code> DaoFactory</code> create specific DAO factory
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public abstract class DaoFactory {
    
    public static final int JDBC = 1;
    public static final int STUB = 2;
    
    public abstract BaseDAO getDAO(TypeDAO type);
    
    public static DaoFactory getFactory(int factory){
        switch(factory){
            case JDBC: return new JdbcDaoFactory();
            case STUB: return new StubDaoFactory();
            default: return null;
        }
    }
    
}
