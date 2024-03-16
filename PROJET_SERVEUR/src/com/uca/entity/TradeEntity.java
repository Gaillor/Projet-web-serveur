package com.uca.entity;

import java.sql.Timestamp;

import com.uca.core.PokemonCore;
import com.uca.core.UserCore;


public class TradeEntity {
    int id;
    int pokemon1Id;
    int pokemon2Id;
    int user1Id;
    int user2Id;

    public TradeEntity(){

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getPokemon1Id(){
        return pokemon1Id;
    }

    public void setPokemon1Id(int pokemon1Id){
        this.pokemon1Id = pokemon1Id;
    }

    public int getPokemon2Id(){
        return pokemon2Id;
    }

    public void setPokemon2Id(int pokemon2Id){
        this.pokemon2Id = pokemon2Id;
    }

    public int getUser1Id(){
        return user1Id;
    }

    public void setUser1Id(int user1Id){
        this.user1Id = user1Id;
    }

    public int getUser2Id(){
        return user2Id;
    }

    public void setUser2Id(int user2Id){
        this.user2Id = user2Id;
    }

    public boolean isMakeTradePossible(UserEntity u1, UserEntity u2, PokemonEntity p1, PokemonEntity p2){
        if(!UserCore.isUserExists(u1) || !UserCore.isUserExists(u2) || !PokemonCore.isPokemonExists(p1) || !PokemonCore.isPokemonExists(p2)){
            
            System.out.println();
            System.out.println("--------");
            System.out.println("Echange ne peut etre abouti car un des elements non-existant");

            return false;
        }

        if(!u1.hasPokemon(p1) || !u2.hasPokemon(p2)){
            System.out.println();
            System.out.println("---------");
            System.out.println("L'un des 2 utilisateurs ne possede pas le Pokemon.");
            
            return false;
        }

        System.out.println("---------");
        System.out.println("Echange possible!");

        return true;
    }

}
