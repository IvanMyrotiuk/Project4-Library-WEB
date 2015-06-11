/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.jdbc.dao;

import com.java.myrotiuk.jdbc.dao.UserDAO;
import com.java.myrotiuk.jdbc.entities.dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Class<code> JdbcUserDAO</code> is a concrete implementation of UserDAO
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class JdbcUserDAO implements UserDAO {

    private static Logger logger = Logger.getLogger(JdbcUserDAO.class);
    
    @Override
    public List<User> findAllWithBook() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll() {

        

        String sql = "select * from user ";

        List<User> users = new LinkedList<>();
        try (Connection conn = JdbcConnection.createConnection();
               Statement stmt = conn.createStatement(); ){
            
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"),
                        rs.getString("phone_number"), rs.getString("email"), rs.getString("password_hash"), rs.getByte("user_type")));
            }

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
            return null;
        } 

        return users;
    }

    @Override
    public User find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(User user) {
        
        
        String sql = " insert into user (first_name, last_name, address, phone_number, email, password_hash) value (?,?,?,?,?,?)";
        int generatedId = 0;
        try (Connection conn = JdbcConnection.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
            
            stmt.setString(1, user.getUserFirstName());
            stmt.setString(2, user.getUserLastName());
            stmt.setString(3, user.getUserAddress());
            stmt.setString(4, user.getUserPhoneNumber());
            stmt.setString(5, user.getUserEmail());
            stmt.setString(6, user.getUserPasswordHash());
            
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next()){
                generatedId = rs.getInt(1);
            }
            
            
        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
        }finally{
            
        }
        
        return generatedId;
    }

    @Override
    public void update(User s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User login(String userEmail, String userPassword) {

        

        String sql = "select * from user where email = ? and password_hash = ?";

        try (Connection conn = JdbcConnection.createConnection();
               PreparedStatement stmt = conn.prepareStatement(sql); ){
            

            stmt.setString(1, userEmail);
            try{
                Integer passHash =Integer.parseInt(userPassword);
                stmt.setString(2, passHash.toString());
            }catch(NumberFormatException ex){
                stmt.setString(2, new Integer(userPassword.hashCode()).toString());
            }
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"),
                        rs.getString("phone_number"), rs.getString("email"), rs.getString("password_hash"), rs.getByte("user_type"));
            } else {
                return null;
            }

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
            return null;
        } 
    }

    @Override
    public boolean exist(String userEmail) {
        
        
        String sql = "select * from user where email = ?";
        
        try (Connection conn = JdbcConnection.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);){
            
            stmt.setString(1, userEmail);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                return true;
            }
            
        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
        }
        
        return false;
    }

}
