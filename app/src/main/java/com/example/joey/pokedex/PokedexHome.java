package com.example.joey.pokedex;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class PokedexHome extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView pokemonList;
    private PokemonAdapter pokemonListAdapter;
    private RecyclerView.LayoutManager pokemonListLayout;
    private Pokedex pokedex = new Pokedex();
    final private ArrayList<Pokedex.Pokemon> pokemonsMaster = pokedex.getPokemon();
    private Button typeFilterButton;
    private Button hpFilterButton;
    private Button randomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_home);

        //init the Pokemon List View
        pokemonList = (RecyclerView) findViewById(R.id.pokemonList);
        pokemonListLayout = new LinearLayoutManager(this);
        pokemonList.setLayoutManager(pokemonListLayout);

        pokemonListAdapter = new PokemonAdapter(getApplicationContext(), pokemonsMaster);
        pokemonList.setAdapter(pokemonListAdapter);

        typeFilterButton = (Button) findViewById(R.id.typeFilterButton);
        hpFilterButton = (Button) findViewById(R.id.hpFilterButton);
        randomButton = (Button) findViewById(R.id.randomButton);

        typeFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent typeOptions = new Intent(getApplicationContext(), TypeFilter.class);
                startActivity(typeOptions);
            }
        });

    }

    /*
    IMPLEMENTATION OF THE POKEMON SEARCH BY NAME FEATURES
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("CREATION", "on query text change called");
        updatePokedemsByName(newText);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    public void updatePokedemsByName(String nameQuery){
        ArrayList<Pokedex.Pokemon> newPokemons = new ArrayList<>();
        for(Pokedex.Pokemon pokemon : pokemonsMaster){
            if(pokemon.name.toLowerCase().startsWith(nameQuery.toLowerCase())){
                newPokemons.add(pokemon);
            }
        }
        pokemonListAdapter.pokemons.clear();
        pokemonListAdapter.pokemons.addAll(newPokemons);
        Log.d("CREATION", "new pokemon list created");
        pokemonListAdapter.notifyDataSetChanged();
    }

}