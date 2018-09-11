/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.DAO.GenericHibernateDAO;
import com.mycompany.serverglassfish.DAO.HibernateUtil;
import com.mycompany.serverglassfish.model.FileDump;
import gson.GsonUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author a.zolotarev
 */
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50,
                 location="C:\\") 
public class GenericFileServlet<T> extends HttpServlet implements GenericFile {
    
    private Class<T> persistentClass;
    private String entityDir;
    private SingularAttribute id_;
    private static String dir="uploadDir";

    public GenericFileServlet(Class<T> persistentClass, String entity, SingularAttribute id) {
        this.persistentClass = persistentClass;
        this.entityDir=entity;
        this.id_=id;
    }
    
    public Class<T> getPersistentClass(){
        return persistentClass;
    }
    
    @Override
    public void setLink(FileDump fileD, String type, int id){
        
    }
    
    @Override
    public String getNameField(String type){
        return "";
    }
    
    private void doGetList(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
        
        String type=req.getParameter("type");
//        List<FileDump> fileDumpList=dao.getList(Integer.valueOf(req.getParameter("id")), id_, getNameField(type));
//        for(FileDump file:fileDumpList){
//            System.out.println(file.getId());
//        }
//        resp.getWriter().write(getGson().toJson(fileDumpList));
    }
    
    private void doGetFile(HttpServletRequest req, HttpServletResponse resp) throws IOException{
       String filePath ="C:"+File.separator+dir+File.separator+entityDir+File.separator+req.getParameter("id")+File.separator+req.getParameter("type")+File.separator+req.getParameter("path");
       System.out.println(filePath);
       File file=new File(filePath);
       if(file.exists()){
           System.out.println("файл досутпен");
           byte[] bytes=Files.readAllBytes(file.toPath());
           String lenght=String.valueOf(bytes.length);
           resp.setHeader("Content-disposition","attachment; filename="+file.getName());
           resp.setHeader("Content-Length", lenght);
           resp.getOutputStream().write(bytes);
           resp.flushBuffer();
       }else{
           System.out.println("файла не существет либо путь неверен");
       }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("path")==null){
            HibernateUtil.getSessionFactory();//создание сессии перед вызовом dao.getList, иначе Department_id=null
            doGetList(req, resp);
        }else{
            doGetFile(req, resp);
        } 
    }
    
    
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {      
     System.out.println("Upload start "+File.separator);
        String type=req.getParameter("type");
        String id=req.getParameter("id");
        String savePath=dir+File.separator+entityDir+File.separator+id+File.separator+type;
        System.out.println(savePath+" paramId= "+id);
        File fileSaveDir=new File("C:\\"+savePath);
        if(!fileSaveDir.exists()){
            fileSaveDir.mkdirs();
        }
        List<FileDump> fileDumpList=new ArrayList<>();
        GenericHibernateDAO dao=new GenericHibernateDAO(FileDump.class);
        for(Part part : req.getParts()){
            System.out.println("---part---");
            FileDump fileD=new FileDump();
            String fileName=extractFileName(part);
            String extension = fileName.substring(fileName.length()-4, fileName.length());
            fileD.setName(fileName);
            fileD.setPath(UUID.randomUUID().toString().concat(extension));
            setLink(fileD, type,Integer.valueOf(id));
            dao.create(fileD);
            fileDumpList.add(fileD);
            System.out.println("fileName="+fileName+" - "+extension);
            part.write(savePath+File.separator+fileD.getPath());
        }
        dao.closeSession();
        System.out.println(getGson().toJson(fileDumpList));
        resp.getWriter().write(getGson().toJson(fileDumpList));
    }
    
    private String extractFileName(Part part){
        String contentDisp=part.getHeader("content-disposition");
        System.out.print(contentDisp);
        String[] items=contentDisp.split(";");
        for(String s : items){
            System.out.println(s);
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }   
    
    @Override
    public Gson getGson(){
        return new GsonUtil().getGson();          
    }
}
