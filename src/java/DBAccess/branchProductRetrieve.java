/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Beans.ConnectionBean;
import Beans.ProductBean;
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
        
        
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
    
        String branchinput = request.getParameter("branch");
        
        if(branchinput == null){
            request.getRequestDispatcher("/allBranchesRetrieve").forward(request, response);
        }
        
        String inText = "SELECT DISTINCT b.productID, p.productName from branchInventory b,product p where b.productID = p.productID and b.branchNum = "+ branchinput + ";";
        
        ResultSet rs = null;
        ConnectionBean cb = new ConnectionBean();
        try {
                cb = MySQL.query(inText,request,response);
                rs = cb.getRS();
                ArrayList<ProductBean> resultList = new ArrayList<>();
                
                while(rs.next()){
                    resultList.add(BeanUtils.createPartialProductBean(rs.getString("productID"),rs.getString("productName")));
                }
                
                rs.close();
                cb.close();
                
                request.setAttribute("branchProductList", resultList);
                
                inText = "SELECT branchName FROM branch WHERE branchNum = " + branchinput + ";";
                cb = MySQL.query(inText,request,response);
                rs = cb.getRS();
                
                rs.first();
                
                request.setAttribute("branchName",rs.getString("branchName"));
                
                rs.close();
                cb.close();
                
                request.getRequestDispatcher("WEB-INF/jsp/branchProducts.jsp").forward(request, response);
	}
	catch (Exception ex){
	// handle any errors
		request.getRequestDispatcher("/allBranchesRetrieve").forward(request, response);
	}
        cb.close();
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
