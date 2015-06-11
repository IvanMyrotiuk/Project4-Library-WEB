/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import com.java.myrotiuk.resource.PathManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class<code> DoLoginCommand</code> command for redirecting to login page
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class DoLoginCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("command", "dologin");
        request.setAttribute("currentPage", PathManager.LOG_IN_PAGE);
        return PathManager.LOG_IN_PAGE; 
    }
    
}
