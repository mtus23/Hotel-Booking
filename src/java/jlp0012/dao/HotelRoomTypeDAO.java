/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import jlp0012.dto.HotelRoomTypeDTO;
import jlp0012.ulti.DBUtil;

/**
 *
 * @author DELL
 */
public class HotelRoomTypeDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public HotelRoomTypeDAO() {
    }

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public List<HotelRoomTypeDTO> getHotelListRoomtype(int hotelId) throws SQLException, ClassNotFoundException, NamingException {
        List<HotelRoomTypeDTO> result = null;
        try {
            String sql = "SELECT quantity, available, hotelRoomId, price, tblHotelRoomType.roomTypeId "
                    + "FROM tblHotelRoomType "
                    + "JOIN tblRoomType ON tblRoomType.roomTypeId = tblHotelRoomType.roomTypeId "
                    + "WHERE hotelId = ? AND tblHotelRoomType.statusId = 1 AND tblRoomType.statusId = 1";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, hotelId);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                int quantity = rs.getInt("quantity");
                int available = rs.getInt("available");
                int hotelRoomId = rs.getInt("hotelRoomId");
                int roomTypeId = rs.getInt("roomTypeId");
                int price = rs.getInt("price");
                HotelRoomTypeDTO dto = new HotelRoomTypeDTO(hotelRoomId, hotelId, price, quantity, available, roomTypeId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public HotelRoomTypeDTO getHotelRoomtype(int hotelRoomId) throws SQLException, ClassNotFoundException, NamingException {
        HotelRoomTypeDTO result = null;
        try {
            String sql = "SELECT quantity, available, price, hotelId, "
                    + "roomTypeId FROM tblHotelRoomType WHERE hotelRoomId = ?"
                    + " AND statusId = 1 ";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, hotelRoomId);
            rs = stm.executeQuery();
            if (rs.next()) {
                int hotelId = rs.getInt("hotelId");
                int quantity = rs.getInt("quantity");
                int available = rs.getInt("available");
                int roomTypeId = rs.getInt("roomTypeId");
                int price = rs.getInt("price");
                result = new HotelRoomTypeDTO(hotelRoomId, hotelId, price, quantity, available, roomTypeId);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<HotelRoomTypeDTO> searchHotelRoomType(String hotelName, int areaId, int amount, Date checkIn, Date checkOut) throws SQLException, ClassNotFoundException, NamingException {
        List<HotelRoomTypeDTO> result = null;
        try {
            String sql = "SELECT tblHotelRoomType.hotelId, quantity, available, hotelRoomId, price, tblHotelRoomType.roomTypeId, tblHotelRoomType.image "
                    + "FROM tblHotelRoomType "
                    + "JOIN tblRoomType ON tblRoomType.roomTypeId = tblHotelRoomType.roomTypeId "
                    + "JOIN tblHotel ON tblHotel.hotelId = tblHotelRoomType.hotelId "
                    + "WHERE areaId = ? AND tblHotelRoomType.statusId = 1 AND tblRoomType.statusId = 1 AND tblHotel.statusId = 1 AND hotelName LIKE ? AND (available >= ? OR( "
                    + " hotelRoomId NOT IN ( "
                    + "SELECT tblHotelRoomType.hotelRoomId "
                    + "FROM tblOrderDetail "
                    + "JOIN tblOrder on tblOrderDetail.orderId = tblOrder.orderId "
                    + "JOIN tblHotelRoomType ON  tblHotelRoomType.hotelRoomId = tblOrderDetail.hotelRoomId "
                    + "WHERE ( fromDate >= ? AND toDate <= ? ) OR (fromDate >= ? AND fromDate <= ? ) "
                    + "OR (toDate >= ? AND toDate <= ? ) AND ( tblOrder.statusId = 5 OR tblOrder.statusId = 7)"
                    + "GROUP BY tblHotelRoomType.hotelRoomId, tblHotelRoomType.quantity "
                    + "HAVING tblHotelRoomType.quantity - COALESCE(SUM(DISTINCT tblOrderDetail.quantity),0) < ? "
                    + ")))";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, areaId);
            stm.setString(2, "%" + hotelName + "%");
            stm.setInt(3, amount);
            stm.setDate(4, checkIn);
            stm.setDate(5, checkOut);
            stm.setDate(6, checkIn);
            stm.setDate(7, checkIn);
            stm.setDate(8, checkOut);
            stm.setDate(9, checkOut);
            stm.setInt(10, amount);
            rs = stm.executeQuery();

            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                int hotelId = rs.getInt("hotelId");
                int quantity = rs.getInt("quantity");
                int available = rs.getInt("available");
                int hotelRoomId = rs.getInt("hotelRoomId");
                int roomTypeId = rs.getInt("roomTypeId");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                HotelRoomTypeDTO dto = new HotelRoomTypeDTO(hotelRoomId, hotelId, price, quantity, available, roomTypeId, image);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<HotelRoomTypeDTO> searchHotelRoomType(String hotelName, int amount, Date checkIn, Date checkOut) throws SQLException, ClassNotFoundException, NamingException {
        List<HotelRoomTypeDTO> result = null;
        try {
            String sql = "SELECT tblHotelRoomType.hotelId, quantity, available, hotelRoomId, price, tblHotelRoomType.roomTypeId, tblHotelRoomType.image "
                    + "FROM tblHotelRoomType "
                    + "JOIN tblRoomType ON tblRoomType.roomTypeId = tblHotelRoomType.roomTypeId "
                    + "JOIN tblHotel ON tblHotel.hotelId = tblHotelRoomType.hotelId "
                    + "WHERE tblHotelRoomType.statusId = 1 AND tblRoomType.statusId = 1 AND tblHotel.statusId = 1 AND hotelName LIKE ? AND (available >= ? "
                    + "OR hotelRoomId NOT IN ( "
                    + "SELECT tblHotelRoomType.hotelRoomId "
                    + "FROM tblOrderDetail  "
                    + "JOIN tblOrder on tblOrderDetail.orderId = tblOrder.orderId  "
                    + "JOIN tblHotelRoomType ON  tblHotelRoomType.hotelRoomId = tblOrderDetail.hotelRoomId "
                    + "WHERE (fromDate >= ? AND toDate <= ? ) OR (fromDate >= ? AND fromDate <= ? ) "
                    + "OR (toDate >= ? AND toDate <= ? ) AND ( tblOrder.statusId = 5 OR tblOrder.statusId = 7)"
                    + "GROUP BY tblHotelRoomType.hotelRoomId, tblHotelRoomType.quantity "
                    + "HAVING tblHotelRoomType.quantity - COALESCE(SUM(DISTINCT tblOrderDetail.quantity),0) < ?))";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + hotelName + "%");
            stm.setInt(2, amount);
            stm.setDate(3, checkIn);
            stm.setDate(4, checkOut);
            stm.setDate(5, checkIn);
            stm.setDate(6, checkIn);
            stm.setDate(7, checkOut);
            stm.setDate(8, checkOut);
            stm.setInt(9, amount);
            rs = stm.executeQuery();

            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                int hotelId = rs.getInt("hotelId");
                int quantity = rs.getInt("quantity");
                int available = rs.getInt("available");
                int hotelRoomId = rs.getInt("hotelRoomId");
                int roomTypeId = rs.getInt("roomTypeId");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                HotelRoomTypeDTO dto = new HotelRoomTypeDTO(hotelRoomId, hotelId, price, quantity, available, roomTypeId, image);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkQuantity(int hotelRoomId, Date fromDate, Date toDate, int quantity) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        try {
            String sql = "SELECT hotelRoomId "
                    + "FROM tblHotelRoomType  "
                    + "WHERE hotelRoomId = ? AND tblHotelRoomType.statusId = 1 AND( available >= ? OR hotelRoomId in ( "
                    + "SELECT tblHotelRoomType.hotelRoomId "
                    + "FROM tblOrderDetail "
                    + "JOIN tblOrder on tblOrderDetail.orderId = tblOrder.orderId "
                    + "JOIN tblHotelRoomType ON  tblHotelRoomType.hotelRoomId = tblOrderDetail.hotelRoomId "
                    + "WHERE (fromDate >= ? AND toDate <= ? ) OR (fromDate >= ? AND fromDate <= ? ) "
                    + "OR (toDate >= ? AND toDate <= ? ) "
                    + "AND (tblOrder.statusId = 5 OR tblOrder.statusId = 7)"
                    + "GROUP BY tblHotelRoomType.hotelRoomId, tblHotelRoomType.quantity "
                    + "having tblHotelRoomType.quantity - COALESCE(SUM(DISTINCT tblOrderDetail.quantity),0) >= ? )) ";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, hotelRoomId);
            stm.setInt(2, quantity);
            stm.setDate(3, fromDate);
            stm.setDate(4, toDate);
            stm.setDate(5, fromDate);
            stm.setDate(6, fromDate);
            stm.setDate(7, toDate);
            stm.setDate(8, toDate);
            stm.setInt(9, quantity);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateHotelRoomAvailable(HotelRoomTypeDTO dto) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        try {
            String sql = "UPDATE tblHotelRoomType SET available = ? "
                    + "WHERE hotelRoomId = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, dto.getAvailable());
            stm.setInt(2, dto.getHotelRoomId());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public HotelRoomTypeDTO getHotelRoomById(int hotelRoomId) throws SQLException, ClassNotFoundException, NamingException {
        HotelRoomTypeDTO result = null;
        try {
            String sql = "SELECT hotelId, roomTypeId, price FROM tblHotelRoomType WHERE hotelRoomId = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, hotelRoomId);
            rs = stm.executeQuery();
            if (rs.next()) {
                int hotelId = rs.getInt("hotelId");
                int roomTypeId = rs.getInt("roomTypeId");
                int price = rs.getInt("price");
                result = new HotelRoomTypeDTO(hotelRoomId, hotelId, price, roomTypeId);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}
