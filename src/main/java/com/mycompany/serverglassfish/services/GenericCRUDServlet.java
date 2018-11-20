/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.DAO.ExceptionConverter;
import com.mycompany.serverglassfish.DAO.GenericHibernateDAO;
import com.mycompany.serverglassfish.model.BaseModel;
import gson.GsonUtil;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a.zolotarev
 */
public abstract class GenericCRUDServlet<T> extends HttpServlet implements GenericServlet<T> {

    private Class<T> persistentClass;
    private Type listType;

    public GenericCRUDServlet(Class<T> persistentClass, Type listType) {
        this.persistentClass = persistentClass;
        this.listType = listType;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    private String getJson(T p) {
        String json = getGson().toJson(p);
        System.out.print("out = " + json);
        return json;
    }

    private String getJson(List<T> p) {
        String json = getGson().toJson(p);
        System.out.print("out = " + json);
        return json;
    }

    @Override
    public T getTypeFromJson(HttpServletRequest req) throws IOException {
        return new GsonUtil<T>().getEntityFromJson(req, getPersistentClass());
    }

    @Override
    public List<T> getListFromJson(HttpServletRequest req) throws IOException {
        return new GsonUtil<T>().getListFromJson(req, listType);
    }

    protected HttpServletResponse respEncoding(HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml");
        return resp;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        try{
        System.out.println("doGet");
        GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
        if (request.getParameter("id") != null) {
            System.out.println("id!=null " + request.getParameter("id"));
            final T p = (T) dao.get(parseInt(request.getParameter("id")));
            setField(p);
            respEncoding(resp).getWriter().write(getJson(p));
        } else {
            System.out.println("id=null " + getGsonFromList().toJson(dao.getAll()));
            respEncoding(resp).getWriter().write(getGsonFromList().toJson(dao.getAll()));
        }
        dao.closeSession();
        }catch(Exception ex){
            respEncoding(resp).getWriter().write(ExceptionConverter.getSpecialty(ex));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try{
        System.out.println("POST");
        System.out.println("in json = " + req);
        String error = "";
        GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
        List<T> list = getListFromJson(req);
        for (T entity : list) {
            setField(entity);
            error.concat(dao.create(entity));
        }
        dao.closeSession();
        if (error.isEmpty()) {
            respEncoding(resp).getWriter().write(getJson(list));
        } else {
            respEncoding(resp).getWriter().write(error);
        }
        }catch(Exception ex){
            respEncoding(resp).getWriter().write(ExceptionConverter.getSpecialty(ex));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try{
        System.out.println("DELETE");
        String response = new GenericHibernateDAO(getPersistentClass()).delete(Parser.getId(req));
        if (!response.isEmpty()) {
            respEncoding(resp).getWriter().write(response);
        }
                }catch(Exception ex){
            respEncoding(resp).getWriter().write(ExceptionConverter.getSpecialty(ex));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
       try{
           System.out.println("PUT");
        GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
        String error = "";
        List<T> list = new ArrayList();
            list = getListFromJson(req);
            for (T entity : list) {
                setField(entity);
                error.concat(dao.update(entity));
            }
        dao.closeSession();
        System.out.print("out parameter= ");
        //print(p);
        if (error.isEmpty()) {
                respEncoding(resp).getWriter().write(getJson(list));
        } else {
            respEncoding(resp).getWriter().write(error);
        }
       }catch(Exception ex){
            respEncoding(resp).getWriter().write(ExceptionConverter.getSpecialty(ex));
        }
    }

    @Override
    public abstract Gson getGson();

    @Override
    public abstract Gson getGsonFromList();

    @Override
    public void setField(T entity) {
        System.out.print("print Generic");
    }
}
