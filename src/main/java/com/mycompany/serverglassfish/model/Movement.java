/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="movement")
public class Movement implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @SerializedName("mId")
    private int id;
    
    @Column(name="base", nullable=false)
    @SerializedName("mName")
    private String base;
    
    @Column(name="date", nullable=false)
    @SerializedName("mDate")
    private LocalDate date;
    
    @ManyToMany
    @JoinTable(name="movement_department",
            joinColumns=@JoinColumn(name="movement_id"),
            inverseJoinColumns=@JoinColumn(name="department_id"))
    @SerializedName("mDepartmentList")
    private List<Department> movementsListMovement=new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name="movement_equipment",
            joinColumns=@JoinColumn(name="movement_id"),
            inverseJoinColumns=@JoinColumn(name="equipment_id"))
    @SerializedName("mEntityList")
    private List<EquipmentInventory> equipmentsListMovement=new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name="movement_worker",
            joinColumns=@JoinColumn(name="movement_id"),
            inverseJoinColumns=@JoinColumn(name="worker_id"))
    @SerializedName("mWorkerList")
    private Set<Worker> workersListMovement=new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Department> getMovementsListMovement() {
        return movementsListMovement;
    }

    public void setMovementsListMovement(List<Department> movementsListMovement) {
        this.movementsListMovement = movementsListMovement;
    }

    public List<EquipmentInventory> getEquipmentsListMovement() {
        return equipmentsListMovement;
    }

    public void setEquipmentsListMovement(List<EquipmentInventory> equipmentsListMovement) {
        this.equipmentsListMovement = equipmentsListMovement;
    }

    public Set<Worker> getWorkersListMovement() {
        return workersListMovement;
    }

    public void setWorkersListMovement(Set<Worker> workersListMovement) {
        this.workersListMovement = workersListMovement;
    }
    
    
}
