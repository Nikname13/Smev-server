package com.mycompany.serverglassfish.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a.zolotarev
 */
public class Parser {

    public static Set getId(HttpServletRequest req) {
        String[] s = req.getParameterValues("id");
        Set<Integer> id = new HashSet<>();
        for (String item : s) {
            System.out.println(item);
            id.add(Integer.parseInt(item));
        }
        return id;
   }
   

}
