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
import com.mycompany.serverglassfish.model.InventoryNumberEquipmentLog;
import com.mycompany.serverglassfish.model.InventoryNumberEquipmentLog_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "InventoryNumberEquipmentLogServlet", urlPatterns = {"/inventory_equipment_log_servlet"})
public class InventoryNumberEquipmentLogServlet extends GenericManyToManyServlet<InventoryNumberEquipmentLog> {
    
    public InventoryNumberEquipmentLogServlet() {
        super(InventoryNumberEquipmentLog.class,new TypeToken<ArrayList<InventoryNumberEquipmentLog>>(){}.getType());
    }

    @Override
    public SingularAttribute getSearchField(String type) {
        return EquipmentInventory_.id;
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(InventoryNumberEquipmentLog.class, InventoryNumberEquipmentLog_.EQUIPMENT_INVENTORY)
                .getGson();
    }
    
    

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }

    @Override
    public String getJoinField(String type) {
        return InventoryNumberEquipmentLog_.EQUIPMENT_INVENTORY;
    }
    
    
    
    
}
