/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Department_;
import com.mycompany.serverglassfish.model.Post;
import com.mycompany.serverglassfish.model.Worker;
import com.mycompany.serverglassfish.model.Worker_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "WorkerServlet", urlPatterns = {"/worker_servlet"})
public class WorkerServlet extends GenericManyToManyServlet<Worker> {
    
    public WorkerServlet() {
        super(Worker.class,new TypeToken<ArrayList<Worker>>(){}.getType());
    }

    @Override
    public void setField(Worker entity) {
        if(entity.getPost()!=null){
           entity.getPost().addWorker(entity);
        }
    }
    
    

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Department.class, "area")
                .addExclusion(Department.class, "purchaseListDepartment")
                .addExclusion(Department.class, "locationsListDepartment")
                .addExclusion(Department.class, "workersListDepartmnet")
                .addExclusion(Department.class, "equipmentList")
                .addExclusion(Post.class, "workerList")
                .getGson();
    }

    @Override
    public SingularAttribute getSearchField(String type) {
                return Department_.id;
    }

    @Override
    public String getJoinField(String type) {
        return Worker_.DEPARTMENT_WORKER;
    }
  
}
