/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.EquipmentInventory_;
import com.mycompany.serverglassfish.model.StateLog;
import com.mycompany.serverglassfish.model.StateLog_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "StateLogServlet", urlPatterns = {"/state_log_servlet"})
public class StateLogServlet extends GenericManyToManyServlet<StateLog> {
    
    public StateLogServlet() {
        super(StateLog.class,new TypeToken<ArrayList<StateLog>>(){}.getType());
    }

    @Override
    public SingularAttribute getSearchField(String type) {
        return EquipmentInventory_.id;
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(StateLog.class, StateLog_.EQUIPMENT_INVENTORY_LOG)
                .getGson();
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }

    @Override
    public String getJoinField(String type) {
        return StateLog_.EQUIPMENT_INVENTORY_LOG;
    }  
}
