/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import Beans.*;
import java.util.ArrayList;
/**
 *
 * @author host
 */

public class productRetrieve extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        //--------------
        //Get servlet context from the session
        ServletContext context = request.getSession().getServletContext();
        //set the MIME type for the response message
        
        response.setContentType("text/plain");
        
        
        //---------------
        
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(context.getInitParameter("jdbcDriver"));
        } catch(Exception ex) {
            out.print("Error 3: " + ex);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
        } catch(SQLException ex) {
            out.write("Error 2: " + ex);
        }
        
        Statement stmt = null;
        ResultSet rs = null;
    
        String pidinput = request.getParameter("pid");
        
        int pidInt;
        try{
            pidInt = Integer.parseInt(pidinput);
        }
        catch(Exception e){
            pidInt = 0;
        }
        try {
        	String inText = "SELECT * FROM product WHERE productID = " + pidInt + ";";
		stmt = conn.createStatement();
		
		if (stmt.execute(inText)) {
			rs = stmt.getResultSet();
		}
                rs.first();
                request.setAttribute("product",(new ProductBean(rs.getString("productID"),rs.getString("productName"), rs.getDouble("MSRP"),rs.getString("Description"), rs.getDouble("DiscountRate"))));
                
                inText = "SELECT * FROM branchInventory WHERE productID = " + pidinput + ";";
                
                if (stmt.execute(inText)) {
			rs = stmt.getResultSet();
		}
                
                ArrayList<ProductInventoryBean> resultList = new ArrayList<>();
                
                while(rs.next()){
                    resultList.add(new ProductInventoryBean(rs.getInt("branchNum"), rs.getInt("productID"), rs.getFloat("branchDiscountRate"), rs.getInt("stock")));
                }
                
                request.setAttribute("productBranchData",resultList);
                if (stmt.execute(inText)) {
			rs = stmt.getResultSet();
		}
                
                request.getRequestDispatcher("WEB-INF/jsp/productPage.jsp").forward(request, response);
                stmt.close();
                conn.close();
	}
	catch (Exception ex){
	// handle any errors
		out.write("Error: empty parameters or no product ID with that name");
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
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
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
