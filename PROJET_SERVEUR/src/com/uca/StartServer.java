package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;
import com.uca.controller.SessionController;

import static spark.Spark.*;

public class StartServer {

    public static void main(String[] args) {
        //Configure Spark
        staticFiles.location("/static/");
        port(8081);


        _Initializer.Init();

        //Defining our routes
        get("/users", (req, res) -> {
            return UserGUI.getAllUsersInfo(req,res);
        });
        
        get("/pokemons", (req, res) -> {
            return PokemonGUI.getAllPokemons();
        });

        get("/register", (req, res) -> {
            return RegisterGUI.getRegisterPage();
        });

        // Handle the registration form submission
        post("/register", (request, response) -> {
            return RegisterGUI.handleRegisterForm(request, response);
        });

        get("/welcome/:id", (request, response) -> {
            return WelcomeGUI.getWelcomePage(request, response);
        });

        get("/connexion", (request, response) -> {
            return ConnexionGUI.getConnexionPage();
        });

        post("/connexion", (request, response) -> {
            return ConnexionGUI.handleConnexionForm(request, response);
        });

        get("/logout", (request, response) -> {
            return ConnexionGUI.handleLogout(request, response);
        });

        get("/profile/:id", (request, response) -> {
            return ProfileGUI.getProfilePage(request, response);
        });

        post("/profile/:id/trade", (request, response) -> {
            return TradeGUI.handleTradeFormProfile(request, response);
        });
    
        get("/trades/:id", (request, response) -> {
            return TradeGUI.getTradePage(request, response);
        });

        post("/trades/:id", (request, response) -> {
            return TradeGUI.makeTradeForm(request, response);
        });

        post("/profile/:id/levelup", (request, response) -> {
            return LevelUpGUI.handleLevelUpPage(request, response);
        });

            
    }
}