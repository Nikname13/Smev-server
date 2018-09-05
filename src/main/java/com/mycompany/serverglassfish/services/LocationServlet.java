/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Department_;
import com.mycompany.serverglassfish.model.Location;
import gson.GsonUtil;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "LocationServlet", urlPatterns = {"/location_servlet"})
public class LocationServlet extends GenericManyToManyServlet<Location> {
    
    public LocationServlet() {
        super(Location.class);
    }

    @Override
    public SingularAttribute getId() {
        return Department_.id;
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }
    

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Department.class, "area")
                .addExclusion(Department.class,"purchaseListDepartment")
                .addExclusion(Department.class, "workersListDepartmnet")
                .addExclusion(Department.class, "equipmentList")
                .getGson();
    }

    @Override
    public String getNameField(String type) {
        return new Location().getDepartmentFieldName();
    }
    
    
}
