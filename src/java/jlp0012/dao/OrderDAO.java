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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.naming.NamingException;
import jlp0012.dto.OrderDTO;
import jlp0012.ulti.DBUtil;

/**
 *
 * @author DELL
 */
public class OrderDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public OrderDAO() {
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

    public boolean addOrder(OrderDTO dto) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        try {
            String sql = "INSERT INTO tblOrder(email, totalPrice, createDate, fromDate, toDate, codeId, name, address, phone, statusId) VALUES(?,?,?,?,?,?,?,?,?,7)";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getEmail());
            stm.setInt(2, dto.getTotalPrice());
            stm.setTimestamp(3, dto.getCreateDate());
            stm.setDate(4, dto.getFromDate());
            stm.setDate(5, dto.getToDate());
            stm.setString(6, dto.getCodeId());
            stm.setString(7, dto.getName());
            stm.setString(8, dto.getAddress());
            stm.setString(9, dto.getPhone());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public String getLastOrderId() throws SQLException, ClassNotFoundException, NamingException {
        String result = null;
        try {
            String sql = "SELECT TOP 1 orderId "
                    + "FROM tblOrder "
                    + "ORDER BY createDate DESC";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getString("orderId");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String getOrderEmail(String orderId) throws SQLException, ClassNotFoundException, NamingException {
        String result = null;
        try {
            String sql = "SELECT email FROM tblOrder WHERE statusId = 7 AND orderId = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setObject(1, UUID.fromString(orderId));
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getString("email");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<OrderDTO> getListOrder(String email) throws SQLException, ClassNotFoundException, NamingException {
        List<OrderDTO> result = null;
        try {
            String sql = "SELECT orderId, totalPrice, createDate, fromDate, toDate, codeId, "
                    + " statusId, name, address, phone FROM tblOrder WHERE email = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                String orderId = rs.getString("orderId");
                int total = rs.getInt("totalPrice");
                Timestamp creDate = rs.getTimestamp("createDate");
                Date fromDate = rs.getDate("fromDate");
                Date toDate = rs.getDate("toDate");
                String code = rs.getString("codeId");
                int statusId = rs.getInt("statusId");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                OrderDTO orderDto = new OrderDTO(orderId, email, total, creDate, fromDate, toDate, code, name, address, phone, statusId);
                result.add(orderDto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateStatus(String orderId, int statusId) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        try {
            String sql = "UPDATE tblOrder SET statusId = ? WHERE orderID = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, statusId);
            stm.setObject(2, UUID.fromString(orderId));
            check = stm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return check;
    }

    public OrderDTO getOrder(String orderId) throws SQLException, ClassNotFoundException, NamingException {
        OrderDTO result = null;
        try {
            String sql = "SELECT totalPrice, email ,createDate, fromDate, toDate, codeId, "
                    + " statusId, name, address, phone FROM tblOrder WHERE orderId = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setObject(1, UUID.fromString(orderId));
            rs = stm.executeQuery();
            while (rs.next()) {
                int total = rs.getInt("totalPrice");
                Timestamp creDate = rs.getTimestamp("createDate");
                Date fromDate = rs.getDate("fromDate");
                Date toDate = rs.getDate("toDate");
                String code = rs.getString("codeId");
                String email = rs.getString("email");
                int statusId = rs.getInt("statusId");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                result = new OrderDTO(orderId, email, total, creDate, fromDate, toDate, code, name, address, phone, statusId);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<OrderDTO> searchOrder(String id, Date bookingDate, String email) throws SQLException, ClassNotFoundException, NamingException {
        List<OrderDTO> result = null;
        try {
            String sql = "SELECT DISTINCT orderId, totalPrice, fromDate, toDate, codeId, createDate, "
                    + " statusId, name, address, phone FROM tblOrder WHERE (orderId LIKE ? OR CAST(createDate AS DATE) = ? ) AND email = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + id + "%");
            stm.setDate(2, bookingDate);
            stm.setString(3, email);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                String orderId = rs.getString("orderId");
                int total = rs.getInt("totalPrice");
                Timestamp creDate = rs.getTimestamp("createDate");
                Date fromDate = rs.getDate("fromDate");
                Date toDate = rs.getDate("toDate");
                String code = rs.getString("codeId");
                int statusId = rs.getInt("statusId");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                OrderDTO orderDto = new OrderDTO(orderId, email, total, creDate, fromDate, toDate, code, name, address, phone, statusId);
                result.add(orderDto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<OrderDTO> searchOrder(Date bookingDate, String email) throws SQLException, ClassNotFoundException, NamingException {
        List<OrderDTO> result = null;
        try {
            String sql = "SELECT DISTINCT orderId, totalPrice, fromDate, toDate, codeId, createDate, "
                    + " statusId, name, address, phone FROM tblOrder WHERE  CAST(createDate AS DATE) = ? AND email = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setDate(1, bookingDate);
            stm.setString(2, email);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                String orderId = rs.getString("orderId");
                int total = rs.getInt("totalPrice");
                Timestamp creDate = rs.getTimestamp("createDate");
                Date fromDate = rs.getDate("fromDate");
                Date toDate = rs.getDate("toDate");
                String code = rs.getString("codeId");
                int statusId = rs.getInt("statusId");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                OrderDTO orderDto = new OrderDTO(orderId, email, total, creDate, fromDate, toDate, code, name, address, phone, statusId);
                result.add(orderDto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<OrderDTO> searchOrder(String id, String email) throws SQLException, ClassNotFoundException, NamingException {
        List<OrderDTO> result = null;
        try {
            String sql = "SELECT DISTINCT orderId, totalPrice, fromDate, toDate, codeId, createDate, "
                    + " statusId, name, address, phone FROM tblOrder WHERE orderId LIKE ?  AND email = ?";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + id + "%");
            stm.setString(2, email);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                String orderId = rs.getString("orderId");
                int total = rs.getInt("totalPrice");
                Timestamp creDate = rs.getTimestamp("createDate");
                Date fromDate = rs.getDate("fromDate");
                Date toDate = rs.getDate("toDate");
                String code = rs.getString("codeId");
                int statusId = rs.getInt("statusId");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                OrderDTO orderDto = new OrderDTO(orderId, email, total, creDate, fromDate, toDate, code, name, address, phone, statusId);
                result.add(orderDto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateOrder(Date today) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        try {
            String sql = "UPDATE tblOrder SET statusId = 10 WHERE toDate = ? AND (statusId = 5 OR statusId = 7)";
            con = DBUtil.getConnection();
            stm = con.prepareStatement(sql);
            stm.setDate(1, today);
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

}
