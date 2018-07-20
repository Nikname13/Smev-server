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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author a.zolotarev
 */

@Entity
@Table(name="parameter")
public class Parameter implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name = "id")
    @SerializedName("mId")
    private int id;
    
    @Column(name = "name",nullable=false)
    @SerializedName("mName")
    private String name;
    
    @OneToMany(mappedBy="parameter",cascade=CascadeType.ALL, orphanRemoval = true)
    @SerializedName("mEntityList")
    private List<ValueParameter> values=new ArrayList();
    
    @OneToMany(mappedBy="parameter", orphanRemoval = true)
    private List<EquipmentParameter> eq_parameter=new ArrayList<>();

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

    public void setValues(List<ValueParameter> values) {
        this.values = values;
    }
      
    public List<ValueParameter> getValues(){
        return values;
    }
    
    public void addValue(ValueParameter value){
        values.add(value);
        value.setParameter(this);
    }
    
    @Override
    public String toString(){
        return "Parameter: ID= "+getId()+" name= "+getName()+" values= ";
    }
}
