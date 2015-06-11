/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.jdbc.dao;

import com.java.myrotiuk.jdbc.dao.AuthorDAO;
import com.java.myrotiuk.jdbc.entities.dto.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Class<code> JdbcAuthorDAO</code> is a concrete implementation of AuthorDAO
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class JdbcAuthorDAO implements AuthorDAO {
    
    private static Logger logger = Logger.getLogger(JdbcAuthorDAO.class);

    @Override
    public List<Author> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Author find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Author author) {
       
        
        String sql = "insert into author(name, birthday) value (?,?)";
        
        int generatedId= 0;
        
        try ( Connection conn = JdbcConnection.createConnection();
              PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); ){
            
            
            stmt.setString(1, author.getAuthorName());
            stmt.setDate(2, author.getAuthorBirthday());
            
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next()){
                generatedId = rs.getInt(1);
                author.setId(generatedId);
                return generatedId;
            }
            
        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
//            System.out.println("There is problem with sql");
//            System.out.println("Message is :" + ex.getMessage());
        } 
        return generatedId;
    }

    @Override
    public void update(Author s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Author getAuthorByNameAndBirthday(Author author) {
        

        String sql = "select * from author whre name = ?" ;// and birthday = ?";

        try (Connection conn = JdbcConnection.createConnection();
                PreparedStatement stmt = conn.prepareCall(sql);){
            

            stmt.setString(1, author.getAuthorName());
           // stmt.setDate(2, author.getAuthorBirthday());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                author.setId(rs.getInt("id"));
                return author;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
            return null;
        } 
        
    }

}
