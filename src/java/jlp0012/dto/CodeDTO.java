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
public class CodeDTO implements Serializable{
    private String codeId;
    private int discountPercent;
    private Date dateCreate;
    private Date expirationDate;
    private String codeName;
    private int statusId;

    public CodeDTO() {
    }

    public CodeDTO(String codeId, int discountPercent, Date dateCreate, Date expirationDate, String codeName, int statusId) {
        this.codeId = codeId;
        this.discountPercent = discountPercent;
        this.dateCreate = dateCreate;
        this.expirationDate = expirationDate;
        this.codeName = codeName;
        this.statusId = statusId;
    }

    public CodeDTO(String codeId, int discountPercent, Date expirationDate) {
        this.codeId = codeId;
        this.discountPercent = discountPercent;
        this.expirationDate = expirationDate;
    }

    public CodeDTO(String codeId, int discountPercent, Date dateCreate, Date expirationDate, String codeName) {
        this.codeId = codeId;
        this.discountPercent = discountPercent;
        this.dateCreate = dateCreate;
        this.expirationDate = expirationDate;
        this.codeName = codeName;
    }

    
    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    

    
    
}
