/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.DAO;

import java.util.List;
import java.util.Set;

/**
 *
 * @author a.zolotarev
 */
public interface GenericDAO<T,ID> {
    
    boolean create(T entity);
    T get(ID id);
    List<T> getAll();
    boolean update(T entity);
    boolean delete(ID id);
    boolean delete(Set<Integer> entity);
    
}
