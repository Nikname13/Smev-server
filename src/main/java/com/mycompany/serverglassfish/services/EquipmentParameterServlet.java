/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.EquipmentParameter;
import com.mycompany.serverglassfish.model.EquipmentParameter_;
import com.mycompany.serverglassfish.model.Equipment_;
import com.mycompany.serverglassfish.model.Parameter;
import com.mycompany.serverglassfish.model.Parameter_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "EquipmentParameterServlet", urlPatterns = {"/equipmentParameter_servlet"})
public class EquipmentParameterServlet extends GenericManyToManyServlet<EquipmentParameter> {
    
    public EquipmentParameterServlet(){
        super(EquipmentParameter.class,new TypeToken<ArrayList<EquipmentParameter>>(){}.getType());
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
                .addExclusion(EquipmentParameter.class, EquipmentParameter_.EQUIPMENT)
                .addExclusion(Parameter.class, Parameter_.VALUES)
                .addExclusion(Parameter.class, Parameter_.EQ_PARAMETER)
                .getGson();
                
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }
    
}
