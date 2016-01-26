/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Beans.*;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author host
 */
public class MySQL {
    public static ConnectionBean query(String inText, HttpServletRequest request, HttpServletResponse response){
        
        ConnectionBean cb = new ConnectionBean();
        ServletContext context = request.getSession().getServletContext();
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(context.getInitParameter("jdbcDriver"));
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            
            ps = conn.prepareStatement(inText);
            
            if(ps.execute()){
                rs = ps.getResultSet();
            
                cb.setRS(rs);
                cb.setPS(ps);
                cb.setConn(conn);
                cb.setStatus(true);
            }
        }
        catch(Exception ex) {
            cb.setStatus(false);
            cb.setEx(ex);
        }
        
        return cb;
    }
    
    public static LoginBean login(String user, String pass, HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        ServletContext context = request.getSession().getServletContext();
        
        LoginBean lb = new LoginBean();
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(context.getInitParameter("jdbcDriver"));
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));

            String inText = "select salt from user where username = ?;";
            String salt = "";
            
            ps = conn.prepareStatement(inText);
            ps.setString(1, user);
            
            if(ps.execute()){
                rs = ps.getResultSet();
                if(rs.first()){
                    salt = rs.getString("salt");
                }
            }
            rs.close();
            ps.close();
            
            String hashedPass = Hashing.sha1().hashString(pass + salt, Charsets.UTF_8).toString();
            
            inText = "select username, userID from user where username = ? and passHash = ?;";
            
            ps = conn.prepareStatement(inText);
            ps.setString(1, user);
            ps.setString(2, hashedPass);
            
            if(ps.execute()){
                
                rs = ps.getResultSet();
                rs.first();
                
                lb.setUserID(rs.getString("userID"));
                lb.setUsername(rs.getString("username"));
                
                if(rs.getString("username").equals(user))
                    lb.setStatus(true);
                else
                    lb.setStatus(false);
            }
            
            rs.close();
            ps.close();
            conn.close();
        }
        catch(Exception ex) {
            rs.close();
            ps.close();
            conn.close();
        }
        return lb;
    }
    
    
}
