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
import jlp0012.dao.HotelDAO;
import jlp0012.dao.HotelRoomTypeDAO;
import jlp0012.dto.HotelDTO;
import jlp0012.dto.HotelRoomTypeDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class LoadHotelDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static Logger LOG = Logger.getLogger(LoadHotelDetailController.class);
    private final String ERROR = "search.jsp";
    private final String DETAIL_PAGE = "detail.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            HotelDAO hotelDao = new HotelDAO();
            HotelRoomTypeDAO hotelRoomTypeDao = new HotelRoomTypeDAO();
            try{
                String hotelId = request.getParameter("txtHotelId");
                int id = Integer.parseInt(hotelId);
                HotelDTO hotelDto = hotelDao.getHotel(id);
                if(hotelDto!=null){
                    List<HotelRoomTypeDTO> listHotelRoomType = hotelRoomTypeDao.getHotelListRoomtype(id);
                    request.setAttribute("hotelDto", hotelDto);
                    request.setAttribute("listHotelRoomtype", listHotelRoomType);
                    url = DETAIL_PAGE;
                }else{
                    request.setAttribute("SearchResult", "Hotel not found");
                }
            }catch(SQLException | NamingException | ClassNotFoundException ex){
              LOG.error(ex.toString());
            }
            finally{
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
