package com.example.mipokelist;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.mipokelist.services.impl.PokemonServiceImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSprites#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSprites extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String url;
    private static final String TAG = "MyFragment";
    @Inject
    PokemonServiceImpl pokemonServicio;

    Context thiscontext;
    private boolean botonPulsado = true;

    @BindView(R.id.imagenOtroSprite)
    ImageView spriteImagen;


    public FragmentSprites() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSprites.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSprites newInstance(String param1, String param2) {
        FragmentSprites fragment = new FragmentSprites();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sprites, container, false);
        ButterKnife.bind(this, view);
        Bundle datos = getArguments();
        //pokemonServicio = new PokemonServiceImpl();
        thiscontext = container.getContext();
        try {

            if (datos != null) {
                Glide.with(this).load(datos.getString("datoSprite")).into(spriteImagen);

            }
        } catch (Exception e) {
            Log.e(TAG, "onCreateView", e);
            throw e;
        }

        return view;
    }




   /* private void getUrlSprites(int j){
        pokemonServicio.obtenerSprites(j, new Callback<PokemonsEstadisticas>() {
            @Override
            public void onSuccess(PokemonsEstadisticas response) {
                List<String> listaSprites = new ArrayList<String>();
                listaSprites.add(response.getSprites().getBackDefault().toString());
                listaSprites.add(response.getSprites().getFrontDefault().toString());
                listaSprites.add(response.getSprites().getBackShiny().toString());
                listaSprites.add(response.getSprites().getFrontShiny().toString());

                Glide.with(thiscontext).load(listaSprites.get(j)).into(spriteImagen);

            }
            @Override
            public void onError(String message) {

            }
        });
    }*/
}
