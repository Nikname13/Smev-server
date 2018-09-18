/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.EquipmentInventory;
import com.mycompany.serverglassfish.model.EquipmentInventory_;
import com.mycompany.serverglassfish.model.InventoryEditLog;
import com.mycompany.serverglassfish.model.InventoryEditLog_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "InventoryLogServlet", urlPatterns = {"/inventory_log_servlet"})
public class InventoryLogServlet extends GenericManyToManyServlet<InventoryEditLog> {
    
    public InventoryLogServlet() {
        super(InventoryEditLog.class,new TypeToken<ArrayList<InventoryEditLog>>(){}.getType());
    }

    @Override
    public SingularAttribute getSearchField(String type) {
        return EquipmentInventory_.id;
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(InventoryEditLog.class, "equipmentInventory")
                .getGson();
    }
    
    

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }

    @Override
    public String getJoinField(String type) {
        return InventoryEditLog_.EQUIPMENT_INVENTORY;
    }
    
    
    
    
}
