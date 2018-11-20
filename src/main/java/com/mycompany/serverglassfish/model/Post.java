/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="post")
public class Post extends BaseModel implements Serializable {
  
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @OneToMany(mappedBy="post")
    private Set<Worker> workerList=new HashSet<Worker>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Worker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(Set<Worker> workerList) {
        this.workerList = workerList;
    }
    
    public void addWorker(Worker worker){
        workerList.add(worker);
    }
    
    
}
