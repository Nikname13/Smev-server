/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="inventory_number", uniqueConstraints={@UniqueConstraint(columnNames={"number","supply_id"})})
public class InventoryNumber implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @SerializedName("mId")
    private int id;
    
    @Column(name="number", nullable=false)
    @SerializedName("mName")
    private String number;
    
    @Column(name="group_equip", nullable=false)
    @SerializedName("mGroup")
    private boolean group_equip;
    
    @Column(name="description")
    @SerializedName("mDescription")
    private String description;
    
    @ManyToOne
    @JoinColumn(name="supply_id",nullable=false)
    @SerializedName("mSupply")
    private Supply supply;
    
    @OneToMany(mappedBy="inv_number")
    private List<EquipmentInventory> eq_inventoryList=new ArrayList<>();
    
    @OneToMany(mappedBy="inventoryNumber")
    @Cascade({CascadeType.ALL})
    @SerializedName("mEntityList")
    private List<InventoryNumberLog> inventoryNumberLogList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isGroup_equip() {
        return group_equip;
    }

    public void setGroup_equip(boolean group_equip) {
        this.group_equip = group_equip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public List<EquipmentInventory> getEq_inventory() {
        return eq_inventoryList;
    }

    public void setEq_inventory(List<EquipmentInventory> eq_inventory) {
        this.eq_inventoryList = eq_inventory;
    }

    public List<InventoryNumberLog> getInventoryNumberLogList() {
        return inventoryNumberLogList;
    }

    public void setInventoryNumberLogList(List<InventoryNumberLog> inventoryNumberLogList) {
        this.inventoryNumberLogList = inventoryNumberLogList;
    }
 
    public void setField(){
        if(inventoryNumberLogList!=null){
            for(InventoryNumberLog numberLog:inventoryNumberLogList){
                numberLog.setInventoryNumber(this);
            }
        }
    }
}
