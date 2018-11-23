/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.Area;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Department_;
import com.mycompany.serverglassfish.model.Equipment;
import com.mycompany.serverglassfish.model.EquipmentInventory;
import com.mycompany.serverglassfish.model.EquipmentInventory_;
import com.mycompany.serverglassfish.model.Equipment_;
import com.mycompany.serverglassfish.model.StateLog;
import com.mycompany.serverglassfish.model.FileDump;
import com.mycompany.serverglassfish.model.FileDump_;
import com.mycompany.serverglassfish.model.InventoryNumberEquipmentLog;
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.InventoryNumber_;
import com.mycompany.serverglassfish.model.Post;
import com.mycompany.serverglassfish.model.State;
import com.mycompany.serverglassfish.model.State_;
import com.mycompany.serverglassfish.model.TypeModel;
import com.mycompany.serverglassfish.model.TypeModel_;
import com.mycompany.serverglassfish.model.Worker;

import gson.GsonUtil;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
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
        super(EquipmentInventory.class,new TypeToken<ArrayList<EquipmentInventory>>(){}.getType());
    }

    @Override
    public void setField(EquipmentInventory entity) {
        if(entity.getEquipmentStates()!=null){
            for(StateLog state : entity.getEquipmentStates()){
                state.setEquipmentInventoryLog(entity);
            }
        }
        if(entity.getInventoryEditLogs()!=null){
            for(InventoryNumberEquipmentLog invLog:entity.getInventoryEditLogs()){
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
            case "inventoryNumber": return EquipmentInventory_.INV_NUMBER;
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
                .addExclusion(Equipment.class, Equipment_.EQ_PARAMETERS)
                .addExclusion(Equipment.class, Equipment_.EQ_INVENTORY)
                
                .addExclusion(TypeModel.class, TypeModel_.PARAMETERS)
                .addExclusion(TypeModel.class, TypeModel_.EQUIPMENTS)
                
                .addExclusion(InventoryNumber.class, InventoryNumber_.SUPPLY)
                .addExclusion(InventoryNumber.class, InventoryNumber_.EQ_INVENTORY_LIST)
                .addExclusion(InventoryNumber.class, InventoryNumber_.INVENTORY_NUMBER_LOG_LIST)
                
                .addExclusion(EquipmentInventory.class,EquipmentInventory_.INVENTORY_EDIT_LOGS)
                .addExclusion(EquipmentInventory.class,EquipmentInventory_.EQUIPMENT_STATES)
                
                .addExclusion(State.class, State_.EQUIPMENT_INVENTORY_LIST)
                
                .addExclusion(Department.class, Department_.AREA)
                .addExclusion(Department.class, Department_.PURCHASE_LIST_DEPARTMENT)
                .addExclusion(Department.class, Department_.WORKERS_LIST_DEPARTMNET)
                .addExclusion(Department.class, Department_.EQUIPMENT_LIST)
                .addExclusion(Department.class, Department_.AVATAR)
                
                .addExclusion(FileDump.class, FileDump_.EQUIPMENT_AVATAR)
                
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_DOC_FILES)
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_CONFIG_FILES)
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_PHOTO_FILES)
                
                .addExclusion(FileDump.class, FileDump_.EQUIPMENT_CONFIG_FILES)
                
                .addExclusion(FileDump.class, FileDump_.EQUIPMENT_INVENT_PHOTO_FILES)
                .addExclusion(FileDump.class, FileDump_.EQUIPMENT_AVATAR)
                
                .addExclusion(FileDump.class, FileDump_.SUPPLY_DOC_FILES)
                .getGson();
    }

}
