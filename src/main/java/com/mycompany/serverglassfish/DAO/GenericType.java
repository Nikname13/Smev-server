/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.DAO;

/**
 *
 * @author a.zolotarev
 */
public class GenericType<T> {
    private T value;

    public GenericType(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    } 
}
