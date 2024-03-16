package com.uca.controller;

import com.uca.gui.FtlToString;
import com.uca.core.PokemonCore;
import com.uca.core.TradeCore;
import com.uca.core.UserCore;
import com.uca.dao.UserDAO;
import com.uca.entity.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import com.uca.gui._FreeMarkerInitializer;

import spark.Request;
import spark.Response;

import java.util.ArrayList;

public class TradeController {
    
    public static String getTradePage(Request request, Response response) throws IOException, TemplateException {
        // Get the user ID from the request parameters
        int userId = Integer.parseInt(request.params(":id"));

        UserEntity user = UserCore.getUserById(userId);

        if (request.session().attribute("user_id") == null || user.getId() != (int) request.session().attribute("user_id")) {
            
            String message = "Vous ne pouvez pas acceder a cette page.";
            return message;
            
        }

        ArrayList<UserEntity> users = UserCore.getAllUsers();

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("user1", user);
        data.put("pokemonList", user.getPokemonsCollection());

        users.remove(user);
        
        data.put("otherUsers", users);

        Template template = _FreeMarkerInitializer.getContext().getTemplate("trades/trades.ftl");

        return FtlToString.templateToString(template, data);
    }

    public static String handleTradeFormProfile(Request request, Response response) throws IOException, TemplateException{
        
        UserEntity user1 = new UserEntity();
        PokemonEntity pokemon1 = new PokemonEntity();

        String userIdParam = request.queryParams("userId");
        String pokemonIdParam = request.queryParams("pokemonId");
        
        if (userIdParam != null && pokemonIdParam != null) {
            try {
                int Uid1 = Integer.parseInt(userIdParam);
                int PId1 = Integer.parseInt(pokemonIdParam);
        
                user1 = UserCore.getUserById(Uid1);
                pokemon1 = PokemonCore.getPokemonById(PId1);

            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(user1.getId() != 0){

            request.session().attribute("param1",user1.getId());
            request.session().attribute("param2",pokemon1.getId());

            response.redirect("/trades/"+ user1.getId() + "?param1="+user1.getId()+"&param2="+pokemon1.getId());
        }

        return null;
    }

    public static String makeTradeForm(Request request, Response response) throws IOException, TemplateException{
        
        UserEntity user1 = new UserEntity();
        PokemonEntity pokemon1 = new PokemonEntity();
        UserEntity user2 = new UserEntity();
        PokemonEntity pokemon2 = new PokemonEntity();
    
        try{

            int Uid1 = request.session().attribute("param1");
            int PId1 = request.session().attribute("param2");

            int Uid2 = Integer.parseInt(request.queryParams("selectedUserId"));
            int PId2 = Integer.parseInt(request.queryParams("selectedPokemonId"));

            user1 = UserCore.getUserById(Uid1);
            pokemon1 = PokemonCore.getPokemonById(PId1);

            user2 = UserCore.getUserById(Uid2);
            pokemon2 = PokemonCore.getPokemonById(PId2);
            
            boolean trade = TradeCore.makeTrade(user1,user2,pokemon1,pokemon2);
            
            if(trade == true){
                System.out.println("Trade reussi");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        response.redirect("/profile/"+ user1.getId());

        return null;
    
    }
}

