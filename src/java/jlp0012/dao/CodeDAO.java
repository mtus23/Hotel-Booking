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
import javax.naming.NamingException;
import jlp0012.dto.CodeDTO;
import jlp0012.ulti.DBUtil;

/**
 *
 * @author DELL
 */
public class CodeDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public CodeDAO() {
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

    public CodeDTO getCode(String codeId) throws SQLException, ClassNotFoundException, NamingException {
        CodeDTO result = null;
        try {
            String sql = "SELECT discountPercent, expirationDate FROM tblCode WHERE codeId = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, codeId);
            rs = stm.executeQuery();
            if (rs.next()) {
                int percent = rs.getInt("discountPercent");
                Date expirationDate = rs.getDate("expirationDate");
                result = new CodeDTO(codeId, percent, expirationDate);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean createCode(CodeDTO codeDto) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        try {
            String sql = "INSERT INTO tblCode(CodeId, discountPercent, dateCreate, codeName, expirationDate , statusId) VALUES (?,?,?,?,?,3)";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, codeDto.getCodeId());
            stm.setInt(2, codeDto.getDiscountPercent());
            stm.setDate(3, codeDto.getDateCreate());
            stm.setString(4, codeDto.getCodeName());
            stm.setDate(5, codeDto.getExpirationDate());
            check = stm.executeUpdate() > 0;   
        } finally {
            closeConnection();
        }
        return check;
    }
}
