/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;
import java.io.Serializable;
/**
 *
 * @author user
 */
public class UserBean implements Serializable {
    
    private String userID;
    private String username;
    
    public UserBean(){
        userID = null;
        username = null;
    }
    public String getUserID(){
	return userID;
    }
    
    public String getUsername(){
	return username;
    }
    public void setUserID(String uid){
        userID = uid;
    }
    public void setUsername(String un){
        username = un;
    }
}
