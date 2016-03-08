/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Beans.LoginBean;
import Utilities.MySQL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
public class Register extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.getRequestDispatcher("WEB-INF/jsp/Register.jsp").forward(request,response);
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
        String userID = request.getParameter("userID");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Part filePart = request.getPart("image");
        
        LoginBean lb = null;
        
        try{
        lb = MySQL.register(userID, username, password, filePart, request, response);
        }
        catch(Exception ex){
        }
        
        HttpSession session = request.getSession();
        
        if(lb.getStatus()){
            Cookie uiCookie = new Cookie("userID", lb.getUserID());
            uiCookie.setMaxAge(3600);
            Cookie unCookie = new Cookie("username", lb.getUsername());
            uiCookie.setMaxAge(3600);
            response.addCookie(uiCookie);
            response.addCookie(unCookie);
            session.setAttribute("userID", lb.getUserID());
            response.sendRedirect("userRetrieve");
        }
        
        if(filePart.getSize()!=0){
            
                InputStream inputStream = null;
                ServletContext context = request.getSession().getServletContext();
                        String imagePath =  context.getInitParameter("imgPath") + "user\\" + lb.getUserID() +".png";
                        File file = new File(imagePath);

                        FileOutputStream outFile = new FileOutputStream(file);
                        inputStream = filePart.getInputStream();          

                        int read = 0;         
                        int bufferSize = 1024;             
                        byte[] buffer = new byte[bufferSize];              
                        while ((read = inputStream.read(buffer)) != -1) {    
                            outFile.write(buffer, 0, read);             
                        }

                        inputStream.close(); 
                        outFile.close();
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
