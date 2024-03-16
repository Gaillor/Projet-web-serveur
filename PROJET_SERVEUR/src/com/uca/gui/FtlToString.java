package com.uca.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class FtlToString {

    public static String templateToString(Template template, Map<String, Object> data) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        template.process(data, stringWriter);
        return stringWriter.toString();
    }

}
