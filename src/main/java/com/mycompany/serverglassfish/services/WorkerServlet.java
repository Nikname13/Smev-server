/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Worker;
import gson.GsonUtil;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "WorkerServlet", urlPatterns = {"/worker_servlet"})
public class WorkerServlet extends GenericCRUDServlet<Worker> {
    
    public WorkerServlet() {
        super(Worker.class);
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(Department.class, "area")
                .addExclusion(Department.class, "purchaseListDepartment")
                .addExclusion(Department.class, "locationsListDepartment")
                .addExclusion(Department.class, "workersListDepartmnet")
                .addExclusion(Department.class, "equipmentList")
                .getGson();
    }

    @Override
    public Gson getGsonFromEntity() {
        return new GsonUtil()
                .addExclusion(Department.class, "area")
                .addExclusion(Department.class, "purchaseListDepartment")
                .addExclusion(Department.class, "locationsListDepartment")
                .addExclusion(Department.class, "workersListDepartmnet")
                .addExclusion(Department.class, "equipmentList")
                .getGson();
    }
  
}
