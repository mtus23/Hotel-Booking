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
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import jlp0012.dto.RoomTypeDTO;
import jlp0012.ulti.DBUtil;

/**
 *
 * @author DELL
 */
public class RoomTypeDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public RoomTypeDAO() {
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

    public List<RoomTypeDTO> getAllRoomType() throws SQLException, ClassNotFoundException, NamingException {
        List<RoomTypeDTO> result = null;
        try {
            String sql = "SELECT roomTypeId, roomTypeName FROM tblRoomType WHERE statusId = 1";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                int id = rs.getInt("roomTypeId");
                String name = rs.getNString("roomTypeName");
                RoomTypeDTO dto = new RoomTypeDTO(id, name);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public Map<Integer, String> getMapRoomType() throws SQLException, ClassNotFoundException, NamingException {
        Map<Integer, String> result = null;
        try {
            String sql = "SELECT roomTypeId, roomTypeName FROM tblRoomType WHERE statusId = 1";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (result == null) {
                    result = new HashMap<>();
                }
                int id = rs.getInt("roomTypeId");
                String name = rs.getNString("roomTypeName");
                result.put(id, name);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
