package com.uca.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserEntity {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private int id;
    private Map<PokemonEntity, Integer> userPokemons;

    public UserEntity() {
        this.userPokemons = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void addPokemonToCollection(PokemonEntity pokemon){
        
        if(userPokemons.containsKey(pokemon)){  //s'il existe déjà
            
            int currentNumber = userPokemons.get(pokemon);
            currentNumber += 1;                 //rajouter son nombre
            userPokemons.put(pokemon, currentNumber);

        }
        else{

            userPokemons.put(pokemon,1);    //Initialisation du nombre à 1

        }
    }

    public void deletePokemonFromCollection(PokemonEntity pokemon){
        
        if(userPokemons.containsKey(pokemon)){  //s'il existe déjà
            
            int currentNumber = userPokemons.get(pokemon);
            currentNumber -= 1;                 //rajouter son nombre
            
            if(currentNumber > 0){
                userPokemons.put(pokemon, currentNumber);
            }
            else{
                userPokemons.remove(pokemon);
            }
        }
        else{

            userPokemons.remove(pokemon);    //Initialisation du nombre à 1

        }
    }

    public Map<PokemonEntity, Integer> getPokemonsCollection(){
        return this.userPokemons;
    }

    public boolean hasPokemon(PokemonEntity pokemon){
        return this.userPokemons.containsKey(pokemon);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return id == user.id &&
                Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }

}
