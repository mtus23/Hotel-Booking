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
public class RoomTypeDTO implements Serializable{
    private int roomTypeId;
    private String roomType;
    private int statusId;

    public RoomTypeDTO() {
    }

    public RoomTypeDTO(int roomTypeId, String roomType, int statusId) {
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
        this.statusId = statusId;
    }

    public RoomTypeDTO(int roomTypeId, String roomType) {
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
    }
    

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
    
}
