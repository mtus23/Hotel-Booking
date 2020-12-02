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
import jlp0012.dao.HotelRoomTypeDAO;
import jlp0012.dto.CartDTO;
import jlp0012.dto.HotelRoomTypeDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class AddToCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger LOG = Logger.getLogger(AddToCartController.class);
    private final String DETAIL = "LoadHotelDetailController";
    private final String ERROR = "error.jsp";
    private final String SEARCH = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            HotelRoomTypeDAO hotelRoomTypeDao = new HotelRoomTypeDAO();
            HttpSession session = request.getSession();
            try {
                String hotelId = request.getParameter("txtHotelId");
                String hotelRoom = request.getParameter("txtHotelRoom");
                CartDTO cart = (CartDTO) session.getAttribute("Cart");
                if (cart == null) {
                    cart = new CartDTO();
                }
                LOG.info(hotelRoom == null ? "yes" : "no");
                if (hotelRoom != null) {
                    if (!hotelRoom.equals("")) {
                        int hotelRoomId = Integer.parseInt(hotelRoom);
                        HotelRoomTypeDTO hotelRoomDto = hotelRoomTypeDao.getHotelRoomtype(hotelRoomId);

                        if (hotelRoomDto != null) {
                            cart.addHotelRoomToCart(hotelRoomDto, 1);
                            int total = cart.getTotalPrice();
                            session.setAttribute("TotalBill", total);
                            session.setAttribute("Cart", cart);
                            url = DETAIL + "?txtHotelId=" + hotelId;
                            request.setAttribute("AddSuccess", "Add to cart successfully");
                            if (hotelId.equals("")) {
                                url = SEARCH;
                            }
                        }
                    }
                } else {
                    url = SEARCH;
                    request.setAttribute("AddFail", "Add to cart fail");
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
