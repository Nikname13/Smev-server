/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.EquipmentInventory_;
import com.mycompany.serverglassfish.model.InventoryEditLog;
import gson.GsonUtil;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "InventoryLogServlet", urlPatterns = {"/inventory_log_servlet"})
public class InventoryLogServlet extends GenericManyToManyServlet<InventoryEditLog> {
    
    public InventoryLogServlet() {
        super(InventoryEditLog.class);
    }

    @Override
    public SingularAttribute getId() {
        return EquipmentInventory_.id;
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(InventoryEditLog.class, "equipmentInventory")
                .getGson();
    }

    @Override
    public String getNameField(String type) {
        return super.getNameField(type); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
