package com.uca.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class PokemonEntity {
    private String name;
    private int level;
    private int id;
    private int ownerId;
    private int idInTable; 
    
    
    public PokemonEntity(){
        //IGNORED
    }

    public PokemonEntity(int idInTable){
        this.idInTable = idInTable;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getOwnerId(){
        return ownerId;
    }

    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }

    public boolean compareInfo(PokemonEntity p){
        if(this.id == p.getId() && this.name == p.getName() && this.level == p.getLevel()){
            return true;
        }
        else{
            return false;
        }
    }

    public int getIdInTable(){
        return this.idInTable;
    }

    public void setIdInTable(int id){
        final int Id = id;
        this.idInTable = Id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonEntity that = (PokemonEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name)&&
                Objects.equals(level,that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, ownerId);
    }


}
