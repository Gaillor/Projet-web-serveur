package com.uca.dao;

import com.uca.entity.PokemonEntity;
import com.uca.entity.UserEntity;
import java.time.LocalDate;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends _Generic<UserEntity> {

    public ArrayList<UserEntity> getAllUsers() {
        ArrayList<UserEntity> entities = new ArrayList<>();
        try {
            
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users ORDER BY id ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {

                //Recupération des information de chaque ligne
                UserEntity entity = new UserEntity();

                entity.setId(resultSet.getInt("id"));
                entity.setFirstName(resultSet.getString("firstname"));
                entity.setLastName(resultSet.getString("lastname"));
                entity.setEmail(resultSet.getString("email"));
                entity.setUserName(resultSet.getString("username"));
                entity.setPassword(resultSet.getString("password"));   
            
                PreparedStatement preparedStatement2 = this.connect.prepareStatement("SELECT * FROM pokemons WHERE owner_id = ?");
                preparedStatement2.setInt(1, entity.getId());

                ResultSet resultSet2 = preparedStatement2.executeQuery();
                PokemonEntity pokemon = new PokemonEntity();

                //Ajout de tous ses pokemons
                while(resultSet2.next()){
                    pokemon.setId(resultSet2.getInt("id"));
                    pokemon.setName(resultSet2.getString("name"));
                    pokemon.setLevel(resultSet2.getInt("level"));
                    pokemon.setOwnerId(resultSet2.getInt("owner_id"));

                    entity.addPokemonToCollection(pokemon);
                }


                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    @Override
    public UserEntity create(UserEntity obj) {
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO users(firstname, lastname, username, password, email) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, obj.getFirstName());
            statement.setString(2, obj.getLastName());
            statement.setString(3, obj.getUserName());
            statement.setString(4, obj.getPassword());
            statement.setString(5, obj.getEmail());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                obj.setId(id);

            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public UserEntity getUserById(int id) {
        
        UserEntity user = new UserEntity();

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));


                PreparedStatement preparedStatement2 = this.connect.prepareStatement("SELECT * FROM pokemons WHERE owner_id = ?");
                preparedStatement2.setInt(1, user.getId());

                ResultSet resultSet2 = preparedStatement2.executeQuery();
                
                //Ajout de tous ses pokemons
                while(resultSet2.next()){
                    PokemonEntity pokemon = new PokemonEntity();
                    
                    pokemon.setId(resultSet2.getInt("id"));
                    pokemon.setName(resultSet2.getString("name"));
                    pokemon.setLevel(resultSet2.getInt("level"));
                    pokemon.setOwnerId(resultSet2.getInt("owner_id"));

                    user.addPokemonToCollection(pokemon);
                }
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public UserEntity getUserByEmail(String email){
        
        try {
            
            UserEntity user = new UserEntity();

            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));


                PreparedStatement preparedStatement2 = this.connect.prepareStatement("SELECT * FROM pokemons WHERE owner_id = ?");
                preparedStatement2.setInt(1, user.getId());

                ResultSet resultSet2 = preparedStatement2.executeQuery();
            
                //Ajout de tous ses pokemons
                while(resultSet2.next()){
                    PokemonEntity pokemon = new PokemonEntity();
                    
                    pokemon.setId(resultSet2.getInt("id"));
                    pokemon.setName(resultSet2.getString("name"));
                    pokemon.setLevel(resultSet2.getInt("level"));
                    pokemon.setOwnerId(resultSet2.getInt("owner_id"));

                    user.addPokemonToCollection(pokemon);
                }

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isUserExists(UserEntity user) {
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT COUNT(*) FROM users WHERE id = ?");
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; // Utilisateur non trouvé dans la base de données
    }
    


    public LocalDate getLastConnexionDate(int userId){

        LocalDate date = null;

        try {
            
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT last_connexion FROM connexions WHERE user_id = ?");
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Date sqlDate = resultSet.getDate("last_connexion");
            
                if (sqlDate != null){
                    date = sqlDate.toLocalDate();
                }

                System.out.println("BONNNN");
            }
            else{
                //Il n'y a pas encore de connexion
                date = LocalDate.now();
                updateLastConnexionDate(date, userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return date;
    }


    public void updateLastConnexionDate(LocalDate date, int userId) {
        if (date == null) {
            System.out.println("La date est vide");
            return;
        }
    
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE connexions SET last_connexion = ? WHERE user_id = ?");
            statement.setDate(1, Date.valueOf(date));
            statement.setInt(2, userId);
            statement.executeUpdate();

            System.out.println("Date mise a jour");

        } catch (SQLException e) {
            // Gérer l'exception de manière appropriée
            e.printStackTrace();
        }
    }
    


    @Override
    public boolean delete(UserEntity obj) {
        //TODO !
        return false;
    }
}
