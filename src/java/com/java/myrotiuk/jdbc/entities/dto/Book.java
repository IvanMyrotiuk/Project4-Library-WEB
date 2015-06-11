/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.entities.dto;

/**
 * Class<code> Book</code> DTO for Book
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class Book extends BaseEntity{
    
    private Author author;
    private String bookName;
    private int bookQuantity;

    public Book() {
    }

    public Book(Integer bookId, Author author, String bookName, int bookQuantity) {
        super(bookId);
        
        this.author = author;
        this.bookName = bookName;
        this.bookQuantity = bookQuantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}
