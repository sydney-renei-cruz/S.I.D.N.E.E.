/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author host
 */
public class LoginBean implements java.io.Serializable{
     private String username;
     private String userID;
     private boolean status;
     
     public LoginBean(){
         username = null;
         userID = null;
         status = false;
     }
     
     public void setUsername(String unin){
         username = unin;
     }
     
     public void setUserID(String udin){
         userID = udin;
     }
     
     public void setStatus(boolean statin){
         status = statin;
     }
     
     public String getUsername(){
         return username;
     }
     
     public String getUserID(){
         return userID;
     }
     
     public boolean getStatus(){
         return status;
     }
}