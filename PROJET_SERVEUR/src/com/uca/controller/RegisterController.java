package com.uca.controller;

import com.uca.dao.UserDAO;
import com.uca.entity.UserEntity;
import com.uca.entity.PokemonAPI;
import com.uca.entity.PokemonEntity;
import com.uca.controller.SessionController;
import com.uca.core.PokemonCore;
import com.uca.core.UserCore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.StringWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import spark.Request;
import spark.Response;


public class RegisterController {

    public static boolean validateRegistration(String email){
        boolean emptyProfile = true;    //le profil  n'existe pas encore
                                        //dans la base de données
        UserEntity U = new UserEntity(); 
        U = UserCore.getUserByEmail(email);


        if(U != null){
            emptyProfile = false;
        }

        return emptyProfile;
    }


    public static String handleRegisterForm(Request request, Response response) throws IOException, TemplateException {
        
        SessionController.checkSession(request, response);

        UserEntity User = null;
        
        try{
        String firstname = request.queryParams("firstname");
        String lastname = request.queryParams("lastname");
        String username = request.queryParams("username");
        String email = request.queryParams("email");
        String password = request.queryParams("password");


        //System.out.println(validateRegistration(email));


        if(validateRegistration(email)){
            
            PokemonEntity pokemon = new PokemonAPI().getRandomPokemon();
            
            // Insert the new user into the database
            User = UserCore.addUser(firstname, lastname, username, email, password);
            User.addPokemonToCollection(pokemon);   //Attribution d'un pokemon

            pokemon.setOwnerId(User.getId());   //Relier le pokemon et son maître

            //Ajout du pokemon dans la table pokemons
            PokemonCore.addPokemon(pokemon.getId(), pokemon.getName(), pokemon.getLevel(), pokemon.getOwnerId());

        }else{

            String message = "Adresse mail deja utilise,veuillez saisir un autre.";
            return message;
        }


        }catch(Exception e){
            e.printStackTrace();
        }
        // Redirect to the welcome page with the user ID
        int userId = User.getId();

        // Premiere connexion
        LocalDate currentDate = LocalDate.now();
        UserCore.updateLastConnexionDate(currentDate, userId);


        System.out.println();
        System.out.println("Registration/first connexion date: " + currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        request.session().attribute("user_id", userId);

        response.redirect("/welcome/"+userId);

        return null;
    }
}
