/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author host
 */
public class ImageRetrieve extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext context = request.getSession().getServletContext();
        
        response.setContentType("image/png");
        String imagePath = "/home/host/img/error.png";
        
        if(request.getParameter("pid")!=null){
            imagePath = "/home/host/img/product/" + request.getParameter("pid") + ".png";
        }
        else if(request.getParameter("branchNum")!=null){
            imagePath = "/home/host/img/branch/" + request.getParameter("branchNum") + ".png";
        }
        
	File file = new File(imagePath);
        
        if(!file.exists()){
            file.createNewFile();
            String inText ="";
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            
            if(request.getParameter("pid")!=null){
                inText = "SELECT image FROM product where productID=\"" + request.getParameter("pid")+ "\";";
            }
            else if(request.getParameter("branchNum")!=null){
                inText = "SELECT image FROM branch where branchNum=\"" + request.getParameter("branchNum") +"\";";
            }
             try {
                Class.forName(context.getInitParameter("jdbcDriver"));
            } catch(Exception ex) {

            }
            
            try {
                conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            } catch(SQLException ex) {

            }

            try {
                
                stmt = conn.createStatement();
                
                if(stmt.execute(inText))
                    rs = stmt.getResultSet();
                
                rs.first();
                
                Blob photo = rs.getBlob("image");
                
                FileOutputStream outFile = new FileOutputStream(file);
                InputStream in = photo.getBinaryStream();             
                
                int length = (int) photo.length();              
                int bufferSize = 1024;             
                byte[] buffer = new byte[bufferSize];              
                while ((length = in.read(buffer)) != -1) {    
                    outFile.write(buffer, 0, length);             
                }              
                in.close(); 
                outFile.close();
                stmt.close();
                conn.close();
            }
            catch(Exception e){
                
            }
        }
        
	BufferedImage bi = ImageIO.read(file);
        
	OutputStream out = response.getOutputStream();
	
        ImageIO.write(bi, "png", out);
        
	out.close();
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
