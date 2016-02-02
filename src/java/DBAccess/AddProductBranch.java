/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Beans.BranchBean;
import Beans.ConnectionBean;
import Beans.ProductBean;
import Utilities.MySQL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import Utilities.BeanUtils;
import javax.servlet.http.HttpSession;

/**
 *
 * @author host
 */
public class AddProductBranch extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        ServletContext context = request.getSession().getServletContext();
        
        HttpSession session = request.getSession(true);
        
        if(session.getAttribute("userID")==null){
            response.sendRedirect("Login");
        }
        else if(request.getParameter("stock") != null){
            int branchNum = Integer.parseInt(request.getParameter("branch"));
            int productID = Integer.parseInt(request.getParameter("product"));
            float branchDiscountRate = Float.parseFloat(request.getParameter("branchDiscountRate"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                //Class.forName("com.mysql.jdbc.Driver");
                Class.forName(context.getInitParameter("jdbcDriver"));
                conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            } catch(Exception ex) {
                ex.printStackTrace(out);
            }

            try {
                    String inText = "INSERT INTO branchInventory(branchNum, productID, branchDiscountRate, stock) VALUES(?,?,?,?);";
                   
                    ps = conn.prepareStatement(inText);
                   
                    ps.setInt(1,branchNum);
                    ps.setInt(2,productID);
                    ps.setFloat(3,branchDiscountRate);
                    ps.setInt(4,stock);
                    
                    int row = ps.executeUpdate();
                    
                    ps.close();
                    conn.close();
                    response.sendRedirect("branchProductRetrieve?branch=" + branchNum);
            }

            catch (Exception ex){
            // handle any errors
                ex.printStackTrace(out);
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
            ConnectionBean cbB = new ConnectionBean();
            cbB = MySQL.query("SELECT branchNum, branchName FROM branch;",request,response);
            ConnectionBean cbP = new ConnectionBean();
            cbP = MySQL.query("SELECT productID, productName FROM product;",request,response);
            ResultSet rs = cbB.getRS();
            LinkedList<BranchBean> branchList = new LinkedList();
            LinkedList<ProductBean> productList = new LinkedList();
            try{
                while(rs.next()){
                    BranchBean bb = null;
                    bb = BeanUtils.createBranchBean(rs.getString("branchNum"),rs.getString("branchName"));
                    branchList.add(bb);
                }
                rs = cbP.getRS();
                while(rs.next()){
                    ProductBean pb = null;
                    pb = BeanUtils.createProductBean(rs.getString("productID"),rs.getString("productName"));
                    productList.add(pb);
                    
                }
                rs.close();
            }
            catch(Exception ex){
                
            }
            cbB.close();
            cbP.close();
            request.setAttribute("productList", productList);
            request.setAttribute("branchList", branchList);
            request.getRequestDispatcher("WEB-INF/jsp/addProductBranch.jsp").forward(request,response);
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
