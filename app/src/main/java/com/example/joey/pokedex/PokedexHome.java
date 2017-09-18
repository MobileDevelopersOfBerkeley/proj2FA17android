package com.example.joey.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class PokedexHome extends AppCompatActivity {

    private RecyclerView pokemonList;
    private RecyclerView.Adapter pokemonListAdapter;
    private RecyclerView.LayoutManager pokemonListLayout;
    private Pokedex pokedex;
    private ArrayList<Pokedex.Pokemon> pokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_home);

        //init the Pokemon List View
        pokemonList = (RecyclerView) findViewById(R.id.pokemonList);
        pokemonListLayout = new LinearLayoutManager(this);
        pokemonList.setLayoutManager(pokemonListLayout);

        pokedex = new Pokedex();
        pokemons = pokedex.getPokemon();

        pokemonListAdapter = new PokemonAdapter(getApplicationContext(), pokemons);



    }
}
