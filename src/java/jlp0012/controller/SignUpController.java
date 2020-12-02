/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jlp0012.dao.UserDAO;
import jlp0012.dto.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class SignUpController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static Logger LOG = Logger.getLogger(SignUpController.class);
    private final String ERROR = "signup.jsp";
    private final String LOGIN = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            UserDAO dao = new UserDAO();
            boolean check = true;
            try {
                String email = request.getParameter("txtEmail");
                String name = request.getParameter("txtName");
                String phoneNumber = request.getParameter("txtPhone");
                String address = request.getParameter("txtAddress");
                String pass = request.getParameter("txtPassword");
                String rePass = request.getParameter("txtRePassword");

                if (dao.getUserName(email) != null) {
                    request.setAttribute("ErrorEmail", "Email existed");
                    check = false;
                }
                if (!pass.equalsIgnoreCase(rePass)) {
                    request.setAttribute("ErrorPassword", "2 password are diffrent");
                    check = false;
                }
                if (check) {
                    Date currentDate = new Date(System.currentTimeMillis());
                    UserDTO dto = new UserDTO(email, name, phoneNumber, address, pass, currentDate);
                    dao.signup(dto);
                    request.setAttribute("SignUpSuccess", "Sign up successfully");
                    url = LOGIN;
                }
            } catch (SQLException | ClassNotFoundException | NamingException ex) {
                LOG.error(ex.toString());
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
