package com.example.mipokelist.ioc;

import com.example.mipokelist.EstadisticasPokemonActivity;
import com.example.mipokelist.MainActivity;
import com.example.mipokelist.services.impl.PokemonServiceImpl;

import dagger.Component;
import dagger.Provides;

@Component(modules = {PokemonModule.class, ComunesModule.class})
public interface PokemonComponent {
    PokemonServiceImpl getPokemonService();
    void inject(MainActivity mainActivity);
    void inject(EstadisticasPokemonActivity estadisticasPokemonActivity);
}
