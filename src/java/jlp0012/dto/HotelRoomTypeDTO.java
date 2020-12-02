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
public class HotelRoomTypeDTO implements Serializable{
    private int hotelRoomId;
    private int hotelId;
    private int price;
    private int quantity;
    private int available;
    private boolean status;
    private int roomTypeId;
    private String image;

    public HotelRoomTypeDTO() {
    }

    public HotelRoomTypeDTO(int hotelRoomId, int hotelId, int price, int roomTypeId) {
        this.hotelRoomId = hotelRoomId;
        this.hotelId = hotelId;
        this.price = price;
        this.roomTypeId = roomTypeId;
    }

    
    public HotelRoomTypeDTO(int hotelRoomId, int hotelId, int price, int quantity, int available, boolean status, int roomTypeId) {
        this.hotelRoomId = hotelRoomId;
        this.hotelId = hotelId;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
        this.status = status;
        this.roomTypeId = roomTypeId;
    }

    public HotelRoomTypeDTO(int hotelRoomId, int hotelId, int price, int quantity, int available, int roomTypeId) {
        this.hotelRoomId = hotelRoomId;
        this.hotelId = hotelId;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
        this.roomTypeId = roomTypeId;
    }

    public HotelRoomTypeDTO(int hotelRoomId, int hotelId, int price, int quantity, int available, int roomTypeId, String image) {
        this.hotelRoomId = hotelRoomId;
        this.hotelId = hotelId;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
        this.roomTypeId = roomTypeId;
        this.image = image;
    }

    

    public int getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(int hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
