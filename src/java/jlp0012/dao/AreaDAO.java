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
import jlp0012.dto.AreaDTO;
import jlp0012.ulti.DBUtil;

/**
 *
 * @author DELL
 */
public class AreaDAO implements Serializable{
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public AreaDAO() {
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
    
    public List<AreaDTO> getAllArea() throws SQLException, ClassNotFoundException, NamingException{
        List<AreaDTO> result = null;
        try{
            String sql ="SELECT areaId, areaName FROM tblArea";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                if(result==null){
                    result=new ArrayList<>();
                }
                int id = rs.getInt("areaId");
                String name = rs.getNString("areaName");
                AreaDTO dto = new AreaDTO(id, name);
                result.add(dto);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    
    public Map<Integer, String> getMapArea() throws SQLException, ClassNotFoundException, NamingException{
        Map<Integer, String> result = null;
        try{
            String sql ="SELECT areaId, areaName FROM tblArea";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                if(result==null){
                    result=new HashMap<>();
                }
                int id = rs.getInt("areaId");
                String name = rs.getNString("areaName");
                result.put(id, name);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
}
