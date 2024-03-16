package com.uca.dao;

import java.sql.*;

public class _Initializer {

    public static void Init(){
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;

            //Init articles table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id int primary key auto_increment, firstname varchar(100), lastname varchar(100), username varchar(100), password varchar(200) not null, email varchar(100) not null); ");
            statement.executeUpdate();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS pokemons (table_id int primary key auto_increment, id int , name varchar(100), level int, owner_id int, foreign key (owner_id) references users(id)); ");
            statement.executeUpdate();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS trades (id int primary key auto_increment, user1_id int, user2_id int, pokemon1_id int, pokemon2_id int, foreign key (user1_id) references users(id), foreign key (user2_id) references users(id), foreign key (pokemon1_id) references pokemons(table_id), foreign key (pokemon2_id) references pokemons(table_id)); ");
            statement.executeUpdate();
            
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS levelUps (id int primary key auto_increment, user_id int, pokemon_id int, foreign key (user_id) references users(id), foreign key (pokemon_id) references pokemons(id)); ");
            statement.executeUpdate();
        
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS connexions (user_id int, last_connexion DATE, foreign key (user_id) references users(id)); ");
            statement.executeUpdate();
        
            
            //Todo Remove me !
            /*
            statement = connection.prepareStatement("INSERT INTO users(firstname, lastname, username, email, password) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, "Theodore");
            statement.setString(2, "Muillerez");
            statement.setString(3, "gaogno");
            statement.setString(4, "blabla@gmail.com");
            statement.setString(5, "addhfhfhf155161");

            statement.executeUpdate();
            */

        } catch (Exception e){
            System.out.println(e.toString());
            throw new RuntimeException("could not create database !");
        }
    }
}
