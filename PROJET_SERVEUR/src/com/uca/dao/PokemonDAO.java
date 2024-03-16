package com.uca.dao;

import com.uca.entity.PokemonEntity;

import java.sql.*;
import java.util.ArrayList;

public class PokemonDAO extends _Generic<PokemonEntity> {

    public ArrayList<PokemonEntity> getAllPokemons() {
        ArrayList<PokemonEntity> entities = new ArrayList<>();
        try {
            
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM pokemons ORDER BY id ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {

                //Recupération des informations de chaque ligne
                PokemonEntity entity = new PokemonEntity();

                entity.setId(resultSet.getInt("id"));
                entity.setName(resultSet.getString("name"));
                entity.setLevel(resultSet.getInt("level"));
                entity.setOwnerId(resultSet.getInt("owner_id"));
            
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }


    public PokemonEntity getPokemonById(int id) {
        
        PokemonEntity pokemon = new PokemonEntity();

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM pokemons WHERE id = ?");
            preparedStatement.setInt(1, id);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                
                pokemon.setId(resultSet.getInt("id"));
                pokemon.setName(resultSet.getString("name"));
                pokemon.setLevel(resultSet.getInt("level"));
                pokemon.setOwnerId(resultSet.getInt("owner_id"));
            
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pokemon;
    }


    @Override
    public PokemonEntity create(PokemonEntity obj) {
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO pokemons(id, name, level, owner_id) VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1,obj.getId());
            statement.setString(2, obj.getName());
            statement.setInt(3, obj.getLevel());
            statement.setInt(4, obj.getOwnerId());
        
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public boolean delete(PokemonEntity obj) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM pokemons WHERE id = ?");
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isPokemonExists(PokemonEntity pokemon) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT COUNT(*) FROM pokemons WHERE id = ?");
            preparedStatement.setInt(1, pokemon.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; // Pokémon non trouvé dans la base de données
    }
    
    public boolean updatePokemonLevel(int pokemonId, int newLevel) throws SQLException{
        try{

            PreparedStatement preparedStatement = this.connect.prepareStatement("UPDATE pokemons SET level = ? WHERE id = ?");
            preparedStatement.setInt(1, newLevel);
            preparedStatement.setInt(2, pokemonId);

            preparedStatement.executeUpdate();

            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }
}
