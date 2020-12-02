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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jlp0012.dao.CodeDAO;
import jlp0012.dao.HotelRoomTypeDAO;
import jlp0012.dto.CartDTO;
import jlp0012.dto.CodeDTO;
import jlp0012.dto.HotelRoomTypeDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class CheckOutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger LOG = Logger.getLogger(CheckOutController.class);
    private final String DETAIL = "confirm.jsp";
    private final String ERROR = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            boolean checkDate = true;
            boolean check = true;
            CodeDAO codeDao = new CodeDAO();
            HttpSession session = request.getSession();
            HotelRoomTypeDAO hotelRoomDao = new HotelRoomTypeDAO();
            List<String> error = new ArrayList<>();
            int percent = 0;
            try {
                Map<Integer, String> mapHotel = (Map<Integer, String>) session.getAttribute("MapHotel");
                Map<Integer, String> mapRoomType = (Map<Integer, String>) session.getAttribute("MapRoomType");
                CartDTO cart = (CartDTO) session.getAttribute("Cart");
                String discountCode = request.getParameter("txtCode");
                String name = request.getParameter("txtCustomer");
                String address = request.getParameter("txtAddress");
                String phone = request.getParameter("txtPhone");
                String txtChkIn = request.getParameter("txtCheckInDate");
                String txtchkOut = request.getParameter("txtCheckOutDate");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date checkInDate = new java.sql.Date((format.parse(txtChkIn).getTime()));
                Date checkOutDate = new java.sql.Date((format.parse(txtchkOut).getTime()));
                if (checkOutDate.compareTo(checkInDate) == 0) {
                    request.setAttribute("DateError", "Check in date is in the same date with chech out date");
                    checkDate = false;
                } else if (checkOutDate.compareTo(checkInDate) < 0) {
                    request.setAttribute("DateError", "Check in date is after chech out date");
                    checkDate = false;
                }
                if (!discountCode.equals("")) {
                    CodeDTO code = codeDao.getCode(discountCode);
                    Date currentDate = new Date(System.currentTimeMillis());
                    if (code == null) {
                        request.setAttribute("CodeError", "Code is not existed");
                        check = false;
                    } else if (currentDate.compareTo(code.getExpirationDate()) > 0) {
                        request.setAttribute("CodeError", "Code is expirated");
                        check = false;
                    } else if (code.getStatusId() == 4) {
                        request.setAttribute("CodeError", "Code is used");
                        check = false;
                    } else {
                        percent = code.getDiscountPercent();
                    }
                }
                if (checkDate) {
                    if (cart != null) {
                        Map<HotelRoomTypeDTO, Integer> list = cart.getItems();
                        for (HotelRoomTypeDTO hotelRoomDto : list.keySet()) {
                            if (!hotelRoomDao.checkQuantity(hotelRoomDto.getHotelRoomId(), checkInDate, checkOutDate, list.get(hotelRoomDto))) {
                                error.add("Not enough " + mapRoomType.get(hotelRoomDto.getHotelRoomId()) + " room in " + mapHotel.get(hotelRoomDto.getHotelRoomId()) + " hotel " + "<br>");
                                check = false;
                            }
                        }
                    }
                }
                if (check && checkDate) {
                    if (!discountCode.equals("")) {
                        request.setAttribute("CodeId", discountCode);
                    }
                    int gap = (int) ChronoUnit.DAYS.between(checkInDate.toLocalDate(), checkOutDate.toLocalDate());
                    int total = (int) session.getAttribute("TotalBill");
                    if (percent != 0) {
                        total = total * gap * percent / 100;
                    } else {
                        total *= gap;
                    }
                    request.setAttribute("NumOfDate", gap);
                    session.setAttribute("TotalBill", total);
                    request.setAttribute("CustomerName", name);
                    request.setAttribute("CustomerAddress", address);
                    request.setAttribute("CustomerPhone", phone);
                    request.setAttribute("CheckInDate", checkInDate);
                    request.setAttribute("CheckOutDate", checkOutDate);

                    url = DETAIL;
                } else {
                    request.setAttribute("QuantityError", error);
                }
            } catch (SQLException | NamingException | ClassNotFoundException | ParseException ex) {
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
