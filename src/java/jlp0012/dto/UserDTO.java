/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlp0012.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class UserDTO implements Serializable{
    private String email;
    private String name;
    private String phone;
    private String address;
    private String password;
    private Date createdate;
    private int roleId;
    private boolean status;
    
    public UserDTO() {
    }

    public UserDTO(String email, String name, String phone, String address, String password, Date createdate, int roleId, boolean status) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.createdate = createdate;
        this.roleId = roleId;
        this.status = status;
    }

    public UserDTO(String email) {
        this.email = email;
    }

    
    public UserDTO(String email, String name, String phone, String address, int roleId) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
    }

    public UserDTO(String email, String name, String phone, String address, String password, Date createdate) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.createdate = createdate;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}