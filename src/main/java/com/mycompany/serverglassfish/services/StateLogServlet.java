/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.EquipmentInventory_;
import com.mycompany.serverglassfish.model.StateLog;
import gson.GsonUtil;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "StateLogServlet", urlPatterns = {"/state_log_servlet"})
public class StateLogServlet extends GenericManyToManyServlet<StateLog> {
    
    public StateLogServlet() {
        super(StateLog.class);
    }

    @Override
    public SingularAttribute getId() {
        return EquipmentInventory_.id;
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(StateLog.class, "equipmentInventoryLog")
                .getGson();
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }

    @Override
    public String getNameField(String type) {
        return new StateLog().getEquipmentFieldName();
    }  
}
