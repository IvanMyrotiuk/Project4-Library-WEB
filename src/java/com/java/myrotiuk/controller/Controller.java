/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.controller;

import com.java.myrotiuk.command.Command;
import com.java.myrotiuk.command.CommandFactory;
import com.java.myrotiuk.resource.MessageManager;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



/**
 * Class<code> Controller</code> class for calling and executing unique command
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class Controller extends HttpServlet {

    private static Logger logger = Logger.getLogger(Controller.class);
    private static final String MESSAGE_NULL_PAGE = "message.nullpage";
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); 
        String log4jLocation = getInitParameter("log4j-properties-location");
        String webAppPath = this.getServletContext().getRealPath("/");
        if(log4jLocation != null){
            PropertyConfigurator.configure(webAppPath+"/" + log4jLocation);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command = commandFactory.getCommand(request);
        String page = command.execute(request, response);
                
        if(page != null){
            request.getRequestDispatcher(page).forward(request, response);
        }else{
            request.setAttribute("nullPageMessage", MessageManager.getProperty(MESSAGE_NULL_PAGE));
            logger.error("error with executing command at the controller therefore page not found!!!");
            request.getRequestDispatcher(request.getContextPath()).forward(request, response);
        }
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
