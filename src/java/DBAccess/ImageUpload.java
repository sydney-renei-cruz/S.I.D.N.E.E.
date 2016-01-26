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
public class ImageUpload extends HttpServlet {

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
       
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream inputStream = null;
        String pid = request.getParameter("branchNum");
        Part filePart = request.getPart("image");
        
        if(filePart!=null){
            inputStream = filePart.getInputStream();
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
                String inText = "UPDATE branch SET image=? where branchNum=?";
                
                ps = conn.prepareStatement(inText);
                ps.setString(2, pid);
                
                if(inputStream!=null){
                    ps.setBlob(1, inputStream);
                }
                out.write("2");
            //sends the statement to the database server
            int row = ps.executeUpdate();
            if(row>0){
                request.setAttribute("msg", "\nSucess");
            }
            else{
                request.setAttribute("msg", "\nFailure");
            }
            out.write("3");
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
