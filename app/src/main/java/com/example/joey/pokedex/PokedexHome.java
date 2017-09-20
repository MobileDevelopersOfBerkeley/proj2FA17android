package com.example.joey.pokedex;

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
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class PokedexHome extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView pokemonList;
    private PokemonAdapter pokemonListAdapter;
    private RecyclerView.LayoutManager pokemonListLayout;
    private Pokedex pokedex = new Pokedex();
    final private ArrayList<Pokedex.Pokemon> pokemonsMaster = pokedex.getPokemon();

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