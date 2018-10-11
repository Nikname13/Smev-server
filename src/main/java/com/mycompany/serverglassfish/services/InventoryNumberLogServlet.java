/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.InventoryNumberLog;
import com.mycompany.serverglassfish.model.InventoryNumberLog_;
import com.mycompany.serverglassfish.model.InventoryNumber_;
import gson.GsonUtil;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "InventoryNumberLogServlet", urlPatterns = {"/inventory_log_servlet"})
public class InventoryNumberLogServlet extends GenericManyToManyServlet<InventoryNumberLog> {

    public InventoryNumberLogServlet() {
        super(InventoryNumberLog.class, new TypeToken<ArrayList<InventoryNumberLog>>(){}.getType());
    }

    @Override
    public SingularAttribute getSearchField(String type) {
        return InventoryNumber_.id;
    }

    @Override
    public String getJoinField(String type) {
        return InventoryNumberLog_.INVENTORY_NUMBER;
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(InventoryNumberLog.class, InventoryNumberLog_.INVENTORY_NUMBER)
                .getGson();
                
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }
    
}
