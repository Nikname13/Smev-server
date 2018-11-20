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
@Table(name="inventory_number_log")
public class InventoryNumberLog extends BaseModel implements Serializable {
  
    @Column(name="inventory_number", nullable=false)
    @SerializedName("mName")
    private String number;
    
    @Column(name="supply_number")
    @SerializedName("mSupplyNumber")
    private String supplyNumber;
    
    @Column(name="date", nullable=false)
    @SerializedName("mDate")
    private LocalDate dateInventoryLog;
    
    @Column(name="description", nullable=false)
    @SerializedName("mDescription")
    private String descriptionInventoryLog;
    
    @ManyToOne
    @JoinColumn(name="inventory_number_id")
    private InventoryNumber inventoryNumber;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSupplyNumber() {
        return supplyNumber;
    }

    public void setSupplyNumber(String supplyNumber) {
        this.supplyNumber = supplyNumber;
    }

    public LocalDate getDateInventoryLog() {
        return dateInventoryLog;
    }

    public void setDateInventoryLog(LocalDate dateInventoryLog) {
        this.dateInventoryLog = dateInventoryLog;
    }

    public String getDescriptionInventoryLog() {
        return descriptionInventoryLog;
    }

    public void setDescriptionInventoryLog(String descriptionInventoryLog) {
        this.descriptionInventoryLog = descriptionInventoryLog;
    }

    public InventoryNumber getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(InventoryNumber inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }
    
    

}
