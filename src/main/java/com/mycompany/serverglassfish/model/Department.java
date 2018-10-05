
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.lang.annotation.Annotation;
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
@Table(name="department")
public class Department implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @SerializedName("mId")
    private int id;
    
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @Column(name="number", nullable=false)
    @SerializedName("mNumber")
    private String number;
    
    @Column(name="electronicQ", nullable=false)
    @SerializedName("mElectronicQ")
    private boolean electronicQ;
    
    @Column(name="renting", nullable=false)
    @SerializedName("mRenting")
    private boolean renting;
    
    @Column(name="description")
    @SerializedName("mDescription")
    private String description;
    
    @ManyToOne
    @JoinColumn(name="area_id", nullable=false)
    @SerializedName("mAreaModel")
    private Area area;
    
    @OneToMany(mappedBy="departmentWorker")
    @SerializedName("mWorkerList")
    private List<Worker> workersListDepartmnet=new ArrayList<>();
    
    @OneToMany(mappedBy="departmentPurchase")
    @Cascade({CascadeType.ALL})
    @SerializedName("mEntityList")
    private List<Purchase> purchaseListDepartment=new ArrayList<>();
    
    @OneToMany(mappedBy="departmentEquipment")
    @SerializedName("mEquipmentList")
    private List<EquipmentInventory> equipmentList=new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name="file_dump_id")
    private FileDump avatar;
       
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isElectronicQ() {
        return electronicQ;
    }

    public void setElectronicQ(boolean electronicQ) {
        this.electronicQ = electronicQ;
    }

    public boolean isRenting() {
        return renting;
    }

    public void setRenting(boolean renting) {
        this.renting = renting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Purchase> getPurchaseListDepartment() {
        return purchaseListDepartment;
    }

    public void setPurchaseListDepartment(List<Purchase> purchaseListDepartment) {
        this.purchaseListDepartment = purchaseListDepartment;
    }
    
    public void addPurchase(Purchase purchase){
        purchase.setDepartmentPurchase(this);
        purchaseListDepartment.add(purchase);
    }

    public List<Worker> getWorkersListDepartmnet() {
        return workersListDepartmnet;
    }

    public void setWorkersListDepartmnet(List<Worker> workersListDepartmnet) {
        this.workersListDepartmnet = workersListDepartmnet;
    }

    public List<EquipmentInventory> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<EquipmentInventory> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public FileDump getAvatar() {
        return avatar;
    }

    public void setAvatar(FileDump avatar) {
        this.avatar = avatar;
    }
    
}
