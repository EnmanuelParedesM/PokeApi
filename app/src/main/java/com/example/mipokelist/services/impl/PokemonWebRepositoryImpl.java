package com.example.mipokelist.services.impl;

import com.example.mipokelist.PokemonAPI;
import com.example.mipokelist.models.PokemonList;
import com.example.mipokelist.models.PokemonsEstadisticas;
import com.example.mipokelist.services.utils.Callback;
import com.example.mipokelist.services.PokemonRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class PokemonWebRepositoryImpl implements PokemonRepository {
    PokemonAPI pokemonAPI;

    @Inject
    public PokemonWebRepositoryImpl(PokemonAPI pokemonAPI){
        this.pokemonAPI = pokemonAPI;
    }

    @Override
    public void obtenerSprites(Callback<PokemonsEstadisticas> responseCallback) {
        Call<PokemonsEstadisticas> call = pokemonAPI.getSprites();
        call.enqueue(new retrofit2.Callback<PokemonsEstadisticas>() {
            @Override
            public void onResponse(Call<PokemonsEstadisticas> call, Response<PokemonsEstadisticas> response) {
                responseCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PokemonsEstadisticas> call, Throwable t) {
                responseCallback.onError("An error has ocurred");
            }
        });
    }

    @Override
    public void getPokemons(Callback<PokemonList> responseCallback) {
        Call<PokemonList> call = pokemonAPI.getPokemons();
        call.enqueue(new retrofit2.Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                responseCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                responseCallback.onError("An error has ocurred");
            }
        });
    }

    @Override
    public void getPokemon(String id, Callback<PokemonsEstadisticas> responseCallback) {
        Call<PokemonsEstadisticas> callAbility = pokemonAPI.getPokemon(id);
        callAbility.enqueue(new retrofit2.Callback<PokemonsEstadisticas>() {
            @Override
            public void onResponse(Call<PokemonsEstadisticas> call, Response<PokemonsEstadisticas> response) {
                responseCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PokemonsEstadisticas> call, Throwable t) {
                responseCallback.onError("An erros has ocurred");

            }
        });
    }


}
