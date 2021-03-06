/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.ValueParameter;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "ValueParameterServlet", urlPatterns = {"/valueParameter_servlet"})
public class ValueParameterServlet extends GenericCRUDServlet<ValueParameter> {

    public ValueParameterServlet() {
        super(ValueParameter.class,new TypeToken<ArrayList<ValueParameter>>(){}.getType());
    }

    @Override
    public Gson getGson() {
        return new GsonUtil().getGson();
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil().getGson();
    }

}
