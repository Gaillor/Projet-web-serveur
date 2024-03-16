package com.uca.gui;

import com.uca.entity.UserEntity;
import com.uca.gui.FtlToString;
import com.uca.core.UserCore;
import com.uca.controller.RegisterController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import spark.Request;
import spark.Response;

public class RegisterGUI extends FtlToString {

    public static String handleRegisterForm(Request request, Response response) throws IOException, TemplateException {
        return RegisterController.handleRegisterForm(request, response);
    }

    public static String getRegisterPage() throws IOException, TemplateException {
        Template template = _FreeMarkerInitializer.getContext().getTemplate("register/register.ftl");
        return FtlToString.templateToString(template, new HashMap<String, Object>());
    }
    
}
