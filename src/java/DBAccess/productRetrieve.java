/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Beans.ProductBean;
import Beans.ProductInventoryBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import Beans.*;
import Utilities.MySQL;
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
        
        
        response.setContentType("text/plain");
        
        
        //---------------
        
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        ConnectionBean cb = new ConnectionBean();
        
        String pidinput = request.getParameter("pid");
        
        ResultSet rs = null;
        
        try {
                
        	String inText = "SELECT * FROM product WHERE productID = \"" + pidinput + "\";";
		cb = MySQL.query(inText,request,response);
                
                rs = cb.getRS();
                
                rs.first();
                
                request.setAttribute("product",(new ProductBean(rs.getString("productID"),rs.getString("productName"), rs.getDouble("MSRP"),rs.getString("Description"), rs.getDouble("DiscountRate"))));
                
                rs.close();
                cb.close();
                
                inText = "SELECT * FROM branchInventory WHERE productID = \"" + pidinput + "\";";
                cb = MySQL.query(inText,request,response);
                
                rs = cb.getRS();
                
                ArrayList<ProductInventoryBean> resultList = new ArrayList<>();
                
                while(rs.next()){
                    resultList.add(new ProductInventoryBean(rs.getInt("branchNum"), rs.getString("productID"), rs.getFloat("branchDiscountRate"), rs.getInt("stock")));
                }
                
                request.setAttribute("productBranchData",resultList);
                rs.close();
                cb.close();
                
                request.getRequestDispatcher("WEB-INF/jsp/productPage.jsp").forward(request, response);
	}
	catch (Exception ex){
	// handle any errors
		out.write("Error: empty parameters or no product ID with that name");
	}
        cb.close();
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
