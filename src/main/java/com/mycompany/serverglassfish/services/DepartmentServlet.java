/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.Area;
import com.mycompany.serverglassfish.model.Area_;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Department_;
import com.mycompany.serverglassfish.model.Equipment;
import com.mycompany.serverglassfish.model.EquipmentInventory;
import com.mycompany.serverglassfish.model.StateLog;
import com.mycompany.serverglassfish.model.FileDump;
import com.mycompany.serverglassfish.model.FileDump_;
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.Location;
import com.mycompany.serverglassfish.model.Post;
import com.mycompany.serverglassfish.model.Post_;
import com.mycompany.serverglassfish.model.Purchase;
import com.mycompany.serverglassfish.model.State;
import com.mycompany.serverglassfish.model.Worker;
import com.mycompany.serverglassfish.model.Worker_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "DepartmentServlet", urlPatterns = {"/department_servlet"})
public class DepartmentServlet extends GenericCRUDServlet<Department> {
    
    public DepartmentServlet() {
        super(Department.class,new TypeToken<ArrayList<Department>>(){}.getType());
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(Area.class, Area_.DEPARTMENTS_LIST_AREA)
                .addExclusion(Department.class, Department_.WORKERS_LIST_DEPARTMNET)
                .addExclusion(Department.class, Department_.PURCHASE_LIST_DEPARTMENT)
                .addExclusion(Department.class, Department_.EQUIPMENT_LIST)
                .getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Area.class, Area_.DEPARTMENTS_LIST_AREA)
                .addExclusion(Department.class, Department_.PURCHASE_LIST_DEPARTMENT)
                .addExclusion(Worker.class, Worker_.DEPARTMENT_WORKER)
                .addExclusion(Post.class, Post_.WORKER_LIST)
                .addExclusion(Department.class, Department_.EQUIPMENT_LIST)
                
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_AVATAR)
                .getGson();
    
}
}
