/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services.FileServices;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.FileDump;
import com.mycompany.serverglassfish.model.FileDump_;
import gson.GsonUtil;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author a.zolotarev
 */
public abstract class BaseLoadFileServlet extends GenericFileServlet<FileDump>{
    
    public BaseLoadFileServlet( String entity, SingularAttribute id) {
        super(FileDump.class, entity, id);
    }
    
     @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_DOC_FILES)
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_CONFIG_FILES)
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_PHOTO_FILES)
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_AVATAR)
                
                .addExclusion(FileDump.class, FileDump_.EQUIPMENT_CONFIG_FILES)
                
                .addExclusion(FileDump.class, FileDump_.EQUIPMENT_INVENT_PHOTO_FILES)
                .addExclusion(FileDump.class, FileDump_.EQUIPMENT_AVATAR)
                
                .addExclusion(FileDump.class, FileDump_.SUPPLY_DOC_FILES)
                .getGson();
    }
}
