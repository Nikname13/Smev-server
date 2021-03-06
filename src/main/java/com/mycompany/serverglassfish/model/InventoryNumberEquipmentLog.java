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
@Table(name="inventory_number_equipment_log")
public class InventoryNumberEquipmentLog extends BaseModel implements Serializable {
  
    @Column(name="inventory_number", nullable=false)
    @SerializedName("mName")
    private String inventoryNumber;
    
    @Column(name="inventory_number_id", nullable=false)
    @SerializedName("mInventoryId")
    private int inventoryId;
    
    @Column(name="date", nullable=false)
    @SerializedName("mDate")
    private LocalDate dateInventoryLog;
    
    @Column(name="description", nullable=false)
    @SerializedName("mDescription")
    private String descriptionInventoryLog;
      
    @ManyToOne
    @JoinColumn(name="equipment_inventory_id")
    private EquipmentInventory equipmentInventory;

    public String getDescriptionState() {
        return descriptionInventoryLog;
    }

    public void setDescriptionState(String descriptionState) {
        this.descriptionInventoryLog = descriptionState;
    }

    public EquipmentInventory getEquipmentInventory() {
        return equipmentInventory;
    }

    public void setEquipmentInventory(EquipmentInventory equipmentInventory) {
        this.equipmentInventory = equipmentInventory;
    }


    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public LocalDate getDateInventoryLog() {
        return dateInventoryLog;
    }

    public void setDateInventoryLog(LocalDate dateInventoryLog) {
        this.dateInventoryLog = dateInventoryLog;
    }
    
    
}
