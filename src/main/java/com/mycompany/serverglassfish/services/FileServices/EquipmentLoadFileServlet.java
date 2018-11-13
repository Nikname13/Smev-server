/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services.FileServices;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.Equipment;
import com.mycompany.serverglassfish.model.Equipment_;
import com.mycompany.serverglassfish.model.FileDump;
import com.mycompany.serverglassfish.model.FileDump_;
import gson.GsonUtil;

import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "EquipmentLoadFileServlet", urlPatterns = {"/load_equipment_servlet"})
public class EquipmentLoadFileServlet extends BaseLoadFileServlet{

    public EquipmentLoadFileServlet() {
        super("equipment", Equipment_.id);
    }

    @Override
    public void setLink(FileDump fileD, String type, int id) {
        Equipment equipment=new Equipment();
        equipment.setId(id);
        fileD.addConfig(equipment);
    }

    @Override
    public String getNameField(String type) {
               switch (type) {
            case ("config"):
                return FileDump_.EQUIPMENT_CONFIG_FILES;
        }
        return "";
    }
}
