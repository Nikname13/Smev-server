/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverglassfish.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.serverglassfish.model.Post;
import com.mycompany.serverglassfish.model.Post_;
import com.mycompany.serverglassfish.model.Purchase;
import gson.GsonUtil;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author a.zolotarev
 */
@WebServlet(name = "PostServlet", urlPatterns = {"/post_servlet"})
public class PostServlet extends GenericCRUDServlet<Post> {

    public PostServlet() {
        super(Post.class,new TypeToken<ArrayList<Post>>(){}.getType());
    }

    @Override
    public Gson getGsonFromList() {
        return getGson();
    }

    @Override
    public Gson getGson() {
        return new GsonUtil()
                .addExclusion(Post.class, Post_.WORKER_LIST)
                .getGson();
    }

}
