/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.sql.DriverManager;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author host
 */
@WebListener
public class BackgroundScheduler implements ServletContextListener {
    
    private ScheduledExecutorService scheduler;
    
    public void contextInitialized(ServletContextEvent sce) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new ImageStore(sce.getServletContext()), 0, 1, TimeUnit.DAYS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class ImageStore implements Runnable{
    private ServletContext context;
    
    public ImageStore(ServletContext conin){
        context = conin;
    }
    
    public void run(){
        
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try { 
            Class.forName(context.getInitParameter("jdbcDriver"));
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            
            stmt = conn.createStatement();
            
            String inText = "SELECT productID, image from product;";
            
            if(stmt.execute(inText))
                rs = stmt.getResultSet();
                
            while(rs.next()){
                String imagePath =  context.getInitParameter("imgPath") + "product/" + rs.getString("productID")+".png";
                File file = new File(imagePath);
                Blob photo = rs.getBlob("image");

                FileOutputStream outFile = new FileOutputStream(file);
                InputStream in = photo.getBinaryStream();             

                int length = 0;            
                int bufferSize = 1024;             
                byte[] buffer = new byte[bufferSize];              
                while ((length = in.read(buffer)) != -1) {    
                    outFile.write(buffer, 0, length);             
                }
                in.close(); 
                outFile.close();
            }
            
            rs.close();
            stmt.close();
            
            stmt = conn.createStatement();
            
            inText = "SELECT branchNum, image from branch;";
            
            if(stmt.execute(inText))
                rs = stmt.getResultSet();
                
            while(rs.next()){
                String imagePath = context.getInitParameter("imgPath") + "branch/" + rs.getString("branchNum")+".png";
                File file = new File(imagePath);
                Blob photo = rs.getBlob("image");

                FileOutputStream outFile = new FileOutputStream(file);
                InputStream in = photo.getBinaryStream();             

                int length = 0;              
                int bufferSize = 1024;             
                byte[] buffer = new byte[bufferSize];              
                while ((length = in.read(buffer)) != -1) {    
                    outFile.write(buffer, 0, length);             
                }
                in.close(); 
                outFile.close();
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception ex) {
            
        }
    }
}