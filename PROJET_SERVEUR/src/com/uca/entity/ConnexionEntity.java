package src.com.uca.entity;

import java.sql.Timestamp;

public class ConnexionEntity {
    private String firstConnexion;
    private String lastConnexion;
    private int userId;
    private int id;


    public ConnexionEntity(){

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getFirstConnexion(){
        return firstConnexion;
    }

    public void setFirstConnexion(String firstConnexion){
        this.firstConnexion = firstConnexion;
    }

    public String getLastConnexion(){
        return lastConnexion;
    }

    public void setLastConnexion(String lastConnexion){
        this.lastConnexion = lastConnexion;
    }
}
