/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;


import com.google.gson.Gson;
import com.mycompany.serverglassfish.model.Department;
import com.mycompany.serverglassfish.model.Department_;
import com.mycompany.serverglassfish.model.FileDump;
import com.mycompany.serverglassfish.model.FileDump_;
import gson.GsonUtil;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "DepartmentLoadFileServlet", urlPatterns = {"/load_department_servlet"})
public class DepartmentLoadFileServlet extends GenericFileServlet<FileDump>{

    public DepartmentLoadFileServlet() {
        super(FileDump.class, "department", Department_.id);
    }

    @Override
    public void setLink(FileDump fileD, String type, int id) {
        Department department = new Department();
        department.setId(id);
        switch (type) {
            case ("doc"):
                fileD.addDoc(department);
                break;
            case ("config"):
                fileD.addConfig(department);
                break;
            case ("photo"):
                fileD.addPhoto(department);
        }
    }

    @Override
    public String getNameField(String type) {
        switch (type) {
            case ("doc"):
                return new FileDump().getNameDepartmentDocList();
            case ("config"):
                return new FileDump().getNameDepartmentConfigList();
            case ("photo"):
                return new FileDump().getNameDepartmentPhotoList();
        }
        return "";
    }
    
    

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_DOC_FILES)
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_CONFIG_FILES)
                .addExclusion(FileDump.class, FileDump_.DEPARTMENT_PHOTO_FILES)
                .getGson();
    }

   
    
    

    
}
