/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import jlp0012.dto.OrderDTO;
import jlp0012.dto.OrderDetailDTO;
import jlp0012.ulti.DBUtil;

/**
 *
 * @author DELL
 */
public class OrderDetailDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public OrderDetailDAO() {
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

    public boolean addOrderDetail(OrderDetailDTO dto) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        try {
            String sql = "INSERT INTO tblOrderDetail (orderId, hotelRoomId, price, quantity) "
                    + "VALUES (?,?,?,?)";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getOrderId());
            stm.setInt(2, dto.getHotelRoomId());
            stm.setInt(3, dto.getPrice());
            stm.setInt(4, dto.getQuantity());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<OrderDetailDTO> getListOrderDetail(OrderDTO dto) throws SQLException, ClassNotFoundException, NamingException {
        List<OrderDetailDTO> result = null;
        try {
            String sql = "SELECT hotelRoomId, price, quantity FROM tblOrderDetail WHERE orderId = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getOrderId());
            rs = stm.executeQuery();
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                int hotelRoomId = rs.getInt("hotelRoomId");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                OrderDetailDTO hotelRommDto = new OrderDetailDTO(dto.getOrderId(), hotelRoomId, quantity, price);
                result.add(hotelRommDto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
