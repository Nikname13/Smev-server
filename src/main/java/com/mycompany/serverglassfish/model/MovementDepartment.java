/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="movement_department")
public class MovementDepartment extends BaseModel implements Serializable {
  
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @Column(name="department_number", nullable=false)
    @SerializedName("mNumber")
    private String departmentNumber;
    
    @Column(name="department_id", nullable=false)
    @SerializedName("mDepartmentId")
    private int departmentId;
    
    @ManyToOne
    @JoinColumn(name="movement_id", nullable=false)
    @SerializedName("mDepartmentMovement")
    private MovementLog departmentMovement;

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
