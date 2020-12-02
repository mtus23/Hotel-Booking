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
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.naming.NamingException;
import jlp0012.dto.HotelDTO;
import jlp0012.ulti.DBUtil;

/**
 *
 * @author DELL
 */
public class HotelDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public HotelDAO() {
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

    public List<HotelDTO> getAllHasAvailableRoom() throws SQLException, ClassNotFoundException, NamingException {
        List<HotelDTO> result = null;
        try {
            String sql = "SELECT DISTINCT tblHotel.hotelId , hotelName, areaId , tblHotel.image, description "
                    + "FROM tblHotel join tblHotelRoomType ON tblHotel.hotelId = tblHotelRoomType.hotelId "
                    + "WHERE available > 0 AND tblHotel.statusId = 1 AND tblHotelRoomType.statusId = 1;";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int hotelId = rs.getInt("hotelId");
                String hotelName = rs.getNString("hotelName");
                int areaId = rs.getInt("areaId");
                String image = rs.getString("image");
                String des = rs.getNString("description");
                HotelDTO dto = new HotelDTO(hotelId, hotelName, areaId, image, des);
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
     public Map<Integer,String> getMapHotel() throws SQLException, ClassNotFoundException, NamingException{
        Map<Integer, String> result = null;
        try{
            String sql ="SELECT hotelId, hotelName FROM tblHotel WHERE statusId = 1";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                if(result==null){
                    result=new HashMap<>();
                }
                int id = rs.getInt("hotelId");
                String name = rs.getNString("hotelName");
                result.put(id, name);
            }
        }finally{
            closeConnection();
        }
        return result;
        
     }
    public HotelDTO getHotel(int hotelId) throws SQLException, ClassNotFoundException, NamingException {
        HotelDTO result = null;
        try {
            String sql = "SELECT hotelName, areaId, image, description FROM tblHotel "
                    + "WHERE statusId = 1 AND hotelId = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, hotelId);
            rs = stm.executeQuery();
            if (rs.next()) {
                String hotelName = rs.getNString("hotelName");
                int areaId = rs.getInt("areaId");
                String image = rs.getString("image");
                String des = rs.getNString("description");
                result = new HotelDTO(hotelId, hotelName, areaId, image, des);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
