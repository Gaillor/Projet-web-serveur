package com.uca.core;

import com.uca.dao.UserDAO;
import com.uca.entity.UserEntity;

import org.mindrot.jbcrypt.BCrypt;
import java.time.LocalDate;

import java.util.ArrayList;

public class UserCore {

    public static ArrayList<UserEntity> getAllUsers() {
        return new UserDAO().getAllUsers();
    }


    public static UserEntity addUser(String firstname, String lastname, String username, String email, String password) {
        UserEntity user = new UserEntity();

        //Cryptage du mot de passe
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);

        UserEntity createdUser = new UserDAO().create(user);


        return createdUser;
    }

    public static UserEntity getUserById(int userId) {
        return new UserDAO().getUserById(userId);
    }

/*  public static UserEntity getUserProfile(String email, String password){
        return new UserDAO().getUserProfile(email,password);
    }
*/
    public static UserEntity getUserByEmail(String email){
        return new UserDAO().getUserByEmail(email);
    }

    public static LocalDate getLastConnexionDate(int userId){
        return new UserDAO().getLastConnexionDate(userId);
    }

    public static void updateLastConnexionDate(LocalDate date, int userId){
        new UserDAO().updateLastConnexionDate(date, userId);
    }

    public static boolean isUserExists(UserEntity user){
        return new UserDAO().isUserExists(user);
    }
}