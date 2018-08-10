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
import com.mycompany.serverglassfish.model.MovementLog;
import com.mycompany.serverglassfish.model.MovementDepartment;
import com.mycompany.serverglassfish.model.MovementEquipment;
import com.mycompany.serverglassfish.model.MovementWorker;
import com.mycompany.serverglassfish.model.Worker;
import gson.GsonUtil;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "MovementServlet", urlPatterns = {"/movement_servlet"})
public class MovementServlet extends GenericCRUDServlet<MovementLog>{
    
    public MovementServlet() {
        super(MovementLog.class);
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(MovementLog.class, "departmentsList")
                .addExclusion(MovementLog.class, "equipmentsList")
                .addExclusion(MovementLog.class, "workersList")
                .getGson();
    }

    @Override
    public Gson getGsonFromEntity() {
        return new GsonUtil()
                .addExclusion(MovementDepartment.class,"departmentMovement")
                .addExclusion(MovementEquipment.class,"equipmentMovement")
                .addExclusion(MovementWorker.class,"workerMovement")
                .getGson();
    }
    
}
