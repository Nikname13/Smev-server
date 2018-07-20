/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Purchase;
import gson.GsonUtil;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "PurchaseServlet", urlPatterns = {"/purchase_servlet"})
public class PurchaseServlet extends GenericCRUDServlet<Purchase> {
    
    public PurchaseServlet() {
        super(Purchase.class);
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil()
                .addExclusion(Purchase.class,"departmentPurchase")
                .getGson();
    }

    @Override
    public Gson getGsonFromEntity() {
        return new GsonUtil()
                .addExclusion(Department.class, "area")
                .addExclusion(Department.class, "purchaseListDepartment")
                .addExclusion(Department.class, "locationsListDepartment")
                .addExclusion(Department.class, "workersListDepartmnet")
                .addExclusion(Department.class, "equipmentList")
                .getGson();
    }
    
    
}
