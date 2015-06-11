/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.entities.dto;

import java.sql.Date;

/**
 * Class<code> Author</code> DTO for Author
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class Author extends BaseEntity{
    private String authorName;
    private Date authorBirthday;

    public Author() {
    }

    public Author(Integer authorId, String authorName, Date authorBirthday) {
        super(authorId);//this.authorId = authorId;
        this.authorName = authorName;
        this.authorBirthday = authorBirthday;
    }

    public Author(String authorName, Date authorBirthday) {
        this.authorName = authorName;
        this.authorBirthday = authorBirthday;
    }
    
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getAuthorBirthday() {
        return authorBirthday;
    }

    public void setAuthorBirthday(Date authorBirthday) {
        this.authorBirthday = authorBirthday;
    }
   
}
