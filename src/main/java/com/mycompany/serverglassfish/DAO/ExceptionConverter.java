/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.DAO;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author a.zolotarev
 */
public class ExceptionConverter {

    private static String[] getStr(Exception ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString().split("\n");
    }

    public static String getSpecialty(Exception ex) {
        String resp = "";
        for (String s : getStr(ex)) {
            if (s.trim().startsWith("Подробности:")) {
                System.out.println("!_! " + s);
                resp+=s+"\n";
            }
        }
        return (resp.isEmpty()?getCaused(ex):resp);
    }

    public static String getCaused(Exception ex) {
        String resp = "";
        for (String s : getStr(ex)) {
            if (s.trim().startsWith("Caused by:")) {
                //System.out.println("!_! " + s);
                resp+=s+"\n";
            }
        }
        return resp;
    }
}
