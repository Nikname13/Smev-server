/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.Parameter;
import com.mycompany.serverglassfish.model.TypeModel;
import gson.GsonUtil;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;


/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "TypeServlet", urlPatterns = {"/type_servlet"})
public class TypeServlet extends GenericCRUDServlet<TypeModel>{

    public TypeServlet() {
        super(TypeModel.class,new TypeToken<ArrayList<TypeModel>>(){}.getType());
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                    .addExclusion(TypeModel.class, "parameters")
                    .addExclusion(TypeModel.class, "equipments")
                    .getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(TypeModel.class, "equipments")
                .addExclusion(Parameter.class, "values")
                .addExclusion(Parameter.class, "eq_parameter")
                .getGson();
    }
}
