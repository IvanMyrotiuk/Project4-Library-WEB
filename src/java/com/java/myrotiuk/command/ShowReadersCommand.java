/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import com.java.myrotiuk.jdbc.entities.dto.User;
import com.java.myrotiuk.logic.ShowReadersLogic;
import com.java.myrotiuk.resource.PathManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class<code> ShowOwingReadersCommand</code> command for showing readers that
 * was registered at the library
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class ShowReadersCommand implements Command{

    private static final String READERS = "readers";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users =  ShowReadersLogic.getAllUsers();
        request.setAttribute("users", users);
        request.setAttribute("command", READERS);
        //request.setAttribute("currentPage", PathManager.READERS_PAGE);
        return PathManager.READERS_PAGE;
    }
    
}
