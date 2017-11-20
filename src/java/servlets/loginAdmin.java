/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michiel
 */
@WebServlet(name = "loginAdmin", urlPatterns = {"/loginAdmin"})
public class loginAdmin extends HttpServlet {
   public static final String SESS_ADMIN = "ADMIN";
   public static final String ADMIN_NAME="ditchAdmin";
   public static final String ADMIN_PASS="d33z_nu7z";

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
    
    
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(username.equals(ADMIN_NAME))
        {
            if(password.equals("d33z_nu7z"))
            {
               //response.sendRedirect("AdministratorPage.jsp");
                 request.getRequestDispatcher("AdministratorPage.jsp").forward(request, response);
               request.getSession().setAttribute(SESS_ADMIN,username);
               
            }
            else
            {
              //response.sendRedirect("administratorLoginPage.html");
                 request.getRequestDispatcher("AdministratorLoginPage.html").forward(request, response);
            }
        }
        else
        {
            //response.sendRedirect("administratorLoginPage.html
            request.getRequestDispatcher("AdministratorLoginPage.html").forward(request, response);
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
