/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import Utilities.MySQL;
import Beans.*;
import java.io.File;
import java.io.FileOutputStream;
/**
 *
 * @author user
 */
@WebServlet(name = "editProduct", urlPatterns = {"/editProduct"})
public class editProduct extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editProduct</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        ServletContext context = request.getSession().getServletContext();
        
        if(request.getParameter("pid") != null){
            String productID = request.getParameter("pid");
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            float discountRate = Float.parseFloat(request.getParameter("discountRate"));
            float MSRP =  Float.parseFloat(request.getParameter("MSRP"));
            Part filePart = request.getPart("image");

            PrintWriter out = response.getWriter();
            
            InputStream is = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                //Class.forName("com.mysql.jdbc.Driver");
                Class.forName(context.getInitParameter("jdbcDriver"));
            } catch(Exception ex) {
                ex.printStackTrace(out);
            }

            Connection conn = null;

            try {
                conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            } catch(SQLException ex) {
                out.println(ex);
            }

            try {
                    String inText;
                    
                    if(filePart.getSize()!=0){
                        is = filePart.getInputStream();
                        inText = "UPDATE product SET productName=?, description=?, discountRate=?, MSRP=?, image=? WHERE productID=?;";
                        ps = conn.prepareStatement(inText);
                        ps.setBlob(5, is);
                        ps.setString(6, productID);
                        
                    }
                     
                    else{
                        inText = "UPDATE product set productName=?, description=?, discountRate=?, MSRP=? WHERE productID=?;";
                        ps = conn.prepareStatement(inText);
                        ps.setString(5, productID);
                    }
                    
                    ps.setString(1, productName);
                    ps.setString(2, description);
                    ps.setFloat(3, discountRate);
                    ps.setFloat(4, MSRP);
                    
                    int row = ps.executeUpdate();
                    
                    
                    if(filePart.getSize() != 0){
                        String imagePath =  context.getInitParameter("imgPath") + "product/" + productID +".png";
                        File file = new File(imagePath);

                        FileOutputStream outFile = new FileOutputStream(file);
                        is = filePart.getInputStream();          

                        int read = 0;         
                        int bufferSize = 1024;             
                        byte[] buffer = new byte[bufferSize];              
                        while ((read = is.read(buffer)) != -1) {    
                            outFile.write(buffer, 0, read);             
                        }

                        is.close(); 
                        outFile.close();
                    }
                    
                    ps.close();
                    conn.close();
                    response.sendRedirect("productRetrieve?pid=" + productID);
            }

            catch (Exception ex){
            // handle any errors
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

                    if (ps != null) {
                            try {
                                    ps.close();
                            } catch (SQLException sqlEx) { } // ignore

                            ps = null;
                            }

                    }
        }
        else{
            ConnectionBean cb = new ConnectionBean();
            cb = MySQL.query("SELECT productName, discountRate, MSRP, description from product where productID = \""+ request.getParameter("prodID") +"\";",request,response);
            ResultSet rs = cb.getRS();
            request.setAttribute("productID", request.getParameter("prodID"));
            try{
                rs.first();
                request.setAttribute("productName", rs.getString("productName"));
                request.setAttribute("description", rs.getString("description"));
                request.setAttribute("MSRP", rs.getString("MSRP"));
                request.setAttribute("discountRate", rs.getString("discountRate"));
                rs.close();
            }
            catch(Exception ex){
                
            }
            cb.close();
            request.getRequestDispatcher("WEB-INF/jsp/editProduct.jsp").forward(request,response);
        }
        
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