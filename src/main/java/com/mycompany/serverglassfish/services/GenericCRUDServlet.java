/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.DAO.GenericHibernateDAO;
import gson.GsonUtil;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a.zolotarev
 */
public class GenericCRUDServlet<T> extends HttpServlet implements GenericServlet<T> {
    
    private Class<T> persistentClass;

    public GenericCRUDServlet(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }
    
    public Class<T> getPersistentClass(){
        return persistentClass;
    }
    
    private String getJson(T p){
        String json=getGsonFromEntity().toJson(p);
        System.out.print("out = "+json);
        return json;
    }

    @Override
    public T getTypeFromJson(HttpServletRequest req) throws IOException {
        return new GsonUtil<T>().getEntityFromJson(req, getPersistentClass());
    }
    
    protected HttpServletResponse respEncoding(HttpServletResponse resp){
        resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/xml");
    return resp;
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("doGet");
        
        GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
        if (request.getParameter("id") != null) {
            System.out.println("id!=null " + request.getParameter("id"));
            final T p = (T) dao.get(parseInt(request.getParameter("id")));
            setField(p);
            respEncoding(resp).getWriter().write(getJson(p));
        } else {
            System.out.println("id=null "+getGsonFromList().toJson(dao.getAll()));
            respEncoding(resp).getWriter().write( getGsonFromList().toJson(dao.getAll()));
        }
        dao.closeSession();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("POST");
        System.out.println("in json = "+req);
        GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
        final T p = getTypeFromJson(req);
        setField(p);
        dao.create(p);
        dao.closeSession();
        System.out.print("out parameter= ");
        respEncoding(resp).getWriter().write(getJson(p));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("DELETE");
        if (!new GenericHibernateDAO(getPersistentClass()).delete(Parser.getId(req))) {
            respEncoding(resp).sendError(666, "Could not execute statement " + "Parameter");
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
        System.out.println("PUT");
        GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
        final T p = getTypeFromJson(req);
        setField(p);
        System.out.println("PUT result= "+dao.update(p));
        System.out.print("out parameter= ");
        //print(p);
        respEncoding(resp).getWriter().write(getJson(p));
    }
        
     @Override
    public Gson getGsonFromEntity() {
        return new GsonUtil().getGson();
    }

    @Override
    public Gson getGsonFromList() {
        return new GsonUtil().getGson();
    }

    @Override
    public void setField(T entity) {
        System.out.print("print Generic");
    }

}
