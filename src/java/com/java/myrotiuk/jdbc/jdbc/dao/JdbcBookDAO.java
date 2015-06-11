/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.jdbc.dao;

import com.java.myrotiuk.jdbc.dao.BookDAO;
import com.java.myrotiuk.jdbc.entities.dto.Author;
import com.java.myrotiuk.jdbc.entities.dto.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;


/**
 * Class<code> JdbcBookDAO</code> is a concrete implementation of BookDAO
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class JdbcBookDAO implements BookDAO {

    private static Logger logger = Logger.getLogger(JdbcBookDAO.class);
    
    @Override
    public Book getPopular() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> getAll() {
        

        String sql = "select * from book join author on book.id_author = author.id";
        List<Book> books = new LinkedList<>();

        try (Connection conn = JdbcConnection.createConnection();
             Statement stmt = conn.createStatement(); ){

            
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Author author = new Author(rs.getInt("author.id"), rs.getString("author.name"), rs.getDate("author.birthday"));
                books.add(new Book(rs.getInt("book.id"), author, rs.getString("book.name"), rs.getInt("book.quantity")));
            }

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
        } 

        return books;
    }

    @Override
    public Book find(int id) {
        
        String sql = "select * from book join author on book.id_author = author.id where book.id = ?";
        
         try (Connection conn = JdbcConnection.createConnection();
               PreparedStatement stmt = conn.prepareStatement(sql);  ){    
            
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Author author = new Author(rs.getInt("author.id"), rs.getString("author.name"), rs.getDate("author.birthday"));
                return new Book(rs.getInt("book.id"), author, rs.getString("book.name"), rs.getInt("book.quantity"));
            }else {
                return null;
            }

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
            return null;
        }

    }

    @Override
    public int insert(Book book) {
        
        String sql = "insert into book(name,id_author,quantity) value (?,?,?)";
        
        int generatedId = 0;
        
        try (Connection conn = JdbcConnection.createConnection();
              PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  ) {
            
            
            stmt.setString(1, book.getBookName());
            stmt.setInt(2, book.getAuthor().getId());
            stmt.setInt(3, book.getBookQuantity());
            
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next()){
                generatedId = rs.getInt(1);
                book.setId(generatedId);
                return generatedId;
            }
            
        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
        } 
        return generatedId;
    }

    @Override
    public void update(Book s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Book getBookByNameAndAuthor(Book book) {
        

        String sql = "select * from book join author on book.id_author = author.id"
                + " where book.name = ? and author.name = ?"; //and author.birthday = ?";

        try (Connection conn = JdbcConnection.createConnection();
              PreparedStatement stmt = conn.prepareStatement(sql);  ) {
            

            stmt.setString(1, book.getBookName());
            stmt.setString(2, book.getAuthor().getAuthorName());
            //stmt.setDate(3, book.getAuthor().getAuthorBirthday());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Author author = new Author(rs.getInt("author.id"), rs.getString("author.name"), rs.getDate("author.birthday"));
                return new Book(rs.getInt("book.id"), author, rs.getString("book.name"), rs.getInt("quantity"));
            } else {
                return null;
            }

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
            return null;
        } 
    }

    @Override
    public void changeQuantity(Book book) {
        

        String sql = "update book set quantity = ? where id = ?";

        try (Connection conn = JdbcConnection.createConnection();
              PreparedStatement stmt = conn.prepareStatement(sql); ) {
            

            stmt.setInt(1, book.getBookQuantity());
            stmt.setInt(2, book.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
        } finally {
            //JdbcConnection.closeConnection();
        }
    }

}
