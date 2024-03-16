package com.uca.entity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.uca.entity.PokemonEntity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Random;

public class PokemonAPI {

    private static final String API_URL = "https://pokeapi.co/api/v2/pokemon-species/";
    private static final int MAX_POKEMON_ID = 1008;
    private static final Random RANDOM = new Random();
    private final OkHttpClient client = new OkHttpClient();

    public static PokemonEntity getPokeFromJson(String json){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        int id = jsonObject.getAsJsonPrimitive("id").getAsInt();
        String name = jsonObject.getAsJsonPrimitive("name").getAsString();

        PokemonEntity pokemon = new PokemonEntity();

        pokemon.setId(id);
        pokemon.setName(name);
        pokemon.setLevel(1);    //Level initial

        return pokemon;
    }

    public PokemonEntity getRandomPokemon() throws IOException {
        int pokemonId = RANDOM.nextInt(MAX_POKEMON_ID) + 1;
        String url = API_URL + pokemonId;

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        PokemonEntity pokemon = getPokeFromJson(json);
        
        return pokemon; 
    }
}
