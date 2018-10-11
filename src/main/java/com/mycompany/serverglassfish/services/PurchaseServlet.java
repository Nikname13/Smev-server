/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Department_;
import com.mycompany.serverglassfish.model.Purchase;
import com.mycompany.serverglassfish.model.Purchase_;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "PurchaseServlet", urlPatterns = {"/purchase_servlet"})
public class PurchaseServlet extends GenericCRUDServlet<Purchase> {
    
    public PurchaseServlet() {
        super(Purchase.class,new TypeToken<ArrayList<Purchase>>(){}.getType());
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(Purchase.class, Purchase_.DEPARTMENT_PURCHASE)
                .getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Department.class, Department_.AREA)
                .addExclusion(Department.class, Department_.PURCHASE_LIST_DEPARTMENT)
                .addExclusion(Department.class, Department_.WORKERS_LIST_DEPARTMNET)
                .addExclusion(Department.class, Department_.EQUIPMENT_LIST)
                .getGson();
    }
    
    
}
