package com.uca.gui;

import com.uca.gui.FtlToString;
import com.uca.controller.ConnexionController;
import com.uca.core.UserCore;
import com.uca.dao.UserDAO;
import com.uca.entity.UserEntity;
import com.uca.controller.ProfileController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import spark.Request;
import spark.Response;

public class ProfileGUI extends FtlToString{
    
    public static String getProfilePage(Request request, Response response) throws IOException, TemplateException {
        return ProfileController.getProfilePage(request, response);
    }
}