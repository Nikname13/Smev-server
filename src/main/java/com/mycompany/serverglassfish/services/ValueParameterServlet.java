/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;


import com.mycompany.serverglassfish.model.ValueParameter;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "ValueParameterServlet", urlPatterns = {"/valueParameter_servlet"})
public class ValueParameterServlet extends GenericCRUDServlet<ValueParameter> {

    public ValueParameterServlet() {
        super(ValueParameter.class);
    }

}
