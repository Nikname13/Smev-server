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
@Table(name="equipment_state_log")
public class StateLog extends BaseModel implements Serializable {
 
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @Column(name="date", nullable=false)
    @SerializedName("mDate")
    private LocalDate dateState;
    
    @Column(name="description", nullable=false)
    @SerializedName("mDescription")
    private String descriptionState;
    
    @ManyToOne
    @JoinColumn(name="equipment_id")
    private EquipmentInventory equipmentInventoryLog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public LocalDate getDateState() {
        return dateState;
    }

    public void setDateState(LocalDate dateState) {
        this.dateState = dateState;
    }

    public String getDescriptionState() {
        return descriptionState;
    }

    public void setDescriptionState(String descriptionState) {
        this.descriptionState = descriptionState;
    }

    public EquipmentInventory getEquipmentInventoryLog() {
        return equipmentInventoryLog;
    }

    public void setEquipmentInventoryLog(EquipmentInventory equipmentInventoryLog) {
        this.equipmentInventoryLog = equipmentInventoryLog;
    }
}
