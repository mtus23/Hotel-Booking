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
public class HotelDTO implements Serializable{
    private int hotelId;
    private String hotelName;
    private int areaId;
    private String image;
    private int statusId;
    private String description;

    public HotelDTO() {
    }

    public HotelDTO(int hotelId, String hotelName, int areaId, String image, int statusId, String description) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.areaId = areaId;
        this.image = image;
        this.statusId = statusId;
        this.description = description;
    }

    public HotelDTO(int hotelId, String hotelName, int areaId, String image, String description) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.areaId = areaId;
        this.image = image;
        this.description = description;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
