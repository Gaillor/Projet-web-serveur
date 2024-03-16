package src.com.uca.entity;

import java.sql.Timestamp;

public class LevelUpEntity {
    private int id;
    private int pokemonId;
    private int userId;

    
    public LevelUpEntity(){

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getPokemonId(){
        return pokemonId;
    }

    public void setPokemon1Id(int pokemonId){
        this.pokemonId = pokemonId;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

}
