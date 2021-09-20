package com.example.mipokelist.services.impl;

import com.example.mipokelist.models.PokemonList;
import com.example.mipokelist.models.PokemonsEstadisticas;
import com.example.mipokelist.services.PokemonRepository;
import com.example.mipokelist.services.utils.Callback;
import com.example.mipokelist.services.PokemonService;

import javax.inject.Inject;


import retrofit2.Call;
import retrofit2.Response;


public class PokemonServiceImpl implements PokemonService {

    private static final String TAG = "Pokemon Service";
    private PokemonRepository pokemonRepository;

    @Inject
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }


    @Override
    public void getPokemons(Callback<PokemonList> responseCallback) {
        pokemonRepository.getPokemons(responseCallback);
//        Call<PokemonList> call = RetroFitClient.getInstance().getPokemonAPI().getPokemons();
//        call.enqueue(new retrofit2.Callback<PokemonList>() {
//            @Override
//            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
//                responseCallback.onSuccess(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<PokemonList> call, Throwable t) {
//                responseCallback.onError("An error has ocurred");
//            }
//        });
    }

    @Override
    public void getPokemon(String id, Callback<PokemonsEstadisticas> responseCallback) {
        pokemonRepository.getPokemon(id, responseCallback);

//        Call<PokemonsEstadisticas> callAbility = RetroFitClient.getInstance().getPokemonAPI().getPokemon(id);
//        callAbility.enqueue(new retrofit2.Callback<PokemonsEstadisticas>() {
//            @Override
//            public void onResponse(Call<PokemonsEstadisticas> call, Response<PokemonsEstadisticas> response) {
//                responseCallback.onSuccess(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<PokemonsEstadisticas> call, Throwable t) {
//                responseCallback.onError("An erros has ocurred");
//
//            }
//        });

    }

    @Override
    public void obtenerSprites(Callback<PokemonsEstadisticas> responseCallback) {
        pokemonRepository.obtenerSprites(responseCallback);
    }
    //PokemonRepository -- PokemonWebRepositoryImpl


//    @Override
//    public void obtenerSprites(Callback<PokemonsEstadisticas> responseCallback) {
//        Call<PokemonsEstadisticas> call = RetroFitClient.getInstance().getPokemonAPI().getSprites();
//        call.enqueue(new retrofit2.Callback<PokemonsEstadisticas>() {
//            @Override
//            public void onResponse(Call<PokemonsEstadisticas> call, Response<PokemonsEstadisticas> response) {
//                responseCallback.onSuccess(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<PokemonsEstadisticas> call, Throwable t) {
//                responseCallback.onError("An error has ocurred");
//            }
//        });
//    }


}
