/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Equipment;
import com.mycompany.serverglassfish.model.EquipmentInventory;
import com.mycompany.serverglassfish.model.EquipmentParameter;
import com.mycompany.serverglassfish.model.EquipmentState;
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.Parameter;
import com.mycompany.serverglassfish.model.Post;
import com.mycompany.serverglassfish.model.State;
import com.mycompany.serverglassfish.model.Type;
import com.mycompany.serverglassfish.model.Worker;
import gson.GsonUtil;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "EquipmentServlet", urlPatterns = {"/equipment_servlet"})
public class EquipmentServlet extends GenericCRUDServlet<Equipment> {

    public EquipmentServlet() {
        super(Equipment.class);
    }

    @Override
    public void setField(Equipment entity) {
        System.out.println("EquipmentServlet print--------");
        if(entity.getEq_parameter() != null){
            for(EquipmentParameter value:entity.getEq_parameter()){
                value.setEquipment(entity);
                System.out.println("valuse name= "+value.getParameter().getName()+" id = "+value.getParameter().getId());
            }
        }
    }
    
    

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(Type.class, "parameters")
                .addExclusion(Type.class, "equipments")
                .addExclusion(EquipmentParameter.class,"equipment")
                .addExclusion(Parameter.class,"values")
                .addExclusion(Parameter.class, "types")
                .addExclusion(Parameter.class, "eq_parameter")
                .addExclusion(Equipment.class, "eq_inventory")
                .addExclusion(Equipment.class, "eq_parameters")
                .getGson();
    }

    @Override
    public Gson getGsonFromEntity() {
        return new GsonUtil()
                .addExclusion(Type.class, "equipments")
                .addExclusion(EquipmentParameter.class, "equipment")
                .addExclusion(Parameter.class,"values")
                .addExclusion(Parameter.class, "types")
                .addExclusion(Parameter.class, "eq_parameter")
                .addExclusion(EquipmentInventory.class,"equipmentInv")
                .addExclusion(EquipmentInventory.class,"inventoryEditLogs")
                
                .addExclusion(State.class, "equipmentInventoryList")
                .addExclusion(InventoryNumber.class, "supply")
                .addExclusion(InventoryNumber.class, "eq_inventoryList")
                .addExclusion(Department.class, "area")
                .addExclusion(Department.class, "purchaseListDepartment")
                .addExclusion(Department.class, "locationsListDepartment")
                .addExclusion(Worker.class, "departmentWorker")
                .addExclusion(Post.class, "workerList")
                .addExclusion(Department.class, "equipmentList")
                .getGson();
    }    
}
