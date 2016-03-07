/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Beans.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
    
    public static LoginBean register(String userID, String username, String password, Part image, HttpServletRequest request, HttpServletResponse response) throws Exception{
        LoginBean lb = new LoginBean();
        lb.setStatus(false);
        ServletContext context = request.getSession().getServletContext();
        
        PrintWriter out = null;
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            out = response.getWriter();
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(context.getInitParameter("jdbcDriver"));
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            
            Random rand = new Random();
            
            int salt = rand.nextInt(1000);
            
            String hashedPass = password + salt;
            
            InputStream is = null;
            
            
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
            
            String inText; 
            if(image.getSize() != 0){
                inText = "INSERT INTO user(userID, username, passHash, salt, image) VALUES(?,?,?,?,?);";
                ps = conn.prepareStatement(inText);
                is = image.getInputStream();
                ps.setBlob(5, is);
            }
            else{
                inText = "INSERT INTO user(userID, username, passHash, salt) VALUES(?,?,?,?);";
                ps = conn.prepareStatement(inText);
            }
            ps.setString(1, userID);
            ps.setString(2, username);
            ps.setString(3, hashedPass);
            ps.setString(4, "" + salt);
            
            ps.executeUpdate();
            
            inText = "SELECT username, userID FROM user WHERE username=? AND passHash=?;";
            
            ps = conn.prepareStatement(inText);
            
            ps.setString(1, username);
            ps.setString(2, hashedPass);
            
            rs = ps.executeQuery();
            
            rs.first();
            
            lb.setUsername(rs.getString("username"));
            lb.setUserID(rs.getString("userID"));
            lb.setStatus(true);
            
            if(image.getSize()!=0){
                InputStream inputStream = null;
                String imagePath =  context.getInitParameter("imgPath") + "user/" + userID +".png";
                File file = new File(imagePath);

                FileOutputStream outFile = new FileOutputStream(file);
                inputStream = image.getInputStream();          

                int read = 0;    
                int bufferSize = 1024;             
                byte[] buffer = new byte[bufferSize];              
                
                while ((read = inputStream.read(buffer)) != -1) {    
                    outFile.write(buffer, 0, read);             
                }

                inputStream.close(); 
                outFile.close();
            }
            
            rs.close();
            ps.close();
            conn.close();
        }
        catch(Exception ex) {
            StackTraceElement[] elements = ex.getStackTrace();
            request.setAttribute("msg", elements[0]);
            request.getRequestDispatcher("errorPage.jsp").forward(request,response);
        }
        return lb;
        
    }
    
    public static LoginBean login(String user, String pass, HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        PrintWriter out = null;
        ServletContext context = request.getSession().getServletContext();
        
        LoginBean lb = new LoginBean();
        LinkedList branchNum = new LinkedList();
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
            
            String hashedPass = pass + salt;
            
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
            
            inText = "select branchNum from branchUserEdit where userID=?;";
            ps = conn.prepareStatement(inText);
            ps.setString(1, lb.getUserID());
            
            if(ps.execute()){
                rs = ps.getResultSet();
                while(rs.next()){
                    branchNum.add(rs.getString("branchNum"));
                }
            }
            lb.setBranch(branchNum);
            rs.close();
            ps.close();
            conn.close();
        }
        catch(Exception ex) {
            StackTraceElement[] elements = ex.getStackTrace();
            request.setAttribute("msg", elements[0]);
            request.getRequestDispatcher("errorPage.jsp").forward(request,response);
        }
        return lb;
    }
    
    public static ConnectionBean search(String phrase, HttpServletRequest request, HttpServletResponse response) throws Exception{
        ConnectionBean cb = new ConnectionBean();
        
        ServletContext context = request.getSession().getServletContext();
        
        LoginBean lb = new LoginBean();
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PrintWriter out = null;
        try {
            out = response.getWriter();
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(context.getInitParameter("jdbcDriver"));
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));

            String inText = "select productID, productName, MSRP, description from product where description like ? or productName like ? or description like ? or productID like ?;";
            ps = conn.prepareStatement(inText);
            
            ps.setString(1, "%" + phrase + "%");
            ps.setString(2, "%" + phrase + "%");
            ps.setString(3, "%" + phrase + "%");
            ps.setString(4, "%" + phrase + "%");
            
            rs = ps.executeQuery();
            cb.setRS(rs);
            cb.setPS(ps);
            cb.setConn(conn);
        }
        catch(Exception ex){
            StackTraceElement[] elements = ex.getStackTrace();
            request.setAttribute("msg", elements[0]);
            request.getRequestDispatcher("errorPage.jsp").forward(request,response);
        }
        return cb;
    }
}
