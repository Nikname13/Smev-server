/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="equipment_inventory")
public class EquipmentInventory implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @SerializedName("mId")
    private int id;
        
    @Column(name="guaranty")
    @SerializedName("mGuaranty")
    private int guaranty;
    
    @Column(name="photos")
    @SerializedName("mPhotos")
    private String photos;
    
    @Column(name="description")
    @SerializedName("mDescription")
    private String description;
    
    @Column(name="description_department")
    @SerializedName("mDescription_department")
    private String description_department;
    
    @ManyToOne
    @JoinColumn(name="equipment_id", nullable=false)
    @SerializedName("mEquipmentModel")
    private Equipment equipmentInv;
    
    @ManyToOne
    @JoinColumn(name="inventory_id", nullable=false)
    @SerializedName("mInventoryNumber")
    private InventoryNumber inv_number;
    
    @OneToMany(mappedBy="equipmentState")
    @Cascade({CascadeType.ALL})
    @SerializedName("mEntityList")
    private List<EquipmentState> equipmentStates=new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name="department_id",nullable=false)
    @SerializedName("mDepartmentModel")
    private Department departmentEquipment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuaranty() {
        return guaranty;
    }

    public void setGuaranty(int guaranty) {
        this.guaranty = guaranty;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_department() {
        return description_department;
    }

    public void setDescription_department(String description_department) {
        this.description_department = description_department;
    }

    public Equipment getEquipment() {
        return equipmentInv;
    }

    public void setEquipment(Equipment equipment) {
        this.equipmentInv = equipment;
    }

    public InventoryNumber getInv_number() {
        return inv_number;
    }

    public void setInv_number(InventoryNumber inv_number) {
        this.inv_number = inv_number;
    }
    
    
    public List<EquipmentState> getEquipmentStates() {
        return equipmentStates;
    }

    public void setEquipmentStates(List<EquipmentState> equipmentStates) {
        this.equipmentStates = equipmentStates;
    }
    
    public void addState(EquipmentState state){
        equipmentStates.add(state);
        state.setEquipmentSate(this);
    }

    public Department getDepartmentEquipment() {
        return departmentEquipment;
    }

    public void setDepartmentEquipment(Department departmentEquipment) {
        this.departmentEquipment = departmentEquipment;
    }
}