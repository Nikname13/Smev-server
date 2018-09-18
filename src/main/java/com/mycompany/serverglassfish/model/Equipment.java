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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="equipment")
public class Equipment implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @SerializedName("mId")
    private int id;
    
    @Column(name="name_fact", nullable=false)
    @SerializedName("mNameFact")
    private String name_fact;
    
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @Column(name="description")
    @SerializedName("mDescription")
    private String description;
    
    @ManyToOne
    @JoinColumn(name="type_id", nullable=false)
    @SerializedName("mTypeModel")
    private TypeModel type;

    @OneToMany(mappedBy="equipment", cascade=CascadeType.ALL)
    @SerializedName("mEntityList")
    private List<EquipmentParameter> eq_parameters=new ArrayList<>();
    
    @OneToMany(mappedBy="equipmentInv")
    private List<EquipmentInventory> eq_inventory=new ArrayList<>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_fact() {
        return name_fact;
    }

    public void setName_fact(String name_fact) {
        this.name_fact = name_fact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeModel getType() {
        return type;
    }

    public void setType(TypeModel type) {
        this.type = type;
    }

    public List<EquipmentParameter> getEq_parameter() {
        return eq_parameters;
    }

    public void setEq_parameter(List<EquipmentParameter> eq_param) {
        this.eq_parameters = eq_param;
    }
    
    public void addEqParameter(EquipmentParameter eq_param){
       eq_parameters.add(eq_param);
       eq_param.setEquipment(this);
    }

    public List<EquipmentInventory> getEq_inventory() {
        return eq_inventory;
    }

    public void setEq_inventory(List<EquipmentInventory> eq_inv) {
        eq_inventory = eq_inv;
    }
    
    public void addEq_inventory(EquipmentInventory eq_inv){
        eq_inventory.add(eq_inv);
        eq_inv.setEquipment(this);
        
    }
}
