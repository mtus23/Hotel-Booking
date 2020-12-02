/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author DELL
 */
public class OrderDTO implements Serializable{
    private String orderId;
    private String email;
    private int totalPrice;
    private Timestamp createDate;
    private Date fromDate;
    private Date toDate;
    private String codeId;
    private String name;
    private String address;
    private String phone;
    private int statusId;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String email, int totalPrice, Timestamp createDate, Date fromDate, Date toDate, String codeId, String name, String address, String phone, int statusId) {
        this.orderId = orderId;
        this.email = email;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.codeId = codeId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.statusId = statusId;
    }

    public OrderDTO(String email, int totalPrice, Timestamp createDate, Date fromDate, Date toDate, String codeId, String name, String address, String phone) {
        this.email = email;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.codeId = codeId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public OrderDTO(String email, int totalPrice, Timestamp createDate, Date fromDate, Date toDate, String name, String address, String phone) {
        this.email = email;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
