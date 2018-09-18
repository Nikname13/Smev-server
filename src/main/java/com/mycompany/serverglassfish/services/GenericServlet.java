/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a.zolotarev
 */
public interface GenericServlet<T> {
    
    Gson getGson();//переопределяемый метод. исключение полей из Gson для одной сущности
    Gson getGsonFromList();//переопределяемый метод. исключение полей из Gson для листа
    void setField (T entity);//переопределяемый метод. добавление ссылки на сущность
    T getTypeFromJson(HttpServletRequest req) throws IOException;//из json в сущность
    List<T> getListFromJson(HttpServletRequest req) throws IOException;
}
