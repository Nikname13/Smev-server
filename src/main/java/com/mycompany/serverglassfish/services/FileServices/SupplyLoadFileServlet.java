/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services.FileServices;

import com.mycompany.serverglassfish.model.Equipment;
import com.mycompany.serverglassfish.model.Equipment_;
import com.mycompany.serverglassfish.model.FileDump;
import com.mycompany.serverglassfish.model.FileDump_;
import com.mycompany.serverglassfish.model.Supply;
import com.mycompany.serverglassfish.model.Supply_;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "SupplyLoadFileServlet", urlPatterns = {"/load_supply_servlet"})
public class SupplyLoadFileServlet extends BaseLoadFileServlet{

    public SupplyLoadFileServlet() {
        super("supply", Supply_.id);
    }

    @Override
    public void setLink(FileDump fileD, String type, int id) {
        Supply supply=new Supply();
        supply.setId(id);
        fileD.addDoc(supply);
    }

    @Override
    public String getNameField(String type) {
                return FileDump_.SUPPLY_DOC_FILES;
    }
}
