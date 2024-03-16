package com.uca.controller;

import spark.Request;
import spark.Response;

public class SessionController {

    public static void checkSession(Request req, Response res){
        Integer id = req.session().attribute("user_id");
            
        //S'il y a déjà ou pas un utilisateur connecté
        if(id != null){
            req.session().removeAttribute("user_id");
            System.out.println("-------------");
            System.out.println("Deconnexion de l'utilisateur actuel...");
            System.out.println("Connexion du nouvel utilisateur...");
        }
        else{
            System.out.println("-------------");
            System.out.println("Connexion....");
        }
    }

}
