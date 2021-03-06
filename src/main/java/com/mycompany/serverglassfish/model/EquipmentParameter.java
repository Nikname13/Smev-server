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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author a.zolotarev
 */
@Entity
@Table(name="equipment_parameter", uniqueConstraints={@UniqueConstraint(columnNames={"equipment_id","parameter_id"})})
public class EquipmentParameter extends BaseModel implements Serializable {
  
    @ManyToOne
    @JoinColumn(name="equipment_id", nullable=false)
    private Equipment equipment;
    
    @ManyToOne
    @JoinColumn(name="parameter_id", nullable=false)
    @SerializedName("mParameterModel")
    private Parameter parameter;
    
    @Column(name="parameterValue")
    @SerializedName("mName")
    private String parameterValue;

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return parameterValue;
    }

    public void setValue(String value) {
        this.parameterValue = value;
    }
}
