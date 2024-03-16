package com.uca.controller;

import com.uca.controller.SessionController;
import com.uca.core.PokemonCore;
import com.uca.core.UserCore;
import com.uca.entity.UserEntity;
import com.uca.entity.PokemonAPI;
import com.uca.entity.PokemonEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.mindrot.jbcrypt.BCrypt;

import spark.Request;
import spark.Response;

public class ConnexionController {

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
    public static boolean checkFirstConnexion(LocalDate date){
        boolean firstConnexion = false;

        LocalDate currentDate = LocalDate.now();
  
        if(date == null || !date.equals(currentDate)){
            firstConnexion = true;
        }

        return firstConnexion;
    }

    public static String handleConnexionForm(Request request, Response response) throws IOException, TemplateException {
        
        SessionController.checkSession(request, response);

        UserEntity User = null;
        
        try{

            String email = request.queryParams("email");
            String password = request.queryParams("password");

            //Take the user from database
            User = UserCore.getUserByEmail(email);

            if(User == null){
                String message = "Votre email est incorrect, veuillez bien le verifier.";
                message = message + " Cela peut aussi etre un probleme interne.";
                return message;
            }


            if(checkPassword(password, User.getPassword()) == false){
                String message = "Mot de passe incorrect.";
                return message;
            }

            LocalDate date = UserCore.getLastConnexionDate(User.getId());

            System.out.println();
            System.out.println("Last Connexion Date: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Is it the first connexion of the day? "+ checkFirstConnexion(date));
            

            if(date == null){
                System.out.println("Date = null");
            }


            if(checkFirstConnexion(date)){
                System.out.println("First connexion of the day");

                PokemonEntity pokemon = new PokemonAPI().getRandomPokemon();

                //Attribution d'un pokemon
                User.addPokemonToCollection(pokemon);   
                
                //Relier le pokemon et son maître
                pokemon.setOwnerId(User.getId());   
    
                //Ajout du pokemon dans la table pokemons
                PokemonCore.addPokemon(pokemon.getId(), pokemon.getName(), pokemon.getLevel(), pokemon.getOwnerId());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        
        // Redirect to the welcome page with the user ID
        int userId = User.getId();

        request.session().attribute("user_id", userId);

        response.redirect("/profile/"+userId);
        
        return null;
    }

    public static String handleLogout(Request request, Response response) {
        // Supprimer l'attribut de session de l'ID de l'utilisateur
        request.session().removeAttribute("user_id");
        
        System.out.println();
        System.out.println("Deconnexion ...");

        // Rediriger vers la page d'accueil ou la page de connexion
        response.redirect("/connexion");
        
        System.out.println("Deconnexion reussie.");
        return null;
    }
}
