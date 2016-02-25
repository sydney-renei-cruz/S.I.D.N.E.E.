/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Beans.*;
import java.io.PrintWriter;
import java.security.MessageDigest;
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
        
        PrintWriter out = null;
        ServletContext context = request.getSession().getServletContext();
        
        LoginBean lb = new LoginBean();
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            out = response.getWriter();
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(context.getInitParameter("jdbcDriver"));
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));

            String inText = "select salt from user where username = ?;";
            String salt = "";
            out.println(inText);
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
            out.println(pass);
            String hashedPass = pass + salt;
            out.println(hashedPass);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(hashedPass.getBytes());
            byte[] digest = md.digest();
            
            char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                         '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuffer buf = new StringBuffer();
            
            for (int j=0; j<digest.length; j++) {
                buf.append(hexDigit[(digest[j] >> 4) & 0x0f]);
                buf.append(hexDigit[digest[j] & 0x0f]);
            }
            
            hashedPass = buf.toString();
            out.println(hashedPass);
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
            ex.printStackTrace(out);
        }
        return lb;
    }
    
    
}
