/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="movement_worker")
public class MovementWorker extends BaseModel implements Serializable {  
    
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @Column(name="post", nullable=false)
    @SerializedName("mWorkerPost")
    private String post;
    
    @Column(name="worker_id", nullable=false)
    @SerializedName("mWorkerId")
    private int workerId;
    
    @ManyToOne
    @JoinColumn(name="movement_id", nullable=false)
    @SerializedName("mWorkerMovement")
    private MovementLog workerMovement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public MovementLog getWorkerMovement() {
        return workerMovement;
    }

    public void setWorkerMovement(MovementLog workerMovement) {
        this.workerMovement = workerMovement;
    }
}
