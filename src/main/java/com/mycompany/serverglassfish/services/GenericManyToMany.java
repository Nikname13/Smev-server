/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author a.zolotarev
 */
public interface GenericManyToMany {
    
     String getNameField(String type);//переопределяемы метод. получение наименования поля, по которому связаны таблицы, для поиска
 Gson getGson();//переопределяемый метод. исключение полей из Gson
 SingularAttribute getId();//переопределяемый метод. получение id по которуму происходит выборка
}
