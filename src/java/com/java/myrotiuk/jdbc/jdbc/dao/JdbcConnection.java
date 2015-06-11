/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * Class<code> JdbcConnection</code> for creating connection with BD
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class JdbcConnection {
    
    private static Logger logger = Logger.getLogger(JdbcConnection.class);

    public static Connection createConnection() {
            Connection conn = null;
        try {
            InitialContext initialContext = new InitialContext();
            Context env = (Context) initialContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) env.lookup("jdbc/project4_library");

            conn = ds.getConnection();

        } catch (NamingException ex) {
            logger.error("There is problem with JNDI", ex);
        } catch (SQLException ex) {
            logger.error("Problem with connection", ex);
        }

        return conn;
    }

}
