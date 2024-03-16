package com.uca.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateException;

import com.uca.core.PokemonCore;
import com.uca.core.UserCore;
import com.uca.entity.PokemonEntity;
import com.uca.entity.UserEntity;
import com.uca.gui.FtlToString;
import com.uca.gui._FreeMarkerInitializer;

import spark.Request;
import spark.Response;

public class LevelUpController {
    public static String handleLevelUpPage(Request req, Response res){

        int userId = Integer.parseInt(req.params(":id"));
        UserEntity user = UserCore.getUserById(userId);
 
        if (req.session().attribute("user_id") == null || user.getId() != (int) req.session().attribute("user_id")) {
            
            String message = "Vous ne pouvez pas acceder a cette page.";
            return message;
            
        }

        try{
            int pokemonId = Integer.parseInt(req.queryParams("pokemonIdLevelUp"));
            int pointValue = Integer.parseInt(req.queryParams("levelIncrease"));

            PokemonEntity pokemon = PokemonCore.getPokemonById(pokemonId);
            
            user.deletePokemonFromCollection(pokemon);

            System.out.println("Level update...");
            System.out.println("Ancien niveau = "+pokemon.getLevel());
            int levelup = pokemon.getLevel() + pointValue;

            boolean updateLevel = PokemonCore.updatePokemonLevel(pokemonId,levelup);
            if(updateLevel){
                pokemon.setLevel(levelup);
                user.addPokemonToCollection(pokemon);
                System.out.println("Niveau mis a jour = "+pokemon.getLevel());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        res.redirect("/profile/" + user.getId());

        return null;
    }

    public static String getConfirmationPageLevelUp(Request request, Response response) throws IOException, TemplateException {
        int userId = Integer.parseInt(request.params(":id"));
        UserEntity user = UserCore.getUserById(userId);
        
        int pokemonId = Integer.parseInt(request.queryParams("pokemonIdLevelUp"));
        PokemonEntity pokemon = PokemonCore.getPokemonById(pokemonId);
    
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("pokemon", pokemon);
    
        Template template = _FreeMarkerInitializer.getContext().getTemplate("levelUp.ftl");
        return FtlToString.templateToString(template, data);
    }
    

}
