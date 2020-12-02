/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jlp0012.dao.OrderDAO;
import jlp0012.dto.UserDTO;
import jlp0012.ulti.StringEncrypter;
import jlp0012.ulti.ValidUUID;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class ConfirmBookingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger LOG = Logger.getLogger(ConfirmBookingController.class);
    private final String SUCCESS = "search.jsp";
    private final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            HttpSession session = request.getSession();
            OrderDAO orderDao = new OrderDAO();
            StringEncrypter encrypter = new StringEncrypter();
            boolean check = false;
            try {
                String order = request.getParameter("order");
                String userId = request.getParameter("user");
                UserDTO user = (UserDTO) session.getAttribute("User");
                if (ValidUUID.isUUID(order) && encrypter.getEncryptedString(user.getEmail()).equals(userId)) {
                    String email = orderDao.getOrderEmail(order);
                    if (order.equals(order) && email.equals(user.getEmail())) {
                        orderDao.updateStatus(order, 5);
                        request.setAttribute("CheckOutConfirmSuccess", "Confirm check out successfully");
                        check = true;
                    }
                }
                if (!check) {
                    request.setAttribute("CheckOutConfirmFail", "Confirm check out fail");
                }
                url = SUCCESS;
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
