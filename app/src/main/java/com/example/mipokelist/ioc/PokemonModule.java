package com.example.mipokelist.ioc;

import com.example.mipokelist.PokemonAPI;
import com.example.mipokelist.services.PokemonRepository;
import com.example.mipokelist.services.PokemonService;
import com.example.mipokelist.services.impl.PokemonMockServiceImpl;
import com.example.mipokelist.services.impl.PokemonServiceImpl;
import com.example.mipokelist.services.impl.PokemonWebRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PokemonModule {

    @Provides
    PokemonService providePokemonService(PokemonRepository pokemonRepository){
        return new PokemonServiceImpl(pokemonRepository);
    }

    @Provides
    PokemonRepository providePokemonRepository(PokemonAPI pokemonAPI){
        return new PokemonWebRepositoryImpl(pokemonAPI);
    }

}
