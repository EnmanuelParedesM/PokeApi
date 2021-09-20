package com.example.mipokelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mipokelist.ioc.DaggerPokemonComponent;
import com.example.mipokelist.ioc.PokemonComponent;
import com.example.mipokelist.models.PokemonList;
import com.example.mipokelist.services.PokemonService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements com.example.mipokelist.services.utils.Callback<PokemonList> {

    public String urlPokemon;
    public String habilidadPokemon;

    @Inject
    PokemonService pokemonService;

    @BindView(R.id.lista)
    ListView pockeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        PokemonComponent componente = DaggerPokemonComponent.create();
        componente.inject(this);
        getPockemons();

        pockeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pokemonId = pockeListView.getItemAtPosition(i).toString().trim();
                Intent myIntent = new Intent(view.getContext(), EstadisticasPokemonActivity.class);
                myIntent.putExtra("pokemonId", pokemonId);
                startActivity(myIntent);
            }
        });
    }

    private void getPockemons() {
        pokemonService.getPokemons(this);

//        Call<PokemonList> call = RetroFitClient.getInstance().getPokemonAPI().getPokemons();
//        call.enqueue(new Callback<PokemonList>() {
//            @Override...
    }



    @Override
    public void onSuccess(PokemonList response) {
        String[] onePokemon = new String[response.getResults().size()];

        for (int i = 0; i < response.getResults().size(); i++) {
            onePokemon[i] = response.getResults().get(i).getName();
        }
        String myStringLista;
        pockeListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, onePokemon));
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplicationContext(), "An error has ocurred", Toast.LENGTH_LONG).show();
    }


}