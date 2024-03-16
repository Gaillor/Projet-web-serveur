package com.uca.gui;

import com.uca.controller.WelcomePageController;

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

import spark.Request;
import spark.Response;

public class WelcomeGUI extends FtlToString{

    public static String getWelcomePage(Request request, Response response) throws IOException, TemplateException {
        return WelcomePageController.getWelcomePage(request, response);
    }


}
