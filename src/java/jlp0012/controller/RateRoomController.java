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
import jlp0012.dao.RateDAO;
import jlp0012.dto.OrderDTO;
import jlp0012.dto.RateDTO;
import jlp0012.dto.UserDTO;
import jlp0012.ulti.ValidUUID;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class RateRoomController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger LOG = Logger.getLogger(RateRoomController.class);
    private final String SUCCESS = "LoadHistoryController";
    private final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            OrderDAO orderDao = new OrderDAO();
            RateDAO rateDao = new RateDAO();
            boolean check = true;
            HttpSession session = request.getSession();
            try {
                String txtHotelRoomId = request.getParameter("txtHotelRoomId");
                String txtOrderId = request.getParameter("txtOrderId");
                String txtPoint = request.getParameter("txtRate");
                OrderDTO order = null;

                if (!ValidUUID.isUUID(txtOrderId)) {
                    check = false;
                } else {
                    order = orderDao.getOrder(txtOrderId);
                    if (order.getStatusId() != 10) {
                        check = false;
                    }
                }
                if (check) {
                    int hotelRoomId = Integer.parseInt(txtHotelRoomId);
                    int point = Integer.parseInt(txtPoint);
                    UserDTO user = (UserDTO) session.getAttribute("User");
                    RateDTO rate = rateDao.getRate(user.getEmail(), txtOrderId, hotelRoomId);
                    if (rate != null) {
                        rate.setPoint(point);
                        rateDao.updateRate(rate);
                    } else {
                        rate = new RateDTO(user.getEmail(), point, hotelRoomId, txtOrderId);
                        rateDao.insertRate(rate);
                    }

                    request.setAttribute("RatingSuccess", "Rating successfully");
                } else {
                    request.setAttribute("RatingError", "Rating fail");
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
