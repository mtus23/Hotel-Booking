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
public class RateDTO implements Serializable{
    private int rateId;
    private String email;
    private int point;
    private int hotelRoomId;
    private String orderId;

    public RateDTO() {
    }

    public RateDTO(int rateId, String email, int point, int hotelRoomId, String orderId) {
        this.rateId = rateId;
        this.email = email;
        this.point = point;
        this.hotelRoomId = hotelRoomId;
        this.orderId = orderId;
    }

    public RateDTO(String email, int point, int hotelRoomId, String orderId) {
        this.email = email;
        this.point = point;
        this.hotelRoomId = hotelRoomId;
        this.orderId = orderId;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(int hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    
    
}
