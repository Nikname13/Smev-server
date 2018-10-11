/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.InventoryNumber_;
import com.mycompany.serverglassfish.model.Provider;
import com.mycompany.serverglassfish.model.Supply;
import com.mycompany.serverglassfish.model.Supply_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "InventoryNumberServlet", urlPatterns = {"/inventoryNumber_servlet"})
public class InventoryNumberServlet extends GenericManyToManyServlet<InventoryNumber> {
    
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
                .addExclusion(Supply.class, Supply_.PROVIDER)
                .addExclusion(Supply.class, Supply_.INVENTORY_LIST)
                .addExclusion(InventoryNumber.class, InventoryNumber_.EQ_INVENTORY_LIST)
                .getGson();
    }   

    @Override
    public SingularAttribute getSearchField(String type) {
        return InventoryNumber_.id;
    }

    @Override
    public String getJoinField(String type) {
        return InventoryNumber_.SUPPLY;
    }
}
