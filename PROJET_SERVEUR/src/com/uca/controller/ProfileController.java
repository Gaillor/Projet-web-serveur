package com.uca.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateException;

import com.uca.core.UserCore;
import com.uca.entity.UserEntity;
import com.uca.gui.FtlToString;
import com.uca.gui._FreeMarkerInitializer;

import spark.Request;
import spark.Response;


public class ProfileController{

    public static String getProfilePage(Request request, Response response) throws IOException, TemplateException {
        // Get the user ID from the request parameters
        int userId = Integer.parseInt(request.params(":id")); 
        UserEntity user = UserCore.getUserById(userId);

        if (request.session().attribute("user_id") == null || user.getId() != (int) request.session().attribute("user_id")) {
            String message = "Vous ne pouvez pas acceder a cette page.";
            return message;
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", user);
        data.put("pokemons", user.getPokemonsCollection());

        Template template = _FreeMarkerInitializer.getContext().getTemplate("users/profile.ftl");

        return FtlToString.templateToString(template, data);
    }


}