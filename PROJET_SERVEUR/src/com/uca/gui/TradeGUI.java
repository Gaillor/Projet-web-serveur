package com.uca.gui;

import com.uca.gui.FtlToString;
import com.uca.core.TradeCore;
import com.uca.dao.UserDAO;
import com.uca.entity.*;
import com.uca.controller.TradeController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import spark.Request;
import spark.Response;

public class TradeGUI {
    
    public static String getTradePage(Request request, Response response) throws IOException, TemplateException {
        return TradeController.getTradePage(request, response);
    }

    public static String handleTradeFormProfile(Request request, Response response) throws IOException, TemplateException{
        return TradeController.handleTradeFormProfile(request, response);
    }

    public static String makeTradeForm(Request request, Response response) throws IOException, TemplateException{
        return TradeController.makeTradeForm(request, response);
    }
}
