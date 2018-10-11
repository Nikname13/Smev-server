/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.Parameter;
import com.mycompany.serverglassfish.model.Parameter_;
import com.mycompany.serverglassfish.model.ValueParameter;
import com.mycompany.serverglassfish.model.ValueParameter_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "ParameterServlet", urlPatterns = {"/parameter_servlet"})
public class ParameterServlet extends GenericCRUDServlet<Parameter> {

    public ParameterServlet() {
        super(Parameter.class,new TypeToken<ArrayList<Parameter>>(){}.getType());
    }

    @Override
    public void setField(Parameter p) {
        System.out.println(" encoding "+p.toString());
        if (p.getValues() != null) {
            for (ValueParameter value : p.getValues()) {
                value.setParameter(p);
                System.out.println("values_parameter= " + value.getName() + "id= " + value.getId() + " idParameter= " + value.getParameter().getId());
            }
        }
    }

    @Override
    public Gson getGsonFromList() {
        System.out.print("getGsonFromList Parameter");
        return new GsonUtil()
                .addExclusion(Parameter.class, Parameter_.VALUES)
                .addExclusion(Parameter.class, Parameter_.EQ_PARAMETER)
                .getGson();
    }

    @Override
    public Gson getGson() {
        System.out.print("getGsonFromEntity Parameter");
        return new GsonUtil()
                .addExclusion(Parameter.class, Parameter_.EQ_PARAMETER)
                .addExclusion(ValueParameter.class, ValueParameter_.PARAMETER)
                .getGson();
    }
}
