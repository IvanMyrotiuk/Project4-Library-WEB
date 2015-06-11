/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.myrotiuk.jdbc.entities.dto;

/**
 * Class<code> User</code> DTO for User
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 23-05-2015
 */
public class User extends BaseEntity{
    
    private String userFirstName; 
    private String userLastName;
    private String userAddress;
    private String userPhoneNumber;
    private String userEmail;
    private String userPasswordHash;
    private Byte userType;

    public User() {
    }

    public User(Integer userId, String userFirstName, String userLastName, String userAddress, String userPhoneNumber, String userEmail, String userPasswordHash, Byte userType) {
        super(userId);
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
       // this.userPasswordHash = userPasswordHash;
        this.userType = userType;
        
        if(userPasswordHash != null){        
            try{
                Integer passHash = Integer.parseInt(userPasswordHash);
                this.userPasswordHash = passHash.toString();
            }catch(NumberFormatException ex){
                Integer passHash = userPasswordHash.hashCode();
                this.userPasswordHash = passHash.toString();
            }
            
        }else{
            this.userPasswordHash = new Integer(userPasswordHash.hashCode()).toString();
        }
    }

    public User(String userFirstName, String userLastName, String userAddress, String userPhoneNumber, String userEmail, String userPasswordHash) {
        super();
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.userPasswordHash = new Integer(userPasswordHash.hashCode()).toString();
    }
    
    

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPasswordHash() {
        return userPasswordHash;
    }

    public void setUserPasswordHash(String userPasswordHash) {
        this.userPasswordHash = userPasswordHash;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + super.getId() + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userAddress=" + userAddress + ", userPhoneNumber=" + userPhoneNumber + ", userEmail=" + userEmail + ", userPasswordHash=" + userPasswordHash + ", userType=" + userType + '}';
    } 
}
