package com.uca.gui;

import com.uca.core.UserCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;

public class UserGUI {

    public static String getAllUsers() throws IOException, TemplateException {
        
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("users", UserCore.getAllUsers());

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/users.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }


    public static String getAllUsersInfo(Request req, Response res)throws IOException, TemplateException{
        Integer id = req.session().attribute("user_id");

        if(id == null){
            return "Veuillez d'abord vous connecter ou creer un compte";
        }
        
        return getAllUsers();
    }
}
