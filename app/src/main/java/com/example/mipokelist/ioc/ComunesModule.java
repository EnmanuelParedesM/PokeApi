package com.example.mipokelist.ioc;

import com.example.mipokelist.PokemonAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ComunesModule {

    @Provides
    PokemonAPI providesPokemonApi(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PokemonAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()). build();
        return retrofit.create(PokemonAPI.class);
    }

}
