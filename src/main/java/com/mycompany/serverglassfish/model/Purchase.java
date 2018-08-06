/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="purchase")
public class Purchase implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @SerializedName("mId")
    private int id;
    
    @Column(name="url", nullable=false)
    @SerializedName("mName")
    private String url;
    
    @Column(name="description")
    @SerializedName("mDescription")
    private String description;
    
    @Column(name="date", nullable=false)
    @SerializedName("mDate")
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name="department_id", nullable=false)
    @SerializedName("mDepartment")
    private Department departmentPurchase;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Department getDepartmentPurchase() {
        return departmentPurchase;
    }

    public void setDepartmentPurchase(Department departmentPurchase) {
        this.departmentPurchase = departmentPurchase;
    }
    
}
