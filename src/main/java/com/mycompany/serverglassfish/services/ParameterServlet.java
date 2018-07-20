/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;


import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.Parameter;
import com.mycompany.serverglassfish.model.ValueParameter;
import gson.GsonUtil;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "ParameterServlet", urlPatterns = {"/parameter_servlet"})
public class ParameterServlet extends GenericCRUDServlet<Parameter> {

    public ParameterServlet() {
        super(Parameter.class);
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
                .addExclusion(Parameter.class,"values")
                .addExclusion(Parameter.class, "eq_parameter")
                .getGson();
    }

    @Override
    public Gson getGsonFromEntity() {
        System.out.print("getGsonFromEntity Parameter");
        return new GsonUtil()
                .addExclusion(Parameter.class, "eq_parameter")
                .addExclusion(ValueParameter.class, "parameter")
                .getGson();
    }
}
