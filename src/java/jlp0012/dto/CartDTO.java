/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class CartDTO implements Serializable {

    private Map<HotelRoomTypeDTO, Integer> items;

    public Map<HotelRoomTypeDTO, Integer> getItems() {
        return items;
    }

    public int getTotalPrice() {
        int total = 0;
        if (this.items != null) {
            for (HotelRoomTypeDTO dto : items.keySet()) {
                total += (dto.getPrice() * this.items.get(dto));
            }
        }
        return total;
    }

    private HotelRoomTypeDTO searchForHotelRoomTypeInfo(int id) {
        for (HotelRoomTypeDTO dto : items.keySet()) {
            if (dto.getHotelRoomId() == id) {
                return dto;
            }
        }
        return null;
    }

    public void addHotelRoomToCart(HotelRoomTypeDTO hotelRoomType, int quantity) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        HotelRoomTypeDTO dto = searchForHotelRoomTypeInfo(hotelRoomType.getHotelRoomId());
        if (dto == null) {
            this.items.put(hotelRoomType, quantity);
        } else {
            int total = this.items.get(dto);
            this.items.put(dto, total + quantity);
        }
    }

    public void updateHotelRoomFromCart(int hotelRoomId, int quantity) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        HotelRoomTypeDTO dto = searchForHotelRoomTypeInfo(hotelRoomId);
        if (dto != null) {
            this.items.put(dto, quantity);
        }
    }

    public void removeHotelRoomFromCart(int hotelRoomId) {
        if (this.items == null) {
            return;
        }
        HotelRoomTypeDTO dto = searchForHotelRoomTypeInfo(hotelRoomId);
        if (dto != null) {
            this.items.remove(dto);

            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

    public void clearCart() {
        if (this.items != null) {
            items = null;
        }
    }
}
