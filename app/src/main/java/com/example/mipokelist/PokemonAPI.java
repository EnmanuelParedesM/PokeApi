package com.example.mipokelist;

import com.example.mipokelist.models.Ability;
import com.example.mipokelist.models.PokemonList;
import com.example.mipokelist.models.PokemonsEstadisticas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {
    //ttps://pokeapi.co/api/v2/pokemon/1/
    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Call<PokemonList> getPokemons();

    @GET("pokemon/{id}")
    Call<PokemonsEstadisticas> getPokemon(@Path("id") String id);

    @GET("pokemon")
    Call<PokemonsEstadisticas> getSprites();
}
