/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="type")
public class TypeModel extends BaseModel implements Serializable {
    
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @ManyToMany
    @JoinTable(name="type_parameter",
            joinColumns=@JoinColumn(name="type_id"),
            inverseJoinColumns=@JoinColumn(name="parameter_id"))
    @SerializedName("mEntityList")
    private List<Parameter> parameters=new ArrayList<>();
    
    @OneToMany(mappedBy="type",cascade=CascadeType.REFRESH, orphanRemoval = true)
    private List<Equipment> equipments=new ArrayList<>();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    
    public void addParameter(Parameter parameter){
        parameters.add(parameter); 
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }
}
