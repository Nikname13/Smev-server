/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Equipment;
import com.mycompany.serverglassfish.model.EquipmentInventory;
import com.mycompany.serverglassfish.model.EquipmentParameter;
import com.mycompany.serverglassfish.model.StateLog;
import com.mycompany.serverglassfish.model.FileDump;
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.Parameter;
import com.mycompany.serverglassfish.model.Post;
import com.mycompany.serverglassfish.model.State;
import com.mycompany.serverglassfish.model.TypeModel;
import com.mycompany.serverglassfish.model.Worker;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "EquipmentServlet", urlPatterns = {"/equipment_servlet"})
public class EquipmentServlet extends GenericCRUDServlet<Equipment> {

    public EquipmentServlet() {
        super(Equipment.class,new TypeToken<ArrayList<Equipment>>(){}.getType());
    }

    @Override
    public void setField(Equipment entity) {
        System.out.println("EquipmentServlet print--------");
        if(entity.getEq_parameter() != null){
            for(EquipmentParameter value:entity.getEq_parameter()){
                value.setEquipment(entity);
                System.out.println("valuse name= "+value.getParameter().getName()+" id = "+value.getParameter().getId());
            }
        }
    }
    
    

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(TypeModel.class, "parameters")
                .addExclusion(TypeModel.class, "equipments")
                .addExclusion(Equipment.class, "eq_inventory")
                .addExclusion(Equipment.class, "eq_parameters")
                .getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(TypeModel.class, "equipments")
                .addExclusion(EquipmentParameter.class, "equipment")
                .addExclusion(Parameter.class,"values")
                .addExclusion(Parameter.class, "types")
                .addExclusion(Parameter.class, "eq_parameter")
                .addExclusion(Equipment.class,"eq_inventory")
                .getGson();
    }    
}
