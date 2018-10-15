/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.InventoryNumber;
import com.mycompany.serverglassfish.model.InventoryNumberLog;
import com.mycompany.serverglassfish.model.Provider;
import com.mycompany.serverglassfish.model.Provider_;
import com.mycompany.serverglassfish.model.Supply;
import com.mycompany.serverglassfish.model.Supply_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "SupplyServlet", urlPatterns = {"/supply_servlet"})
public class SupplyServlet extends GenericCRUDServlet<Supply> {
    
    public SupplyServlet() {
        super(Supply.class,new TypeToken<ArrayList<Supply>>(){}.getType());
    }

    @Override
    public void setField(Supply entity) {
        if(entity.getInventoryList()!=null){
            for(InventoryNumber number:entity.getInventoryList()){
                number.setSupply(entity);
                number.setField();
            }
        }
    }
    
    

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Provider.class, Provider_.SUPPLYS)
                .addExclusion(Supply.class, Supply_.INVENTORY_LIST)
                .getGson();
    }
    
    
    
}
