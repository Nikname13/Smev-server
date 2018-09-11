/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.DAO.GenericHibernateDAO;
import com.mycompany.serverglassfish.model.Area;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Equipment;
import com.mycompany.serverglassfish.model.EquipmentInventory;
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.MovementLog;
import com.mycompany.serverglassfish.model.MovementDepartment;
import com.mycompany.serverglassfish.model.MovementDepartment_;
import com.mycompany.serverglassfish.model.MovementEquipment;
import com.mycompany.serverglassfish.model.MovementEquipment_;
import com.mycompany.serverglassfish.model.MovementLog_;
import com.mycompany.serverglassfish.model.MovementWorker;
import com.mycompany.serverglassfish.model.MovementWorker_;
import com.mycompany.serverglassfish.model.Worker;
import gson.GsonUtil;
import java.util.List;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "MovementServlet", urlPatterns = {"/movement_servlet"})
public class MovementServlet extends GenericManyToManyServlet<MovementLog>{
    
    public MovementServlet() {
        super(MovementLog.class);
    }

    @Override
    public void setField(MovementLog entity) {
        for(MovementWorker worker:entity.getWorkersList()){
            worker.setWorkerMovement(entity);
        }
        for(MovementDepartment department:entity.getDepartmentsList()){
            department.setDepartmentMovement(entity);
        }
        for(MovementEquipment equipment:entity.getEquipmentsList()){
            equipment.setEquipmentMovement(entity);
        }
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
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(MovementDepartment.class,"departmentMovement")
                .addExclusion(MovementEquipment.class,"equipmentMovement")
                .addExclusion(MovementWorker.class,"workerMovement")
                .getGson();
    }

    @Override
    public SingularAttribute getSearchField(String type) {
        switch(type){
            case "equipment":
                return MovementEquipment_.equipmentId;
            case "department":
                return MovementDepartment_.departmentId;
            case "worker":
                return MovementWorker_.workerId;
            default:return MovementLog_.id;
        }
    }

    @Override
    public String getJoinField(String type) {
        switch(type){
            case "equipment":
                return MovementLog_.EQUIPMENTS_LIST;
            case "department":
                return MovementLog_.DEPARTMENTS_LIST;
            case "worker":
                return MovementLog_.WORKERS_LIST;
                default:return "";
        }
        
    }
    
    
    
}
