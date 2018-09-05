/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.Parameter;
import com.mycompany.serverglassfish.model.Type;
import gson.GsonUtil;

import javax.servlet.annotation.WebServlet;


/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "TypeServlet", urlPatterns = {"/type_servlet"})
public class TypeServlet extends GenericCRUDServlet<Type>{

    public TypeServlet() {
        super(Type.class);
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                    .addExclusion(Type.class, "parameters")
                    .addExclusion(Type.class, "equipments")
                    .getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Type.class, "equipments")
                .addExclusion(Parameter.class, "values")
                .addExclusion(Parameter.class, "eq_parameter")
                .getGson();
    }
}
