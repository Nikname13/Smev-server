/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services.FileServices;

import com.mycompany.serverglassfish.model.Equipment;
import com.mycompany.serverglassfish.model.EquipmentInventory;
import com.mycompany.serverglassfish.model.EquipmentInventory_;
import com.mycompany.serverglassfish.model.FileDump;
import com.mycompany.serverglassfish.model.FileDump_;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "EquipmentInventoryLoadFileServlet", urlPatterns = {"/load_equipmentInventory_servlet"})
public class EquipmentInventoryLoadFileServlet extends BaseLoadFileServlet {

    public EquipmentInventoryLoadFileServlet() {
        super("equipmentInventory", EquipmentInventory_.id);
    }

    @Override
    public void setLink(FileDump fileD, String type, int id) {
        EquipmentInventory equipment=new EquipmentInventory();
        equipment.setId(id);
        fileD.addPhoto(equipment);
    }

    @Override
    public String getNameField(String type) {
                return FileDump_.EQUIPMENT_INVENT_PHOTO_FILES;
    }
    
}
