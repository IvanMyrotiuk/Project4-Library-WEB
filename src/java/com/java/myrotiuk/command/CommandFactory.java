/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.command;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Class<code> CommandFactory</code> for creating unique command for specific purposes
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class CommandFactory {
    
    private static Logger logger = Logger.getLogger(CommandFactory.class);
    private Map<String, Command> commands = new HashMap<>();
    protected static CommandFactory instance = new CommandFactory();
    
    protected CommandFactory(){
        commands.put("dologin", new DoLoginCommand());
        commands.put("dosingup", new DoSingupCommand());
        commands.put("login", new LoginCommand());
        commands.put("singup", new SingupCommand());
        commands.put("logout", new LogoutCommand());
        
        commands.put("catalog", new ShowCatalogCommand());
        commands.put("readers", new ShowReadersCommand());
        commands.put("owingreader", new ShowOwingReadersCommand());
        commands.put("addbook", new AddBookCommand());
        
        commands.put("catalogforreader", new ShowCatalogForReadersCommand());
        commands.put("takenbooks", new ShowTakenBooksCommand());
        commands.put("takebook", new TakeBookCommand());
        
        commands.put("givebookback", new GiveBookBackCommand());
    }
    
    public static CommandFactory getInstance(){
        return instance;
    }
    
    public Command getCommand(HttpServletRequest request){
        
        String action = request.getParameter("action");//null if no action
        
        Command command = commands.get(action);
        
        if(command == null){
            command = new EmptyCommand();
            logger.warn("Empty command has been created, because there are no such command");
        }
        
        return command;
    }
    
}
