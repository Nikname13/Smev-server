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
import java.util.List;
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
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="supply")
public class Supply extends BaseModel implements Serializable {
    
    @Column(name="number", nullable=false,unique=true)
    @SerializedName("mName")
    private String number;

    
    @Column(name="type", nullable=false)
    @SerializedName("mTypeSupply")
    private String type;
    
    @Column(name="date", nullable=false)
    @SerializedName("mDate")
    private LocalDate date;
        
    @Column(name="description")
    @SerializedName("mDescription")
    private String description;
    
    @ManyToOne
    @JoinColumn(name="provider_id", nullable=false)
    @SerializedName("mProviderModel")
    private Provider provider;
    
    @OneToMany(mappedBy="supply", cascade=CascadeType.ALL)
    @SerializedName("mEntityList")
    private List<InventoryNumber> inventoryList =new ArrayList(); 

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<InventoryNumber> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<InventoryNumber> inventoryList) {
        this.inventoryList = inventoryList;
    }
    
    public void addInventoryNumber(InventoryNumber number){
        inventoryList.add(number);
        number.setSupply(this);
    }
    
}
