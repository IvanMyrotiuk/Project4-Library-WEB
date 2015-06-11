/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import com.java.myrotiuk.jdbc.entities.dto.Order;
import com.java.myrotiuk.logic.ShowOwingReadersLogic;
import com.java.myrotiuk.resource.PathManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class<code> ShowOwingReadersCommand</code> command for showing readers that took 
 * some books
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class ShowOwingReadersCommand implements Command{

    private static final String OWING_READER = "owingreader";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = ShowOwingReadersLogic.getAllOrders();
        request.setAttribute("orders", orders);
        request.setAttribute("command", OWING_READER);
        //request.setAttribute("currentPage", PathManager.READER_OWING_PAGE);
        return PathManager.READER_OWING_PAGE;
    }
    
}
