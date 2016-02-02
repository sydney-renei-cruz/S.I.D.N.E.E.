/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Beans.ConnectionBean;
import Utilities.MySQL;
import java.io.File;
import java.io.FileOutputStream;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author host
 */
public class EditBranch extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        if(request.getParameter("branchName") != null){
            String branchNum = request.getParameter("branchNum");
            String branchName = request.getParameter("branchName");
            String branchAddress = request.getParameter("branchAddress");
            String branchPhoneNum = request.getParameter("branchPhoneNum");
            
            Part filePart = request.getPart("image");
            
            Connection conn = null;
            InputStream is = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                //Class.forName("com.mysql.jdbc.Driver");
                Class.forName(context.getInitParameter("jdbcDriver"));
            } catch(Exception ex) {
                ex.printStackTrace(out);
            }
            
            try {
                conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            } catch(SQLException ex) {
                out.println(ex);
            }

            try {
                    String inText;
                    if(filePart!=null){
                        is = filePart.getInputStream();
                        inText = "UPDATE branch SET branchName=?, branchAddress=?, branchPhoneNum=?, image=? WHERE branchNum=?;";
                        ps = conn.prepareStatement(inText);
                        ps.setBlob(4, is);
                        ps.setString(5, branchNum);
                        out.println("1");
                    }
                     
                    else{
                        inText = "UPDATE branch SET branchName=?, branchAddress=?, branchPhoneNum=? WHERE branchNum=?;";
                        ps = conn.prepareStatement(inText);
                        ps.setString(4, branchNum);
                        out.println("3");
                    }
                    
                    ps.setString(1, branchName);
                    ps.setString(2, branchAddress);
                    ps.setString(3, branchPhoneNum);
                    
                    ps.executeUpdate();
                    
                    if(filePart!=null){
                        String imagePath =  context.getInitParameter("imgPath") + "branch/" + branchNum +".png";
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
                    response.sendRedirect("branchProductRetrieve?branch=" + branchNum);
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
            cb = MySQL.query("SELECT branchNum, branchName, branchAddress, branchPhoneNum FROM branch where branchNum = \""+ request.getParameter("branchNum") +"\";",request,response);
            ResultSet rs = cb.getRS();
            request.setAttribute("branchNum", request.getParameter("branchNum"));
            try{
                rs.first();
                request.setAttribute("branchName", rs.getString("branchName"));
                request.setAttribute("branchAddress", rs.getString("branchAddress"));
                request.setAttribute("branchPhoneNum", rs.getString("branchPhoneNum"));
                rs.close();
            }
            catch(Exception ex){
                
            }
            cb.close();
            request.getRequestDispatcher("WEB-INF/jsp/editBranch.jsp").forward(request,response);
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
