/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author host
 */
public class ImageRetrieve extends HttpServlet {

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
        
        ServletContext context = request.getSession().getServletContext();
        
        response.setContentType("image/png");
        String imagePath = context.getInitParameter("imgPath") + "error.png";
        
        if(request.getParameter("pid")!=null){
            imagePath = context.getInitParameter("imgPath") + "product\\" + request.getParameter("pid") + ".png";
        }
        else if(request.getParameter("branchNum")!=null){
            imagePath = context.getInitParameter("imgPath") + "branch\\" + request.getParameter("branchNum") + ".png";
        }
        else if(request.getParameter("uid")!=null){
            imagePath = context.getInitParameter("imgPath") + "user\\" + request.getParameter("uid") + ".png";
        }
        
	File file = new File(imagePath);
        BufferedImage bi;
        try{
            bi = ImageIO.read(file);
        }
        catch(javax.imageio.IIOException ex){
            file = new File(context.getInitParameter("imgPath") + "error.png");
            bi = ImageIO.read(file);
        }
        
	OutputStream outImg = response.getOutputStream();
        File cacheDir = new File(context.getInitParameter("imgPath") +"cache");
	ImageIO.setCacheDirectory(cacheDir);
        ImageIO.write(bi, "png", outImg);
        
	outImg.close();
        
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
