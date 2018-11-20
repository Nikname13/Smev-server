/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Equipment;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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
@Table(name="file_dump")
public class FileDump extends BaseModel implements Serializable  {
  
    @Column(name = "name",nullable=false)
    @SerializedName("mName")
    private String name;
    
    @Column(name="path", nullable=false, unique=true)
    @SerializedName("mPath")
    private String path;
    
    @ManyToMany(targetEntity=Department.class)
    @JoinTable(name="department_documentation_file",
            joinColumns=@JoinColumn(name="file_dump_id"),
            inverseJoinColumns=@JoinColumn(name="department_id"))
    private  List<Department> department_doc_files=new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name="department_config_file",
            joinColumns=@JoinColumn(name="file_dump_id"),
            inverseJoinColumns=@JoinColumn(name="department_id"))
    private  List<Department> department_config_files=new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name="department_photo_file",
            joinColumns=@JoinColumn(name="file_dump_id"),
            inverseJoinColumns=@JoinColumn(name="department_id"))
    private  List<Department> department_photo_files=new ArrayList<>();
    
    @OneToMany(mappedBy="avatar")
    private Set<Department> departmentAvatar=new HashSet();
    
    @ManyToMany
    @JoinTable(name="equipment_config_file",
            joinColumns=@JoinColumn(name="file_dump_id"),
            inverseJoinColumns=@JoinColumn(name="equipment_id"))
    private  List<Equipment> equipment_config_files=new ArrayList<>();
     
    @ManyToMany
    @JoinTable(name="equipmentInvent_photo_file",
            joinColumns=@JoinColumn(name="file_dump_id"),
            inverseJoinColumns=@JoinColumn(name="equipment_id"))
    private  List<EquipmentInventory> equipmentInvent_photo_files=new ArrayList<>();
    
    @OneToMany(mappedBy="avatar")
    private Set<EquipmentInventory> equipmentAvatar=new HashSet();

    @ManyToMany
    @JoinTable(name="supply_documentation_file",
            joinColumns=@JoinColumn(name="file_dump_id"),
            inverseJoinColumns=@JoinColumn(name="supply_id"))
    private  List<Supply> supply_doc_files=new ArrayList<>();
  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    

    public List<Department> getDepartment_doc_files() {
        return department_doc_files;
    }

    public void setDepartment_doc_files(List<Department> department_doc_files) {
        this.department_doc_files = department_doc_files;
    }

    public List<Department> getDepartment_config_files() {
        return department_config_files;
    }

    public void setDepartment_config_files(List<Department> department_config_files) {
        this.department_config_files = department_config_files;
    }

    public List<Department> getDepartment_photo_files() {
        return department_photo_files;
    }

    public void setDepartment_photo_files(List<Department> department_photo_files) {
        this.department_photo_files = department_photo_files;
    }

    public Set<Department> getDepartmentAvatar() {
        return departmentAvatar;
    }

    public void setDepartmentAvatar(Set<Department> departmentAvatar) {
        this.departmentAvatar = departmentAvatar;
    }

    public List<Equipment> getEquipment_config_files() {
        return equipment_config_files;
    }

    public void setEquipment_config_files(List<Equipment> equipment_config_files) {
        this.equipment_config_files = equipment_config_files;
    }

    public List<EquipmentInventory> getEquipmentInvent_photo_file() {
        return equipmentInvent_photo_files;
    }

    public void setEquipmentInvent_photo_file(List<EquipmentInventory> equipmentInvent_photo_file) {
        this.equipmentInvent_photo_files = equipmentInvent_photo_file;
    }
    
    public Set<EquipmentInventory> getEquipmentAvatar() {
        return equipmentAvatar;
    }

    public void setEquipmentAvatar(Set<EquipmentInventory> equipmentAvatar) {
        this.equipmentAvatar = equipmentAvatar;
    }

    public List<Supply> getSupply_documentation_file() {
        return supply_doc_files;
    }

    public void setSupply_documentation_file(List<Supply> supply_documentation_file) {
        this.supply_doc_files = supply_documentation_file;
    }
    
    public void addDoc(Department department){
        department_doc_files.add(department);
    }
    
    public void addDoc(Supply supply){
        supply_doc_files.add(supply);
    }
    
    public void addConfig(Department department){
        department_config_files.add(department);
    }
        
    public void addConfig(Equipment equipment){
        equipment_config_files.add(equipment);
    }
    
    public void addPhoto(Department department){
        department_photo_files.add(department);
    }
        
    public void addPhoto(EquipmentInventory eq_inventory){
        equipmentInvent_photo_files.add(eq_inventory);
    }


    
    
}
