package gson;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 *
 * @author a.zolotarev
 */
public class GsonExclusionStrategy implements ExclusionStrategy {
    
    private Class<?> model;
    private String fieldName;

    public GsonExclusionStrategy(Class<?> c,String name) {
       // System.out.print("GsonExclusionStrategy constructor");
        model=c;
        fieldName=name;
    } 
    
    @Override
    public boolean shouldSkipField(FieldAttributes fa) {
            return (fa.getDeclaringClass()==model && fa.getName().equals(fieldName));
    }

    @Override
    public boolean shouldSkipClass(Class<?> type) {
        return false;
    }
    
}
