/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.entities.dto;

import java.sql.Timestamp;

/**
 * Class<code> Order</code> DTO for Order
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class Order extends BaseEntity{
    
    private User user;
    private Book book;
    private Byte readingArea;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public Order() {
    }

    public Order(Integer orderId, User user, Book book, Byte readingArea, Timestamp createdAt) {
        super(orderId);
        this.user = user;
        this.book = book;
        this.readingArea = readingArea;
        this.createdAt = createdAt;
    }

    public Order(User user, Book book, Byte readingArea, Integer id) {
        super(id);
        this.user = user;
        this.book = book;
        this.readingArea = readingArea;
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    public Byte getReadingArea() {
        return readingArea;
    }

    public void setReadingArea(Byte readingArea) {
        this.readingArea = readingArea;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
