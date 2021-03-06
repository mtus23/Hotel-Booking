/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jlp0012.dao.UserDAO;
import jlp0012.dto.UserDTO;
import jlp0012.ulti.StringEncrypter;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class ResetPassController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ERROR = "reset.jsp";
    private final String SUCCESS = "login.jsp";
    private static final Logger LOG = Logger.getLogger(ResetPassController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            UserDAO userDao = new UserDAO();
            StringEncrypter encrypt = new StringEncrypter();
            boolean check = true;
            try {
                String pass = request.getParameter("txtPassword");
                String rePass = request.getParameter("txtRePassword");
                String id = request.getParameter("userId");

                UserDTO userFound = null;
                if (!pass.equals(rePass)) {
                    request.setAttribute("ResetPasswordError", "2 password are diffrent");
                    check = false;
                }
                List<UserDTO> listUser = userDao.getAll();
                for (UserDTO user : listUser) {
                    LOG.info(encrypt.getEncryptedString(user.getEmail()));
                    if (encrypt.getEncryptedString(user.getEmail()).equals(id)) {
                        userFound = new UserDTO(user.getEmail());
                        break;
                    }
                }
                if (userFound == null) {
                    request.setAttribute("ResetPasswordError", "Email is invalid");
                    check = false;
                }
                if (check && userFound != null) {
                    String encruptedPass = encrypt.getEncryptedString(pass);
                    userFound.setPassword(encruptedPass);
                    userDao.changePassword(userFound);
                    url = SUCCESS;
                    request.setAttribute("ResetPssswordSuccess", "Reset Password success");
                }
            } catch (SQLException | NamingException | ClassNotFoundException ex) {
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
