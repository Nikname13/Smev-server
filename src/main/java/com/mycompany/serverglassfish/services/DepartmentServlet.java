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
import com.mycompany.serverglassfish.model.Location;
import com.mycompany.serverglassfish.model.Post;
import com.mycompany.serverglassfish.model.Purchase;
import com.mycompany.serverglassfish.model.State;
import com.mycompany.serverglassfish.model.Worker;
import gson.GsonUtil;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "DepartmentServlet", urlPatterns = {"/department_servlet"})
public class DepartmentServlet extends GenericCRUDServlet<Department> {
    
    public DepartmentServlet() {
        super(Department.class);
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(Area.class, "departmentsListArea")
                .addExclusion(Department.class,"purchaseListDepartment")
                .addExclusion(Department.class, "workersListDepartmnet")
                .addExclusion(Department.class, "equipmentList")
                .getGson();
    }

    @Override
    public Gson getGsonFromEntity() {
        return new GsonUtil()
                .addExclusion(Area.class, "departmentsListArea")
                .addExclusion(Purchase.class, "departmentPurchase")
                .addExclusion(Worker.class, "departmentWorker")
                .addExclusion(Post.class, "workerList")
                .addExclusion(InventoryNumber.class, "supply")
                .addExclusion(InventoryNumber.class, "eq_inventoryList")
                .addExclusion(EquipmentInventory.class,"inventoryEditLogs")
                .addExclusion(EquipmentInventory.class,"equipmentStates")
                .addExclusion(State.class, "equipmentStateList")
                .addExclusion(Equipment.class, "eq_parameters")
                .addExclusion(Equipment.class, "eq_inventory")
                .addExclusion(Equipment.class, "type")
                .addExclusion(EquipmentInventory.class, "departmentEquipment")
                .getGson();
    }
    
}
