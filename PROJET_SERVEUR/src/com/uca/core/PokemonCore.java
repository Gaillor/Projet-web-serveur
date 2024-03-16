package com.uca.core;

import com.uca.dao.PokemonDAO;
import com.uca.dao.UserDAO;
import com.uca.entity.PokemonEntity;

import java.sql.SQLException;

import java.util.ArrayList;

public class PokemonCore {

    public static ArrayList<PokemonEntity> getAllPokemons() {
        return new PokemonDAO().getAllPokemons();
    }
    
    public static PokemonEntity addPokemon(int id, String name, int level, int ownerId) {
        PokemonEntity pokemon = new PokemonEntity();

        pokemon.setId(id);
        pokemon.setName(name);
        pokemon.setLevel(level);
        pokemon.setOwnerId(ownerId);

        PokemonEntity createdPokemon = new PokemonDAO().create(pokemon);


        return createdPokemon;
    }


    public static PokemonEntity getPokemonById(int pokemonId) {
        return new PokemonDAO().getPokemonById(pokemonId);
    }


    public static boolean isPokemonExists(PokemonEntity pokemon){
        return new PokemonDAO().isPokemonExists(pokemon);
    }

    public static boolean deletePokemon(PokemonEntity pokemon){
        return new PokemonDAO().delete(pokemon);
    }

    public static boolean updatePokemonLevel(int pId, int level)throws SQLException{
        return new PokemonDAO().updatePokemonLevel(pId, level);
    }
}
