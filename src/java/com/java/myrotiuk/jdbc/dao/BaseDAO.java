/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.dao;

import java.util.List;

/**
 * Interface<code> BaseDAO</code> contain specific methods that 
 * concrete realization has to implement
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public interface BaseDAO<T> {

    List<T> getAll();

    T find(int id);

    int insert(T s);

    void update(T s);

    void delete(int id);
}
