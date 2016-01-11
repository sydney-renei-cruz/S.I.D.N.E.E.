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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author host
 */
public class addProduct extends HttpServlet {

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
            out.println("<title>Servlet addProduct</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addProduct at " + request.getContextPath() + "</h1>");
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
        
        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        float MSRP =  Float.parseFloat(request.getParameter("MSRP"));
        float discountRate = Float.parseFloat(request.getParameter("discountRate"));
        
        PrintWriter out = response.getWriter();
        Part filePart = request.getPart("image");
        InputStream is = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream inputStream = null;
        
        ServletContext context = request.getSession().getServletContext();
        
        if(filePart!=null){
            is = filePart.getInputStream();
        }
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(context.getInitParameter("jdbcDriver"));
        } catch(Exception ex) {
            out.println("1");
        }
        
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
        } catch(SQLException ex) {
            out.println(ex);
        }
        
        try {
                String inText = "INSERT INTO product(productID, productName, MSRP, description, discountRate, image) VALUES(?, ?, ?, ?, ?, ?);";
                
                
                ps = conn.prepareStatement(inText);
                
                ps.setInt(1, productID);
                ps.setString(2, productName);
                ps.setFloat(3, MSRP);
                ps.setString(4, description);
                ps.setFloat(5, discountRate);
                
                if(is!=null){
                    ps.setBlob(6, is);
                }
                //sends the statement to the database server
                int row = ps.executeUpdate();
                if(row>0){
                    request.setAttribute("msg", "\nSucess");
                }
                else{
                    request.setAttribute("msg", "\nFailure");
                }
                
                request.getRequestDispatcher("WEB-INF/jsp/Output.jsp").forward(request, response);

                ps.close();
                conn.close();
	}
        
	catch (Exception ex){
	// handle any errors
            request.setAttribute("msg", "\nError: " + ex);
            request.getRequestDispatcher("WEB-INF/jsp/Output.jsp").forward(request, response);
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
