/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Beans.ProductBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author host
 */
public class branchProductRetrieve extends HttpServlet {

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
    
        String branchinput = request.getParameter("branch");
        
        if(branchinput == null){
            request.getRequestDispatcher("/allBranchesRetrieve").forward(request, response);
        }
        
        int branchInt;
        
        try{
            branchInt = Integer.parseInt(branchinput);
        }
        catch(Exception e){
            branchInt= 0;
        }
        
        try {
                
        	String inText = "SELECT DISTINCT b.productID, p.productName from branchInventory b,product p where b.productID = p.productID and b.branchNum = "+ branchinput + ";";
                
		stmt = conn.createStatement();
		
		if (stmt.execute(inText)) {
			rs = stmt.getResultSet();
		}
                ArrayList<ProductBean> resultList = new ArrayList<>();
                
                while(rs.next()){
                    resultList.add(new ProductBean(rs.getString("productID"),rs.getString("productName")));
                }
                inText = "SELECT branchName FROM branch WHERE branchNum = " + branchinput;
                
                if(stmt.execute(inText)){
                    rs = stmt.getResultSet();
                }
                
                request.setAttribute("branchProductList", resultList);
                
                rs.first();
                request.setAttribute("branchName",rs.getString("branchName"));
                
                request.getRequestDispatcher("WEB-INF/jsp/branchProducts.jsp").forward(request, response);
                
	stmt.close();
	conn.close();
	}
	catch (Exception ex){
	// handle any errors
		request.getRequestDispatcher("/allBranchesRetrieve").forward(request, response);
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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request,response);
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    

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
