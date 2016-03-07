/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author host
 */
public class AddBranch extends HttpServlet {

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
        
        HttpSession session = request.getSession(true);
        
        if(session.getAttribute("userID")==null){
            response.sendRedirect("Login");
        }
        
        else if(request.getParameter("submitted")!=null){
            ServletContext context = request.getSession().getServletContext();
            PrintWriter out = response.getWriter();

            ResultSet rs = null;

            int branchNum = Integer.parseInt(request.getParameter("branchNum"));
            String branchName = request.getParameter("branchName");
            String branchAddress = request.getParameter("branchAddress");
            String branchPhoneNum = request.getParameter("branchPhoneNum");
            
            PreparedStatement ps = null;
            InputStream inputStream = null;

            Part filePart = request.getPart("image");

            if(filePart.getSize()!=0){
                inputStream = filePart.getInputStream();
            }

            try {
                //Class.forName("com.mysql.jdbc.Driver");
                Class.forName(context.getInitParameter("jdbcDriver"));
            } catch(Exception ex) {
                StackTraceElement[] elements = ex.getStackTrace();
                request.setAttribute("msg", elements[0]);
                request.getRequestDispatcher("errorPage.jsp").forward(request,response);
            }

            Connection conn = null;

            try {
                conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            } catch(SQLException ex) {
                StackTraceElement[] elements = ex.getStackTrace();
            request.setAttribute("msg", elements[0]);
            request.getRequestDispatcher("errorPage.jsp").forward(request,response);
            }

            try {
                    String inText = "";
                    if(filePart.getSize()!=0){
                        inText = "INSERT INTO branch(branchNum, branchName, branchAddress, branchPhoneNum, image) values (?, ?, ?, ?, ?)";
                        ps = conn.prepareStatement(inText);
                        ps.setBlob(5, inputStream);
                    }
                    else{
                        inText = "INSERT INTO branch(branchNum, branchName, branchAddress, branchPhoneNum) values (?, ?, ?, ?)";
                        ps = conn.prepareStatement(inText);
                    }
                    
                    ps.setInt(1, branchNum);
                    ps.setString(2, branchName);
                    ps.setString(3, branchAddress);
                    ps.setString(4, branchPhoneNum);

                    

                //sends the statement to the database server
                    ps.executeUpdate();
                    
                    if(filePart.getSize()!=0){
                        String imagePath =  context.getInitParameter("imgPath") + "branch\\" + branchNum +".png";
                        File file = new File(imagePath);

                        FileOutputStream outFile = new FileOutputStream(file);
                        inputStream = filePart.getInputStream();          

                        int read = 0;         
                        int bufferSize = 1024;             
                        byte[] buffer = new byte[bufferSize];              
                        while ((read = inputStream.read(buffer)) != -1) {    
                            outFile.write(buffer, 0, read);             
                        }

                        inputStream.close(); 
                        outFile.close();
                    }

                    ps.close();
                    conn.close();
                    if(request.getParameter("add")!=null){
                        response.sendRedirect("addBranch");
                    }
                    else{
                        response.sendRedirect("allBranchesRetrieve?pid=" + branchNum);
                    }
            }

            catch (Exception ex){
                StackTraceElement[] elements = ex.getStackTrace();
                request.setAttribute("msg", elements[0]);
                request.getRequestDispatcher("errorPage.jsp").forward(request,response);
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
            request.getRequestDispatcher("WEB-INF/jsp/addBranch.jsp").forward(request,response);
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
