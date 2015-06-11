/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import com.java.myrotiuk.resource.MessageManager;
import com.java.myrotiuk.resource.PathManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class<code> EmptyCommand</code> command for redirecting to index page
 * if CommandFactory has not got specific command than will have been created this
 * EmptyComand
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class EmptyCommand implements Command{

    private static final String MESSAGE_NULL_PAGE = "message.nullpage";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nullPageMessage", MessageManager.getProperty(MESSAGE_NULL_PAGE));
        return PathManager.INDEX_PAGE;
    }
    
}
