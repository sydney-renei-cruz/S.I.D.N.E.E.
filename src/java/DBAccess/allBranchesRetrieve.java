/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Beans.*;
import Utilities.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author host
 */
public class allBranchesRetrieve extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }
/**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        //--------------
        //Get servlet context from the session
        //set the MIME type for the response message
        
        response.setContentType("text/plain");
        
        
        //---------------
        
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        String inText = "SELECT * FROM branch;";
        ConnectionBean cb = MySQL.query(inText, request, response);
        ResultSet rs = cb.getRS();
        
        try {
                ArrayList<BranchBean> resultList = new ArrayList<>();
                
                while(rs.next()){
                    resultList.add(BeanUtils.createBranchBean(rs.getString("branchNum"), rs.getString("branchName"), rs.getString("branchAddress"), rs.getString("branchPhoneNum")));
                }
                
                rs.close();
                
                request.setAttribute("branchList",resultList);
                
                request.getRequestDispatcher("WEB-INF/jsp/allBranches.jsp").forward(request, response);
	}
	catch (Exception ex){
	// handle any errors
		out.write("SQLException: " + ex);
	}
        
	
        cb.close();
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
