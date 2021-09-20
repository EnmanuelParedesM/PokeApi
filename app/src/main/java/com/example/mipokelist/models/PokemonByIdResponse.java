package com.example.mipokelist.models;

import com.google.gson.annotations.SerializedName;

public class PokemonByIdResponse {

    @SerializedName("base_experience")
    private int baseExperience, id;
    private String name;

    public int getBaseExperience(){
        return baseExperience;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }
}
