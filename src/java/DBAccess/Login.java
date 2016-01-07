/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import com.google.common.base.Charsets;
import com.google.common.hash.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author host
 */
public class Login extends HttpServlet {

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
        request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp").forward(request, response);
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
        //set the MIME type for the response message
        
        response.setContentType("text/html");
        
        Cookie cookie = new Cookie("userID", null);
        response.addCookie(cookie);
        //---------------
        
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(context.getInitParameter("jdbcDriver"));
        } catch(Exception ex) {
            out.print(ex);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
        } catch(SQLException ex) {
            out.println(ex);
        }
        
        Statement stmt = null;
        ResultSet rs = null;
    
        String input = request.getParameter("username");
        String pass =  request.getParameter("password");
        
        try {
        	String inText = "select salt, passHash from user where username = \"" + input + "\";";
		String hashed = "junktext";
                
                stmt = conn.createStatement();
                
		if (stmt.execute(inText)) {
			rs = stmt.getResultSet();
		}
                
                if(rs.next()){
                    hashed = Hashing.sha1().hashString(pass + rs.getString("salt"), Charsets.UTF_8).toString();
                }
                
                inText = "select username, userID from user where username = \"" + input +"\" and passHash = \"" + hashed +"\";";
                
                if(stmt.execute(inText)){
                    rs = stmt.getResultSet();
                    rs.first();
                    if(rs.getString("username").equalsIgnoreCase(input)){
                        response.addCookie(new Cookie("userID", rs.getString("userID")));
                        request.getSession(true).setAttribute("userID", rs.getString("userID"));
                        response.sendRedirect("index.html");
                    }
                }
            stmt.close();
            conn.close();
        
	}
        
	catch (Exception ex){
	// handle any errors
		request.setAttribute("errorMessage", "Wrong username/password");
                request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp").forward(request, response);
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
