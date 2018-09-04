/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.mycompany.serverglassfish.model.EquipmentParameter;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "EquipmentParameterServlet", urlPatterns = {"/equipmentParameter_servlet"})
public class EquipmentParameterServlet extends GenericCRUDServlet<EquipmentParameter> {
    
    public EquipmentParameterServlet(){
        super(EquipmentParameter.class);
    }
    
}
