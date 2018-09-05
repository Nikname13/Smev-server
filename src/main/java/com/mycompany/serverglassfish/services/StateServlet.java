/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;


import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.State;
import gson.GsonUtil;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "StateServlet", urlPatterns = {"/state_servlet"})
public class StateServlet extends GenericCRUDServlet<State> {

    public StateServlet() {
        super(State.class);
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(State.class, "equipmentInventoryList")
                .getGson();
    }
    
    
}
