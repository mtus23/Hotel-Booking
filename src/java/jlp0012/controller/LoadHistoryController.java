/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jlp0012.dao.HotelRoomTypeDAO;
import jlp0012.dao.OrderDAO;
import jlp0012.dao.OrderDetailDAO;
import jlp0012.dao.RateDAO;
import jlp0012.dto.HotelRoomTypeDTO;
import jlp0012.dto.OrderDTO;
import jlp0012.dto.OrderDetailDTO;
import jlp0012.dto.RateDTO;
import jlp0012.dto.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class LoadHistoryController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger LOG = Logger.getLogger(LoadHistoryController.class);
    private final String SUCCESS = "history.jsp";
    private final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            OrderDAO orderDao = new OrderDAO();
            HotelRoomTypeDAO hotelRoomDao = new HotelRoomTypeDAO();
            OrderDetailDAO orderDetailDao = new OrderDetailDAO();
            HttpSession session = request.getSession();
            RateDAO rateDao = new RateDAO();
            try {
                UserDTO user = (UserDTO) session.getAttribute("User");
                List<OrderDTO> listOrder = orderDao.getListOrder(user.getEmail());
                if (listOrder != null) {
                    Map<OrderDTO, List<HotelRoomTypeDTO>> mapOrderDetail = new HashMap<>();
                    Map<Integer, Integer> mapRate = new HashMap<>();
                    for (OrderDTO dto : listOrder) {
                        List<HotelRoomTypeDTO> listHotelroom = new ArrayList<>();
                        List<OrderDetailDTO> listOrdDetail = orderDetailDao.getListOrderDetail(dto);
                        for (OrderDetailDTO ordDetail : listOrdDetail) {
                            RateDTO rate = rateDao.getRate(user.getEmail(), dto.getOrderId(), ordDetail.getHotelRoomId());
                            HotelRoomTypeDTO hotelRoomDto = hotelRoomDao.getHotelRoomById(ordDetail.getHotelRoomId());
                            hotelRoomDto.setQuantity(ordDetail.getQuantity());
                            listHotelroom.add(hotelRoomDto);
                            if (rate != null) {
                                mapRate.put(ordDetail.getHotelRoomId(), rate.getPoint());
                            }
                        }
                        mapOrderDetail.put(dto, listHotelroom);
                    }
                    request.setAttribute("ListOrder", listOrder);
                    request.setAttribute("MapOrderDetail", mapOrderDetail);
                    request.setAttribute("MapRate", mapRate);
                } else {
                    request.setAttribute("ListOrderError", "List order is empty");
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
