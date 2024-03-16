package com.uca.dao;

import com.uca.core.PokemonCore;
import com.uca.dao.UserDAO;
import com.uca.entity.*;

import java.sql.*;
import java.util.ArrayList;

public class TradeDAO extends _Generic<TradeEntity> {
    
    public boolean makeTrade(UserEntity u1, UserEntity u2, PokemonEntity p1, PokemonEntity p2){
        boolean trade = new TradeEntity().isMakeTradePossible(u1,u2,p1,p2);

        if(!trade){
            return false;
        }

        try{

            PokemonCore.deletePokemon(p1);
            PokemonCore.deletePokemon(p2);

            p1.setOwnerId(u2.getId());
            p2.setOwnerId(u1.getId());
    
            u1.deletePokemonFromCollection(p1);
            u1.addPokemonToCollection(p2);
            u2.deletePokemonFromCollection(p2);
            u2.addPokemonToCollection(p1);

            PokemonCore.addPokemon(p1.getId(), p1.getName(), p1.getLevel(), p1.getOwnerId());
            PokemonCore.addPokemon(p2.getId(), p2.getName(), p2.getLevel(), p2.getOwnerId());


        }catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public TradeEntity create(TradeEntity obj){
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO trades(user1_id, user2_id, pokemon1_id, pokemon2_id) VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, obj.getUser1Id());
            statement.setInt(2, obj.getUser2Id());
            statement.setInt(3, obj.getPokemon1Id());
            statement.setInt(4, obj.getPokemon2Id());
            
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
        
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                obj.setId(id);

            } else {
                throw new SQLException("Creating Trade failed, no ID obtained.");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public boolean delete(TradeEntity obj) {
        //TODO !
        return false;
    }

}