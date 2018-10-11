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
import com.mycompany.serverglassfish.model.Location;
import com.mycompany.serverglassfish.model.Location_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "LocationServlet", urlPatterns = {"/location_servlet"})
public class LocationServlet extends GenericManyToManyServlet<Location> {
    
    public LocationServlet() {
        super(Location.class,new TypeToken<ArrayList<Location>>(){}.getType());
    }

    @Override
    public SingularAttribute getSearchField(String type) {
                return Department_.id;     
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }
    

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Department.class, Department_.AREA)
                .addExclusion(Department.class, Department_.PURCHASE_LIST_DEPARTMENT)
                .addExclusion(Department.class, Department_.WORKERS_LIST_DEPARTMNET)
                .addExclusion(Department.class, Department_.EQUIPMENT_LIST)
                .getGson();
    }

    @Override
    public String getJoinField(String type) {
        return Location_.DEPARTMENTS_LIST_LOCATION;
    }
    
    
}
