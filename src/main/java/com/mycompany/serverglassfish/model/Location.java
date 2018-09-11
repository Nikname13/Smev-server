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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="location")
public class Location implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @SerializedName("mId")
    private int id;
    
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String address;
    
    @ManyToMany
    @JoinTable(name="department_location",
            joinColumns=@JoinColumn(name="location_id"),
            inverseJoinColumns=@JoinColumn(name="department_id"))
    @SerializedName("mDepartmentList")
    private List<Department> departmentsListLocation=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Department> getDepartmentsList() {
        return departmentsListLocation;
    }

    public void setDepartmentsList(List<Department> departmentsList) {
        this.departmentsListLocation = departmentsList;
    }
    
}
