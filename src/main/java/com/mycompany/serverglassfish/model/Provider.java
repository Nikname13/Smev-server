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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="provider")
public class Provider implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name = "id")
    @SerializedName("mId")
    private int id;
    
    @Column(name = "name",nullable=false)
    @SerializedName("mName")
    private String name;
    
    @Column(name="description")
    @SerializedName("mDescription")
    private String description;
    
    @OneToMany(mappedBy="provider", orphanRemoval = true)
    @SerializedName("mEntityList")
    private List<Supply> supplys=new ArrayList();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Supply> getSupply() {
        return supplys;
    }

    public void setSupply(List<Supply> supply) {
        this.supplys = supply;
    }
    
    public void addSupply(Supply supply){
        supply.setProvider(this);
        supplys.add(supply);
    }
}
