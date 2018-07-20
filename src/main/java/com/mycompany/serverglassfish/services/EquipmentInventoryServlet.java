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
import com.mycompany.serverglassfish.model.EquipmentState;
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.State;
import com.mycompany.serverglassfish.model.Worker;

import gson.GsonUtil;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "EquipmentInventoryServlet", urlPatterns = {"/equipmentInventory_servlet"})
public class EquipmentInventoryServlet extends GenericCRUDServlet<EquipmentInventory> {
    
    public EquipmentInventoryServlet() {
        super(EquipmentInventory.class);
    }

    @Override
    public void setField(EquipmentInventory entity) {
        if(entity.getEquipmentStates()!=null){
            for(EquipmentState state : entity.getEquipmentStates()){
                state.setEquipmentSate(entity);
            }
        }
    }
    
    

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(Equipment.class,"eq_parameters")
                .addExclusion(Equipment.class,"eq_inventory")
                .addExclusion(Equipment.class, "type")
                .addExclusion(InventoryNumber.class, "supply")
                .addExclusion(InventoryNumber.class, "eq_inventoryList")
                .addExclusion(EquipmentState.class, "equipmentState")
                .addExclusion(State.class, "equipmentStateList")
                .addExclusion(Department.class, "area")
                .addExclusion(Department.class,"purchaseListDepartment")
                .addExclusion(Department.class, "locationsListDepartment")
                .addExclusion(Department.class, "workersListDepartmnet")
                .addExclusion(Department.class, "equipmentList")
                .getGson();
    }

    @Override
    public Gson getGsonFromEntity() {
        return new GsonUtil()
                .addExclusion(Equipment.class,"eq_parameters")
                .addExclusion(Equipment.class,"eq_inventory")
                .addExclusion(Equipment.class, "type")
                .addExclusion(InventoryNumber.class, "supply")
                .addExclusion(InventoryNumber.class, "eq_inventoryList")
                .addExclusion(EquipmentState.class, "equipmentState")
                .addExclusion(State.class, "equipmentStateList")
                .addExclusion(Department.class, "area")
                .addExclusion(Department.class,"purchaseListDepartment")
                .addExclusion(Department.class, "locationsListDepartment")
                .addExclusion(Worker.class, "departmentWorker")
                .addExclusion(Department.class, "equipmentList")
                .getGson();
    }

}
