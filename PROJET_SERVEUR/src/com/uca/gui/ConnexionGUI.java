package com.uca.gui;

import com.uca.controller.ConnexionController;
import com.uca.gui.FtlToString;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import spark.Request;
import spark.Response;

public class ConnexionGUI extends FtlToString{
    public static String getConnexionPage() throws IOException, TemplateException {
        Template template = _FreeMarkerInitializer.getContext().getTemplate("users/connexion.ftl");
        return FtlToString.templateToString(template, new HashMap<String, Object>());
    }

    public static String handleConnexionForm(Request req, Response res) throws IOException, TemplateException{
        return ConnexionController.handleConnexionForm(req, res);
    }

    public static String handleLogout(Request req, Response res){
        return ConnexionController.handleLogout(req, res);
    }
}
