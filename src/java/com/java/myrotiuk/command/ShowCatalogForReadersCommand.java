/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import com.java.myrotiuk.jdbc.entities.dto.Book;
import com.java.myrotiuk.logic.ShowCatalogLogic;
import com.java.myrotiuk.resource.PathManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class<code> ShowCatalogForReadersCommand</code> command for showing catalog to readers
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class ShowCatalogForReadersCommand implements Command {

    private static final String CATALOG_FOR_READER = "catalogforreader";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Book> catalog = ShowCatalogLogic.getCatolog();
        request.setAttribute("catalog", catalog);

        request.setAttribute("command", CATALOG_FOR_READER);
       // request.setAttribute("currentPage", PathManager.CATALOG_FOR_READERS_PAGE);
        return PathManager.CATALOG_FOR_READERS_PAGE;
    }

}
