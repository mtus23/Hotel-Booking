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
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jlp0012.dao.HotelRoomTypeDAO;
import jlp0012.dto.HotelRoomTypeDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class SearchHotelController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static Logger LOG = Logger.getLogger(SearchHotelController.class);
    private final String SEARCH_PAGE = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = SEARCH_PAGE;
            boolean check = true;
            HotelRoomTypeDAO hotelRoomDao = new HotelRoomTypeDAO();
            try {
                String txtChkIn = request.getParameter("txtCheckInDate");
                String txtchkOut = request.getParameter("txtCheckOutDate");
                String txtArea = request.getParameter("cboArea");
                String txtAmount = request.getParameter("txtAmount");
                String txtHotelName = request.getParameter("txtHotelNameSearch");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date checkInDate = new java.sql.Date((format.parse(txtChkIn).getTime()));
                Date checkOutDate = new java.sql.Date((format.parse(txtchkOut).getTime()));
                List<HotelRoomTypeDTO> result;
                if (checkOutDate.compareTo(checkInDate) == 0) {
                    request.setAttribute("DateError", "Check in date is in the same date with chech out date");
                    check = false;
                } else if (checkOutDate.compareTo(checkInDate) < 0) {
                    request.setAttribute("DateError", "Check in date is after chech out date");
                    check = false;
                }
                int amount = Integer.parseInt(txtAmount);
                if (txtArea.equals("------All------")) {
                    result = hotelRoomDao.searchHotelRoomType(txtHotelName, amount, checkInDate, checkOutDate);
                } else {
                    int areaId = Integer.parseInt(txtArea);
                    result = hotelRoomDao.searchHotelRoomType(txtHotelName, areaId, amount, checkInDate, checkOutDate);
                }
                if (check) {
                    if (result != null) {
                        request.setAttribute("SearchResult", result);
                    }
                    else{
                        request.setAttribute("SearchResultEmpty", "Hotel room type not found");
                    }
                }
                url = SEARCH_PAGE;
            } catch (ParseException | SQLException | NamingException | ClassNotFoundException ex) {
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
