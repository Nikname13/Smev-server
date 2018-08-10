/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
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
@Table(name="movement_dapartment")
public class MovementDepartment {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @SerializedName("mId")
    private int id;
    
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @Column(name="department_number", nullable=false)
    @SerializedName("mDepartmentNumber")
    private String departmentNumber;
    
    @Column(name="department_id", nullable=false)
    @SerializedName("mDepartmentId")
    private int departmentId;
    
    @ManyToOne
    @JoinColumn(name="movement_id")
    @SerializedName("mDepartmentMovement")
    private MovementLog departmentMovement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public MovementLog getDepartmentMovement() {
        return departmentMovement;
    }

    public void setDepartmentMovement(MovementLog departmentMovement) {
        this.departmentMovement = departmentMovement;
    }
    
    
}
