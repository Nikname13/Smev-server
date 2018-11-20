/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="movement_log")
public class MovementLog extends BaseModel implements Serializable {
 
    @Column(name="base", nullable=false)
    @SerializedName("mName")
    private String base;
    
    @Column(name="date", nullable=false)
    @SerializedName("mDate")
    private LocalDate date;
    
    @OneToMany(mappedBy="departmentMovement", cascade=javax.persistence.CascadeType.ALL)
    @SerializedName("mDepartmentList")
    private List<MovementDepartment> departmentsList=new ArrayList<>();
    
    @OneToMany(mappedBy="equipmentMovement", cascade=javax.persistence.CascadeType.ALL)
    @SerializedName("mEntityList")
    private List<MovementEquipment> equipmentsList=new ArrayList<>();
    
    @OneToMany(mappedBy="workerMovement",cascade=javax.persistence.CascadeType.ALL)
    @SerializedName("mWorkerList")
    private List<MovementWorker> workersList=new ArrayList();

    public String getBase() {
        return base;
    }

    public void setBase(String name) {
        this.base = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    } 

    public List<MovementDepartment> getDepartmentsList() {
        return departmentsList;
    }

    public void setDepartmentsList(List<MovementDepartment> departmentsList) {
        this.departmentsList = departmentsList;
    }

    public List<MovementEquipment> getEquipmentsList() {
        return equipmentsList;
    }

    public void setEquipmentsList(List<MovementEquipment> equipmentsList) {
        this.equipmentsList = equipmentsList;
    }

    public List<MovementWorker> getWorkersList() {
        return workersList;
    }

    public void setWorkersList(List<MovementWorker> workersList) {
        this.workersList = workersList;
    }
    
   
}
