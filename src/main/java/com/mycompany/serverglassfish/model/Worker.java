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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="worker")
public class Worker extends BaseModel implements Serializable{ 
    
    @Column(name="name", nullable=false)
    @SerializedName("mName")
    private String name;
    
    @ManyToOne
    @JoinColumn(name="department_id", nullable=false)
    @SerializedName("mDepartmentModel")
    private Department departmentWorker;
    
    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    @SerializedName("mPost")
    private Post post;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
    public Department getDepartmentWorker() {
        return departmentWorker;
    }

    public void setDepartmentWorker(Department departmentWorker) {
        this.departmentWorker = departmentWorker;
    }
}
