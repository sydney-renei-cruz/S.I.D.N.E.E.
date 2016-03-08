/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Beans.ConnectionBean;
import Utilities.MySQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class EditProductBranch extends HttpServlet {

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
        String stock = request.getParameter("stock");
        String bdr = request.getParameter("branchDiscountRate");
        String prodID = request.getParameter("productID");
        String branchNum = request.getParameter("branchNum");
        
        if(stock!=null && bdr!=null){
            String inText = "UPDATE branchInventory SET stock=\""+stock+"\", branchDiscountRate=\""+bdr+"\" WHERE branchNum = \"" + branchNum + "\" AND productID = \""+ prodID +"\";";
            ConnectionBean cb = MySQL.query(inText, request, response);
            request.setAttribute("msg", "Update");
            request.setAttribute("msg", "Update successful<br><br><a href=productRetrieve?pid=" + prodID +">Back to Product</a><br><a href=branchProductRetrieve?branch=" + branchNum+">Back to Branch</a>");
            request.getRequestDispatcher("GenPage.jsp").forward(request,response);
        }
        else if(prodID != null && branchNum != null){
            String inText = "SELECT branchDiscountRate, stock FROM branchInventory WHERE branchNum = \"" + branchNum + "\" AND productID = \""+ prodID +"\";";
            ConnectionBean cb = MySQL.query(inText, request, response);
            try{
                ResultSet rs = cb.getRS();
                rs.first();
                request.setAttribute("branch", branchNum);
                request.setAttribute("product", prodID);
                request.setAttribute("stock", rs.getString("stock"));
                request.setAttribute("bdr", rs.getString("branchDiscountRate"));
                request.getRequestDispatcher("WEB-INF/jsp/editBranchProduct.jsp").forward(request,response);
            }catch(Exception ex){
                StackTraceElement[] elements = ex.getStackTrace();
                request.setAttribute("msg", elements[0]);
                request.getRequestDispatcher("errorPage.jsp").forward(request,response);
            }
        }
        else{
            request.setAttribute("msg", "Please select a branch and select the product to edit first.");
            request.getRequestDispatcher("GenPage.jsp").forward(request,response);
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
