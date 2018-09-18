/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.Area;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "AreaServlet", urlPatterns = {"/area_servlet"})
public class AreaServlet extends GenericCRUDServlet<Area> {
    
    public AreaServlet() {
        super(Area.class,new TypeToken<ArrayList<Area>>(){}.getType());
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Area.class,"departmentsListArea")
                .getGson();
    }
    
    
}
