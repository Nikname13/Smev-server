/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.DAO.GenericType;
import com.mycompany.serverglassfish.model.FileDump;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a.zolotarev
 */
public interface GenericFile {
    
 void setLink(FileDump fileD, String type, int id);//переопределяемый метод. добавляет ссылку на сущность
 String getNameField(String type);//переопределяемы метод. получение наименования поля, по которому связаны таблицы, для поиска
 Gson getGson();//переопределяемый метод. исключение полей из Gson
 GenericType getCriterion(String criterion);
}
