/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.dto;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class OrderDetailDTO implements Serializable{
    private int orderDetailId;
    private String orderId;
    private int hotelRoomId;
    private int quantity;
    private int price;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderDetailId, String orderId, int hotelRoomId, int quantity, int price) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.hotelRoomId = hotelRoomId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetailDTO(String orderId, int hotelRoomId, int quantity, int price) {
        this.orderId = orderId;
        this.hotelRoomId = hotelRoomId;
        this.quantity = quantity;
        this.price = price;
    }

    

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(int hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
