/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.jdbc.dao;

import com.java.myrotiuk.jdbc.dao.OrderDAO;
import com.java.myrotiuk.jdbc.entities.dto.Author;
import com.java.myrotiuk.jdbc.entities.dto.Book;
import com.java.myrotiuk.jdbc.entities.dto.Order;
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
 * Class<code> JdbcOrderDAO</code> is a concrete implementation of OrderDAO
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class JdbcOrderDAO implements OrderDAO {

    private static Logger logger = Logger.getLogger(JdbcOrderDAO.class);
    
    @Override
    public List<Order> getAll() {
        

        String sql = "select * from order_book join user on order_book.id_user = user.id "
                + "join book on order_book.id_book = book.id join author on book.id_author = author.id";

        List<Order> orders = new LinkedList<>();

        try (Connection conn = JdbcConnection.createConnection();
              Statement stmt = conn.createStatement(); ){
            
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Author author = new Author(rs.getInt("author.id"), rs.getString("author.name"), rs.getDate("author.birthday"));
                Book book = new Book(rs.getInt("book.id"), author, rs.getString("book.name"), rs.getInt("book.quantity"));
                User user = new User(rs.getInt("user.id"), rs.getString("user.first_name"), rs.getString("user.last_name"), rs.getString("user.address"),
                        rs.getString("user.phone_number"), rs.getString("user.email"), rs.getString("user.password_hash"), rs.getByte("user.user_type"));

                orders.add(new Order(rs.getInt("order_book.id"), user, book, rs.getByte("order_book.reading_area"), rs.getTimestamp("order_book.created_at")));

            }

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
        } 
        return orders;
    }

    @Override
    public Order find(int id) {

        

        String sql = "select * from order_book join user on order_book.id_user = user.id "
                + " join book on order_book.id_book = book.id join author on book.id_author = author.id where order_book.id = ?";

        try (Connection conn = JdbcConnection.createConnection();
              PreparedStatement stmt = conn.prepareStatement(sql);  ){
            

            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Author author = new Author(rs.getInt("author.id"), rs.getString("author.name"), rs.getDate("author.birthday"));
                Book book = new Book(rs.getInt("book.id"), author, rs.getString("book.name"), rs.getInt("book.quantity"));
                User user = new User(rs.getInt("user.id"), rs.getString("user.first_name"), rs.getString("user.last_name"), rs.getString("user.address"),
                        rs.getString("user.phone_number"), rs.getString("user.email"), rs.getString("user.password_hash"), rs.getByte("user.user_type"));

                return new Order(rs.getInt("order_book.id"), user, book, rs.getByte("order_book.reading_area"), rs.getTimestamp("order_book.created_at"));
            }

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
            return null;
        } 
        return null;
    }

    @Override
    public int insert(Order order) {
        

        String sql = "insert into order_book(id_user, id_book, reading_area, created_at) value (?,?,?,?)";

        int generatedId = 0;

        try (Connection conn = JdbcConnection.createConnection();
               PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); ){
            

            stmt.setInt(1, order.getUser().getId());
            stmt.setInt(2, order.getBook().getId());
            stmt.setInt(3, order.getReadingArea());
            stmt.setTimestamp(4, order.getCreatedAt());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
        } 

        return generatedId;
    }

    @Override
    public void update(Order s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        

        String sql = "delete from order_book where id = ?";

        try (Connection conn = JdbcConnection.createConnection();
               PreparedStatement stmt = conn.prepareStatement(sql); ){
            

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
        } 
    }

    @Override
    public List<Order> getUsersBooks(User user) {
        

        String sql = " select * from order_book join user on order_book.id_user = user.id "
                + " join book on order_book.id_book = book.id"
                + " join author on book.id_author = author.id where user.id = ?";

        List<Order> orders = new LinkedList<>();

        try (Connection conn = JdbcConnection.createConnection();
               PreparedStatement stmt = conn.prepareStatement(sql); ){
            

            stmt.setInt(1, user.getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Author author = new Author(rs.getInt("author.id"), rs.getString("author.name"), rs.getDate("author.birthday"));
                Book book = new Book(rs.getInt("book.id"), author, rs.getString("book.name"), rs.getInt("book.quantity"));
                orders.add(new Order(rs.getInt("order_book.id"), user, book, rs.getByte("order_book.reading_area"), rs.getTimestamp("order_book.created_at")));
            }
            if (orders.isEmpty()) {
                return null;
            }
        } catch (SQLException ex) {
            logger.error("There is problem with sql", ex);
        } 
        
        return orders;
    }

}
