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
@Table(name="movement_equipment")
public class MovementEquipment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @SerializedName("mId")
    private int id;
    
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @Column(name="inventory_number", nullable=false)
    @SerializedName("mInventoryNumber")
    private String inventoryNumber;
    
    @Column(name="equipment_type", nullable=false)
    @SerializedName("mType")
    private String equipmentType;
    
    @Column(name="equipment_id", nullable=false)
    @SerializedName("mEquipmentId")
    private int equipmentId;
    
    @ManyToOne
    @JoinColumn(name="movement_id",nullable=false)
    @SerializedName("mEquipmentMovement")
    private MovementLog equipmentMovement;

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

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }
    
    

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public MovementLog getEquipmentMovement() {
        return equipmentMovement;
    }

    public void setEquipmentMovement(MovementLog equipmentMovement) {
        this.equipmentMovement = equipmentMovement;
    }
    
}
