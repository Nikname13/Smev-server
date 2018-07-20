/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.Type;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a.zolotarev
 * @param <T>
 */
public class GsonUtil<T> {
    
    private Map<String,Class<?>> map;
    
    public GsonUtil() {
        System.out.print("GsonUtil constructor");
        map=new HashMap<String,Class<?>>();
    } 
    
    public GsonUtil addExclusion(Class c, String name){
        //System.out.println("in class= "+c+"in fieldName= "+name);
        //System.out.println("in map size= "+map.size());
        map.put(name,c);
        //System.out.println("out map size= "+map.size());
        for(Map.Entry<String,Class<?>> entry:map.entrySet()){
          //  System.out.println("Class= "+entry.getKey()+" fieldName= "+entry.getValue());
        }
        return this;
    }
    
    public Gson getGson(){
        GsonBuilder g=new GsonBuilder();
        for(Map.Entry<String,Class<?>> entry:map.entrySet()){
            //System.out.println("out Class= "+entry.getKey()+" out fieldName= "+entry.getValue());
            g.setExclusionStrategies(new GsonExclusionStrategy(entry.getValue(),entry.getKey()));
        }
        return g.create();
    }
    
    public T getEntityFromJson(HttpServletRequest req, Class<?> c) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader(); 
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
           return (T) new Gson().fromJson(sb.toString(), c);
    } 
    
    
}
