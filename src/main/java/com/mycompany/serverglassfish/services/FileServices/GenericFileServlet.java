/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services.FileServices;

import com.google.gson.Gson;
import com.mycompany.serverglassfish.DAO.ExceptionConverter;
import com.mycompany.serverglassfish.DAO.GenericHibernateDAO;
import com.mycompany.serverglassfish.DAO.GenericType;
import com.mycompany.serverglassfish.DAO.HibernateUtil;
import com.mycompany.serverglassfish.model.FileDump;
import com.mycompany.serverglassfish.services.Parser;
import gson.GsonUtil;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author a.zolotarev
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50,
        location = "C:\\")
public abstract class GenericFileServlet<T> extends HttpServlet implements GenericFile {

    private Class<T> persistentClass;
    private String entityDir;
    private SingularAttribute id_;
    private static String dir = "smevMV";
    private static final Logger LOG = LoggerFactory.getLogger(GenericFileServlet.class);

    public GenericFileServlet(Class<T> persistentClass, String entity, SingularAttribute id) {
        this.persistentClass = persistentClass;
        this.entityDir = entity;
        this.id_ = id;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public abstract void setLink(FileDump fileD, String type, int id);

    @Override
    public abstract String getNameField(String type);

    private void doGetList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
        try { 
            String type = req.getParameter("type");
            String reqUpdate =req.getParameter("lastUpdate");
            LocalDateTime lastUpdate=LocalDateTime.MIN;
            List<FileDump> fileDumpList = dao.getList(getCriterion(req.getParameter("id")), id_, getNameField(type));
            if (fileDumpList != null && fileDumpList.size()!=0) {
                lastUpdate=fileDumpList.get(0).getLastUpdate();
                for (FileDump file : fileDumpList) {
                    System.out.println(file.getId());
                    if(file.getLastUpdate().isAfter(lastUpdate)){
                        lastUpdate=file.getLastUpdate();
                    }
                }
            }
            
//            if(reqUpdate!=null){
//                if(LocalDateTime.parse(reqUpdate).compareTo(lastUpdate)==-1){
//            respEncoding(resp).getWriter().write(getGson().toJson(fileDumpList));
//                }else{
//                resp.sendError(HttpServletResponse.SC_NOT_MODIFIED);
//            }
//            }else{
                respEncoding(resp).getWriter().write(getGson().toJson(fileDumpList));
//            }
                  dao.closeSession();
        } catch (Exception ex) {
            respEncoding(resp).getWriter().write(ExceptionConverter.getSpecialty(ex));
        }

    }

    private void doGetFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String filePath = "C:" + File.separator + dir + File.separator + entityDir + File.separator + req.getParameter("id") + File.separator + req.getParameter("type") + File.separator + req.getParameter("path");
            System.out.println(filePath);
            File file = new File(filePath);
            if (file.exists()) {
                System.out.println("файл досутпен");
                byte[] bytes = Files.readAllBytes(file.toPath());
                String lenght = String.valueOf(bytes.length);
                resp.setHeader("Content-disposition", "attachment; filename=" + file.getName());
                resp.setHeader("Content-Length", lenght);
                resp.getOutputStream().write(bytes);
                resp.flushBuffer();
            } else {
                System.out.println("файла не существет либо путь неверен");
            }
        } catch (Exception ex) {
            respEncoding(resp).getWriter().write(ExceptionConverter.getSpecialty(ex));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("path") == null) {
            HibernateUtil.getSessionFactory();//создание сессии перед вызовом dao.getList, иначе Department_id=null
            doGetList(req, resp);
        } else {
            doGetFile(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {   
        try {
            String type = req.getParameter("type");
            String id = req.getParameter("id");
            String savePath = dir + File.separator + entityDir + File.separator + id + File.separator + type;
            LOG.info("File path "+savePath + " paramId= " + id);
            File fileSaveDir = new File("C:"+File.separator+savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            List<FileDump> fileDumpList = new ArrayList<>();
            GenericHibernateDAO dao = new GenericHibernateDAO(FileDump.class);
             LOG.info("Parts "+req.getParts().size());
            for (Part part : req.getParts()) {
                LOG.info("---part---");
                FileDump fileD = new FileDump();
                String fileName = extractFileName(part);
                String extension = fileName.substring(fileName.lastIndexOf("."));
                fileD.setName(fileName);
                fileD.setPath(UUID.randomUUID().toString().concat(extension));
                setLink(fileD, type, Integer.valueOf(id));
                dao.create(fileD);
                fileDumpList.add(fileD);
                LOG.info("fileName= " + fileName + " - " + extension);
                part.write("C:"+File.separator+savePath + File.separator + fileD.getPath());
            }
            dao.closeSession();
            System.out.println(getGson().toJson(fileDumpList));
            respEncoding(resp).getWriter().write(getGson().toJson(fileDumpList));
        } catch (Exception ex) {
            LOG.error("Error load file "+ex);
            respEncoding(resp).getWriter().write(ExceptionConverter.getSpecialty(ex));
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        LOG.info(contentDisp);
        String[] items = contentDisp.split(";");
        for (String s : items) {
            System.out.println(s);
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String responseError = "";
            GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
            Set<Integer> idList = Parser.getId(req);
            for (int id : idList) {
                FileDump deleteFileModel = (FileDump) dao.get(id);
                String filePath = "C:"
                        + File.separator
                        + dir
                        + File.separator + entityDir
                        + File.separator + req.getParameter("idEntity")
                        + File.separator + req.getParameter("type")
                        + File.separator + deleteFileModel.getPath();
                System.out.println(filePath);
                responseError = new GenericHibernateDAO(getPersistentClass()).delete(id);
                File file = new File(filePath);
                if (!responseError.isEmpty()) {
                    respEncoding(resp).getWriter().write(responseError);
                } else {
                    if (file.exists()) {
                        Files.delete(file.toPath());
                    } else {
                        System.out.println("файла не существет либо путь неверен");
                    }
                }
            }
        } catch (Exception ex) {
            respEncoding(resp).getWriter().write(ExceptionConverter.getSpecialty(ex));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            GenericHibernateDAO dao = new GenericHibernateDAO(getPersistentClass());
            String type = req.getParameter("type");
            String id = req.getParameter("id");
            if (id != null && type != null) {
                FileDump entity = (FileDump) getTypeFromJson(req);
                setLink(entity, type, Integer.valueOf(id));
                String error = dao.update(entity);
                dao.closeSession();
                if (error.isEmpty()) {
                    respEncoding(resp).getWriter().write(getGson().toJson(entity));
                } else {
                    respEncoding(resp).getWriter().write(error);
                }
            }
        } catch (Exception ex) {
            respEncoding(resp).getWriter().write(ExceptionConverter.getSpecialty(ex));
        }
    }

    private T getTypeFromJson(HttpServletRequest req) throws IOException {
        return new GsonUtil<T>().getEntityFromJson(req, getPersistentClass());
    }

    @Override
    public Gson getGson() {
        return new GsonUtil().getGson();
    }

    @Override
    public GenericType getCriterion(String criterion) {
        return new GenericType<Integer>(parseInt(criterion));
    }

    protected HttpServletResponse respEncoding(HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml");
        return resp;
    }
}
