package com.uca.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.uca.controller.LevelUpController;

import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import spark.Request;
import spark.Response;

public class LevelUpGUI {
    public static String handleLevelUpPage(Request request, Response response){
        return LevelUpController.handleLevelUpPage(request, response);
    }

    public static String getConfirmationPageLevelUp(Request request, Response response)throws IOException, TemplateException {
        return LevelUpGUI.getConfirmationPageLevelUp(request,response);
    }
}
