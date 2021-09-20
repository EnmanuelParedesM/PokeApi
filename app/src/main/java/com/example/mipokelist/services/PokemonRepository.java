package com.example.mipokelist.services;

import com.example.mipokelist.models.PokemonList;
import com.example.mipokelist.models.PokemonsEstadisticas;
import com.example.mipokelist.services.utils.Callback;

public interface PokemonRepository {

    public void obtenerSprites(Callback<PokemonsEstadisticas> responseCallback);
    public void getPokemons(Callback<PokemonList> responseCallback);
    public void getPokemon(String id, Callback<PokemonsEstadisticas> responseCallback);
}
