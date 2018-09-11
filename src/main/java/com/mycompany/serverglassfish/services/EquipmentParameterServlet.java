/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.EquipmentParameter;
import com.mycompany.serverglassfish.model.EquipmentParameter_;
import com.mycompany.serverglassfish.model.Equipment_;
import com.mycompany.serverglassfish.model.Parameter;
import gson.GsonUtil;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "EquipmentParameterServlet", urlPatterns = {"/equipmentParameter_servlet"})
public class EquipmentParameterServlet extends GenericManyToManyServlet<EquipmentParameter> {
    
    public EquipmentParameterServlet(){
        super(EquipmentParameter.class);
    }

    @Override
    public SingularAttribute getSearchField(String type) {
        return Equipment_.id;
    }

    @Override
    public String getJoinField(String type) {
        return  EquipmentParameter_.EQUIPMENT;
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(EquipmentParameter.class, "equipment")
                .addExclusion(Parameter.class, "values")
                .addExclusion(Parameter.class, "eq_parameter")
                .getGson();
                
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }
    
}
