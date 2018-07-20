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
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.Movement;
import com.mycompany.serverglassfish.model.Worker;
import gson.GsonUtil;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "MovementServlet", urlPatterns = {"/movement_servlet"})
public class MovementServlet extends GenericCRUDServlet<Movement>{
    
    public MovementServlet() {
        super(Movement.class);
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(Area.class, "departmentsListArea")
                .addExclusion(Department.class,"purchaseListDepartment")
                .addExclusion(Department.class, "locationsListDepartment")
                .addExclusion(Department.class, "workersListDepartmnet")
                .addExclusion(Department.class, "equipmentList")
                .addExclusion(Movement.class, "equipmentsListMovement")
                .addExclusion(Movement.class, "workersListMovement")
                .getGson();
    }

    @Override
    public Gson getGsonFromEntity() {
        return new GsonUtil()
                .addExclusion(Area.class, "departmentsListArea")
                .addExclusion(Department.class,"purchaseListDepartment")
                .addExclusion(Department.class, "locationsListDepartment")
                .addExclusion(Department.class, "equipmentList")
                .addExclusion(Equipment.class,"eq_parameters")
                .addExclusion(Equipment.class,"eq_inventory")
                .addExclusion(Equipment.class, "type")
                .addExclusion(InventoryNumber.class, "supply")
                .addExclusion(InventoryNumber.class, "eq_inventoryList")
                .addExclusion(EquipmentInventory.class, "departmentEquipment")
                .addExclusion(Worker.class, "departmentWorker")
                .getGson();
    }
    
}
