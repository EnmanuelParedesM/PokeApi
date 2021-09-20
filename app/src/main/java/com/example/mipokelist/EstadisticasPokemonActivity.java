package com.example.mipokelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mipokelist.ioc.DaggerPokemonComponent;
import com.example.mipokelist.ioc.PokemonComponent;
import com.example.mipokelist.models.PokemonsEstadisticas;
import com.example.mipokelist.services.PokemonService;
import com.example.mipokelist.services.utils.Callback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EstadisticasPokemonActivity extends BaseActivity {



    private int listaClickada = 0;
    private int indice = 0;
    public String urlImagen;
    private String pokemonId;
    Context context;
    Bundle datos;

    FragmentSprites fragmentSprite;

    @Inject
    PokemonService pokemonService;

    @BindView(R.id.estadisticas)
    TextView mostrardatos;

    @BindView(R.id.imagenPokemon)
    ImageView imagenPokemon;

    @BindView(R.id.tipoPokemon)
    TextView tipoDePokemon;

    @BindView(R.id.listaSprites)
    ListView listaDeSprites;

    @BindView(R.id.cabeceraListaSprites)
    TextView cabeceraLista;

    @BindView(R.id.botonPrev)
    Button botonAnterior;

    @BindView(R.id.botonNext)
    Button botonSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_pokemon);
        ButterKnife.bind(this);

        PokemonComponent componente = DaggerPokemonComponent.create();
        componente.inject(this);

        datos = getIntent().getExtras();
        pokemonId = datos.getString("pokemonId");
        getEstadisticasPokemon(pokemonId);
        context = this.getBaseContext();
        cabeceraLista.setText("Lista de Sprites:");

        listaDeSprites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String urlSprite = listaDeSprites.getItemAtPosition(i).toString().trim();
                Glide.with(context).load(urlSprite).into(imagenPokemon);
                listaClickada = i;
            }
        });
        ;
        botonSiguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (listaClickada < 3) {
                    listaClickada++;
                }

                String unSprite = listaDeSprites.getItemAtPosition(listaClickada).toString().trim();
                Glide.with(context).load(unSprite).into(imagenPokemon);
            }
        });

        botonAnterior.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (listaClickada > 0) {
                    listaClickada--;
                }
                String otroSprite = listaDeSprites.getItemAtPosition(listaClickada).toString().trim();
                Glide.with(context).load(otroSprite).into(imagenPokemon);

            }
        });
    }

    private void getEstadisticasPokemon(String posicion) {
        pokemonService.getPokemon(posicion, new Callback<PokemonsEstadisticas>() {
            @Override
            public void onSuccess(PokemonsEstadisticas response) {
                String nombrePrimeraLetraMayúscula = EstadisticasPokemonActivity.primeraLetraMayuscula(response.getName());
                mostrardatos.setText(nombrePrimeraLetraMayúscula);
                String urlImagen = response.getSprites().getFrontDefault();

                String tipos = "Tipo: ";
                for (int i = 0; i < response.getTypes().size(); i++) {
                    tipos += (i == 0 ? " " : ", ") + response.getTypes().get(i).getType().getName();
                }
                tipoDePokemon.setText(tipos);
                Glide.with(context).load(urlImagen).into(imagenPokemon);
                List<String> sprites = new ArrayList<String>();
                sprites.add(response.getSprites().getFrontDefault().toString());
                sprites.add(response.getSprites().getBackDefault().toString());
                sprites.add(response.getSprites().getFrontShiny().toString());
                sprites.add(response.getSprites().getBackShiny().toString());

                listaDeSprites.setAdapter(new ArrayAdapter<String>(getApplicationContext()
                        , android.R.layout.simple_list_item_1, sprites));
            }

            @Override
            public void onError(String message) {

            }
        });
    }


    public static String primeraLetraMayuscula(String tagName) {
        String[] splits = tagName.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splits.length; i++) {
            String eachWord = splits[i];
            if (i > 0 && eachWord.length() > 0) {
                sb.append(" ");
            }
            String cap = eachWord.substring(0, 1).toUpperCase()
                    + eachWord.substring(1);
            sb.append(cap);
        }
        return sb.toString();
    }

    private void abrirFragment(String sprite) {
        fragmentSprite = new FragmentSprites();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();

        datos = new Bundle();
        datos.putString("datoSprite", sprite);

        fragmentSprite.setArguments(datos);
    }

    private String getUrlSprites(int j) {
        List<String> listaSprites = new ArrayList<String>();
        pokemonService.obtenerSprites(new Callback<PokemonsEstadisticas>() {
            @Override
            public void onSuccess(PokemonsEstadisticas response) {

                listaSprites.add(response.getSprites().getBackDefault().toString());
                listaSprites.add(response.getSprites().getFrontDefault().toString());
                listaSprites.add(response.getSprites().getBackShiny().toString());
                listaSprites.add(response.getSprites().getFrontShiny().toString());
            }

            @Override
            public void onError(String message) {

            }
        });
        return listaSprites.get(j);
    }
}