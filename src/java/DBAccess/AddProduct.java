/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author host
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct"})
public class AddProduct extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        Statement stmt = null;
        ResultSet rs = null;
        
        String pID = request.getParameter("productID");
        String pName = request.getParameter("productName");
        float MSRP = Float.parseFloat(request.getParameter("MSRP"));
        String description = request.getParameter("description");
        float discountRate = Float.parseFloat(request.getParameter("discountRate"));
        
        InputStream inputStream = null;
        
        Part filePart = request.getPart("photo");
        
        if(filePart!=null){
            inputStream = filePart.getInputStream();
        }
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(context.getInitParameter("jdbcDriver"));
        } catch(Exception ex) {
            out.println("Error (jdbcDriver):\n");
            ex.printStackTrace(out);
        }
        
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
        } catch(SQLException ex) {
            out.println("Error (conn): \n");
            ex.printStackTrace(out);
        }
        
        try {
                String inText = "INSERT INTO product (productID, productName, MSRP, description, discountRate, image) values (?, ?, ?, ?, ?)";
                
                PreparedStatement ps = conn.prepareStatement(inText);
                ps.setString(1, pID);
                ps.setString(2, pName);
                ps.setFloat(3, MSRP);
                ps.setString(4, description);
                ps.setFloat(5, discountRate);
                
                if(inputStream!=null){
                    ps.setBlob(6, inputStream);
                }
                
            //sends the statement to the database server
                if(ps.executeUpdate()>0){
                    request.setAttribute("msg", "Sucess");
                }
            
                else{
                    request.setAttribute("msg", "Failure");
                }
            
                request.getRequestDispatcher("WEB-INF/Output.jsp").forward(request, response);
                
                response.setContentType("image/png");
                String imagePath = context.getInitParameter("imgPath") + "product/" + pID + ".png";

                File file = new File(imagePath);
                BufferedImage bi;

                try{
                    bi = ImageIO.read(file);
                }
                catch(javax.imageio.IIOException ex){
                    file = new File(context.getInitParameter("imgPath") + "error.png");
                    bi = ImageIO.read(file);
                }

                OutputStream outImg = response.getOutputStream();
                File cacheDir = new File(context.getInitParameter("imgPath") +"cache");
                ImageIO.setCacheDirectory(cacheDir);
                ImageIO.write(bi, "png", outImg);

                outImg.close();
                
                stmt.close();
                conn.close();
	}
        
	catch (Exception ex){
	// handle any errors
            /**request.setAttribute("msg", " Error: " + ex);
            request.getRequestDispatcher("WEB-INF/Output.jsp").forward(request, response);**/
            out.println("Error (DB connection): ");
            ex.printStackTrace(out);
	}
        
	finally {
	// it is a good idea to release
	// resources in a finally{} block
	// in reverse-order of their creation
	// if they are no-longer needed

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqlEx) { } // ignore
			rs = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqlEx) { } // ignore
			stmt = null;
			}
		}
        
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
