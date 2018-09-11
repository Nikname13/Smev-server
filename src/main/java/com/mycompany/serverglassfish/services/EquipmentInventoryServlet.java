/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.Area;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Equipment;
import com.mycompany.serverglassfish.model.EquipmentInventory;
import com.mycompany.serverglassfish.model.EquipmentInventory_;
import com.mycompany.serverglassfish.model.StateLog;
import com.mycompany.serverglassfish.model.FileDump;
import com.mycompany.serverglassfish.model.InventoryEditLog;
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.Post;
import com.mycompany.serverglassfish.model.State;
import com.mycompany.serverglassfish.model.Worker;

import gson.GsonUtil;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "EquipmentInventoryServlet", urlPatterns = {"/equipmentInventory_servlet"})
public class EquipmentInventoryServlet extends GenericManyToManyServlet<EquipmentInventory> {
    
    public EquipmentInventoryServlet() {
        super(EquipmentInventory.class);
    }

    @Override
    public void setField(EquipmentInventory entity) {
        if(entity.getEquipmentStates()!=null){
            for(StateLog state : entity.getEquipmentStates()){
                state.setEquipmentInventoryLog(entity);
            }
        }
        if(entity.getInventoryEditLogs()!=null){
            for(InventoryEditLog invLog:entity.getInventoryEditLogs()){
               invLog.setEquipmentInventory(entity);
            }
        }
    }

    @Override
    public SingularAttribute getSearchField(String type) {
        return EquipmentInventory_.id;
    }

    @Override
    public String getJoinField(String type) {
        switch(type){
            case "equipment": return EquipmentInventory_.EQUIPMENT_INV;
            case "department": return EquipmentInventory_.DEPARTMENT_EQUIPMENT;
        }
        return null;
    }
    
    
    
    @Override
    public Gson getGsonFromList() {
        return getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Equipment.class,"eq_parameters")
                .addExclusion(Equipment.class,"eq_inventory")
                .addExclusion(Equipment.class, "type")
                
                .addExclusion(InventoryNumber.class, "supply")
                .addExclusion(InventoryNumber.class, "eq_inventoryList")
                
                .addExclusion(EquipmentInventory.class,"inventoryEditLogs")
                .addExclusion(EquipmentInventory.class,"equipmentStates")
                
                .addExclusion(State.class, "equipmentInventoryList")
                
                .addExclusion(Department.class, "area")
                .addExclusion(Department.class, "purchaseListDepartment")
                .addExclusion(Department.class, "locationsListDepartment")
                .addExclusion(Department.class, "workersListDepartmnet")
                .addExclusion(Department.class, "equipmentList")
                .addExclusion(Department.class, "avatar")
                
                .addExclusion(FileDump.class, "equipmentAvatar")
                .getGson();
    }

}
