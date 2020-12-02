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
import java.util.UUID;
import javax.naming.NamingException;
import jlp0012.dto.RateDTO;
import jlp0012.ulti.DBUtil;

/**
 *
 * @author DELL
 */
public class RateDAO implements Serializable{
     private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public RateDAO() {
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
    
    public boolean insertRate(RateDTO dto) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        try {
            String sql = "INSERT INTO tblRate(email,hotelRoomId,point,orderId)"
                    + " VALUES(?,?,?,?)";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getEmail());
            stm.setInt(2, dto.getHotelRoomId());
            stm.setInt(3, dto.getPoint());
            stm.setString(4, dto.getOrderId());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateRate(RateDTO dto) throws SQLException, ClassNotFoundException, NamingException{
        boolean check = false;
        try {
            String sql = "UPDATE tblRate SET point = ? "
                    + " WHERE rateId = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, dto.getPoint());
            stm.setInt(2, dto.getRateId());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public RateDTO getRate(String email,String orderId, int HotelRoomId) throws SQLException, NamingException, ClassNotFoundException{
        RateDTO result = null;
        try {
            String sql = "SELECT rateId, point FROM tblRate"
                    + " WHERE email = ? AND HotelRoomId = ? AND orderId = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setInt(2, HotelRoomId);
            stm.setObject(3, UUID.fromString(orderId));
            rs = stm.executeQuery();
            if (rs.next()){
                int rateId = rs.getInt("RateId");
                int point = rs.getInt("point");
                result = new RateDTO(rateId, email, point, HotelRoomId, orderId);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
