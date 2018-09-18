/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.Provider;
import com.mycompany.serverglassfish.model.Supply;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "InventoryNumberServlet", urlPatterns = {"/inventoryNumber_servlet"})
public class InventoryNumberServlet extends GenericCRUDServlet<InventoryNumber> {
    
    public InventoryNumberServlet() {
        super(InventoryNumber.class,new TypeToken<ArrayList<InventoryNumber>>(){}.getType());
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();         
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Supply.class, "provider")
                .addExclusion(Supply.class, "inventoryList")
                .addExclusion(InventoryNumber.class, "eq_inventoryList")
                .getGson();
    }   
}
