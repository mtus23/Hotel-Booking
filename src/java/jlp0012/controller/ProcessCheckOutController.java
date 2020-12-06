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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import jlp0012.dao.HotelRoomTypeDAO;
import jlp0012.dao.OrderDAO;
import jlp0012.dao.OrderDetailDAO;
import jlp0012.dto.CartDTO;
import jlp0012.dto.HotelRoomTypeDTO;
import jlp0012.dto.OrderDTO;
import jlp0012.dto.OrderDetailDTO;
import jlp0012.dto.UserDTO;
import jlp0012.ulti.EmailSender;
import jlp0012.ulti.StringEncrypter;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class ProcessCheckOutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger LOG = Logger.getLogger(ProcessCheckOutController.class);
    private final String SUCCESS = "search.jsp";
    private final String ERROR = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            boolean lastCheck = true;
            HttpSession session = request.getSession();
            HotelRoomTypeDAO hotelRoomDao = new HotelRoomTypeDAO();
            List<String> error = new ArrayList<>();
            OrderDAO orderDao = new OrderDAO();
            OrderDetailDAO orderDetailDao = new OrderDetailDAO();
            StringEncrypter encrypter = new StringEncrypter();
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
                if (cart != null) {
                    Map<HotelRoomTypeDTO, Integer> list = cart.getItems();
                    for (HotelRoomTypeDTO hotelRoomDto : list.keySet()) {
                        if (!hotelRoomDao.checkQuantity(hotelRoomDto.getHotelRoomId(), checkInDate, checkOutDate, list.get(hotelRoomDto))) {
                            error.add("Not enough " + mapRoomType.get(hotelRoomDto.getRoomTypeId()) + " room in " + mapHotel.get(hotelRoomDto.getHotelRoomId()) + " hotel " + "<br>");
                            lastCheck = false;
                        }
                    }
                }
                if (cart == null || lastCheck == false) {
                    request.setAttribute("QuantityError", error);
                } else {
                    OrderDTO order = null;
                    int totalBill = (int) session.getAttribute("TotalBill");
                    UserDTO user = (UserDTO) session.getAttribute("User");
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                    if (discountCode.equals("")) {
                        order = new OrderDTO(user.getEmail(), totalBill, currentTime, checkInDate, checkOutDate, name, address, phone);
                    } else {
                        order = new OrderDTO(user.getEmail(), totalBill, currentTime, checkInDate, checkOutDate, discountCode, name, address, phone);
                    }
                    orderDao.addOrder(order);
                    String orderId = orderDao.getLastOrderId();
                    Map<HotelRoomTypeDTO, Integer> list = cart.getItems();
                    for (HotelRoomTypeDTO hotelRoom : list.keySet()) {
                        int quantity = list.get(hotelRoom);
                        OrderDetailDTO orderDetailDto = new OrderDetailDTO(orderId, hotelRoom.getHotelRoomId(), quantity, hotelRoom.getPrice());
                        orderDetailDao.addOrderDetail(orderDetailDto);

                        HotelRoomTypeDTO dto = hotelRoomDao.getHotelRoomtype(hotelRoom.getHotelRoomId());
                        int currAvailable = dto.getAvailable() - quantity;
                        if (currAvailable < 0) {
                            currAvailable = 0;
                        }
                        dto.setAvailable(currAvailable);
                        hotelRoomDao.updateHotelRoomAvailable(dto);
                    }
                    cart.clearCart();
                    session.removeAttribute("Cart");

                    String link = "http://localhost:8084/J3LP0012/confirmBooking?order=" + orderId + "&user=" + encrypter.getEncryptedString(user.getEmail());
                    EmailSender sender = new EmailSender(user.getEmail());
                    sender.sendConfirmBooking(link);
                    url = SUCCESS;
                }
            } catch (ParseException | SQLException | NamingException | ClassNotFoundException ex) {
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
