/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.resource;

import java.util.ResourceBundle;

/**
 * Class<code> MessageManager</code> for getting a specific message
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class MessageManager {
    
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("com.java.myrotiuk.properties.message");
    
    private MessageManager(){
        
    }
    
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
    
}
