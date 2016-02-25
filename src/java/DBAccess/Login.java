/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Beans.LoginBean;
import Utilities.MySQL;
import java.io.IOException;
import java.io.PrintWriter;
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
        HttpSession session = request.getSession(true);
        
        if(session.getAttribute("userID")!=null){
            Cookie uCookie = new Cookie("userID", null);
            uCookie.setMaxAge(0);
            response.addCookie(uCookie);
            
            Cookie unCookie = new Cookie("username",null);
            unCookie.setMaxAge(0);
            response.addCookie(unCookie);
            session.invalidate();
            response.sendRedirect("allProductsRetrieve");
        }
        
        else{
            request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp").forward(request,response);
        }
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
        //set the MIME type for the response message
        HttpSession session = request.getSession(true);
        session = request.getSession(true);
        Cookie cookie = new Cookie("userID", null);
        response.addCookie(cookie);
            //---------------

        response.setCharacterEncoding("UTF-8");

        String input = request.getParameter("username");
        String pass =  request.getParameter("password");
        LoginBean lb = new LoginBean();
        try {
            lb = MySQL.login(input, pass, request, response);
        } catch (Exception ex) {
        
        }

        if(lb.getStatus()){
            Cookie uiCookie = new Cookie("userID", lb.getUserID());
            uiCookie.setMaxAge(3600);
            Cookie unCookie = new Cookie("username", lb.getUsername());
            uiCookie.setMaxAge(3600);
            response.addCookie(uiCookie);
            response.addCookie(unCookie);
            session.setAttribute("userID", lb.getUserID());
            response.sendRedirect("index.html");
        }
        else{
            request.setAttribute("errorMessage", "Wrong username/password");
            request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp").forward(request, response);
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
