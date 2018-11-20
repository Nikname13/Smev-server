/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="area")
public class Area extends BaseModel implements Serializable {
     
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @OneToMany(mappedBy="area")
    private List<Department> departmentsListArea=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getDepartmentsListArea() {
        return departmentsListArea;
    }

    public void setDepartmentsListArea(List<Department> departmentsListArea) {
        this.departmentsListArea = departmentsListArea;
    }
    
    
}
