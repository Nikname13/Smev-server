/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.DAO.GenericHibernateDAO;
import com.mycompany.serverglassfish.DAO.HibernateUtil;
import com.mycompany.serverglassfish.DAO.GenericType;
import gson.GsonUtil;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a.zolotarev
 */
public abstract class GenericManyToManyServlet<T> extends GenericCRUDServlet<T> implements GenericManyToMany {

    public GenericManyToManyServlet(Class<T> persistentClass,Type listType) {
        super(persistentClass, listType);
    }

    @Override
    public GenericType getCriterion(String criterion) {
        return new GenericType<Integer>(parseInt(criterion));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");
        if (type==null) {
            super.doGet(req,resp);
        } else {
            GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
            if (req.getParameter("id") != null) {
                HibernateUtil.getSessionFactory();//создание сессии перед вызовом dao.getList, иначе getId()=null
                List<T> listResp = dao.getList(getCriterion(req.getParameter("id")), getSearchField(type), getJoinField(type));
                respEncoding(resp).getWriter().write(getGson().toJson(listResp));
            } else {
                respEncoding(resp).getWriter().write(getGsonFromList().toJson(dao.getAll()));
            }
            dao.closeSession();
        }
    }

    @Override
    public abstract SingularAttribute getSearchField(String type);

    @Override
    public abstract String getJoinField(String type);  
}
