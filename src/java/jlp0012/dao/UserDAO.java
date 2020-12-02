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
import jlp0012.dto.UserDTO;
import jlp0012.ulti.DBUtil;
import jlp0012.ulti.StringEncrypter;

/**
 *
 * @author DELL
 */
public class UserDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;
    StringEncrypter pe = new StringEncrypter();

    public UserDAO() {
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

    public UserDTO checkLogin(String email, String password) throws SQLException, ClassNotFoundException, NamingException {
        UserDTO dto = null;
        try {
            String sql = "SELECT name, phone, address, roleId FROM tblUser WHERE email = ? AND password = ? AND statusId = 5";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, pe.getEncryptedString(password));
            rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                int roleId = rs.getInt("roleId");
                String address = rs.getString("address");
                dto = new UserDTO(email, name, phone, address, roleId);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public String getUserName(String email) throws SQLException, ClassNotFoundException, NamingException {
        String result = null;
        try {
            String sql = "SELECT name FROM tblUser WHERE email = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getString("name");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean signup(UserDTO dto) throws SQLException, ClassNotFoundException, NamingException {
        boolean result = false;
        try {
            String sql = "INSERT INTO tblUser(email, phone, name, address, createDate, password, statusId, roleId) VALUES(?,?,?,?,?,?,5,2)";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getEmail());
            stm.setString(2, dto.getPhone());
            stm.setString(3, dto.getName());
            stm.setString(4, dto.getAddress());
            stm.setDate(5, dto.getCreatedate());
            stm.setString(6, pe.getEncryptedString(dto.getPassword()));
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkEmail(String email) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        try {
            String sql = "SELECT name FROM tblUser WHERE email = ? AND statusId = 5";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean changePassword(UserDTO dto) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        try {
            String sql = "UPDATE tblUser SET password = ? WHERE email = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getPassword());
            stm.setString(2, dto.getEmail());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<UserDTO> getAll() throws SQLException, ClassNotFoundException, NamingException {
        List<UserDTO> result = null;
        try {
            String sql = "SELECT email FROM tblUser WHERE statusId = 5";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                String email = rs.getString("email");
                UserDTO dto = new UserDTO(email);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
