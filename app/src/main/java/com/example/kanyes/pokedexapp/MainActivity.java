package com.example.kanyes.pokedexapp;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public Button view_switch;
    public android.widget.SearchView search;
    public Menu menu;
    public ArrayList<Pokedex.Pokemon> pkmn_full_list, pkmn_list_after_filter;
    public PokemonAdapter adapter;
    public AlertDialog atkBox, defBox, hpBox;
    public RecyclerView recyclerView;
    public static Pokedex.Pokemon pokemon;
    public boolean list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = true;
        view_switch = (Button) findViewById(R.id.view_switch);
        view_switch.setOnClickListener(this);

        search = (android.widget.SearchView) findViewById(R.id.search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        Pokedex pokedex = new Pokedex();
        pkmn_full_list = pokedex.getPokemon();
        pkmn_list_after_filter = pkmn_full_list;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PokemonAdapter(pkmn_list_after_filter, getApplicationContext(), true);
        recyclerView.setAdapter(adapter);

        AlertDialog.Builder atk = new AlertDialog.Builder(this);
        atk.setTitle("Search by Attack");
        atk.setMessage("Enter minimum attack");
        final EditText min_atk = new EditText(this);
        atk.setView(min_atk);
        atk.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String answer = min_atk.getText().toString();
                filtered_list(answer, "Attack");
            }
        });
        atk.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        atkBox = atk.create();


        AlertDialog.Builder def = new AlertDialog.Builder(this);
        def.setTitle("Search by Defense");
        def.setMessage("Enter minimum defense");
        final EditText min_def = new EditText(this);
        def.setView(min_def);
        def.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String answer = min_def.getText().toString();
                filtered_list(answer, "Defense");
            }
        });
        def.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        defBox = def.create();


        AlertDialog.Builder hp = new AlertDialog.Builder(this);
        hp.setTitle("Search by HP");
        hp.setMessage("Enter minimum HP");
        final EditText min_hp = new EditText(this);
        hp.setView(min_hp);
        hp.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String answer = min_hp.getText().toString();
                filtered_list(answer, "HP");
            }
        });
        hp.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        hpBox = hp.create();

        //search.setOnQueryTextListener();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_switch:
                if (list != true) {
                    list = false;
                    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                    adapter = new PokemonAdapter(pkmn_list_after_filter, getApplicationContext(), false);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    list=true;
                    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter = new PokemonAdapter(pkmn_list_after_filter, getApplicationContext(), true);
                    recyclerView.setAdapter(adapter);
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // legit after 6 hours of youtube tutorials and stackoverflow none of this works and we've debugged every single line with no solution pls help


        /*final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<Pokedex.Pokemon> newList = new ArrayList<>();
                for(Pokedex.Pokemon pokemon: pkmn_list_after_filter){
                    String name = pokemon.name.toLowerCase();
                    if(name.contains(newText));
                    newList.add(pokemon);
                }
                pkmn_list_after_filter = newList;
                adapter = new PokemonAdapter(pkmn_list_after_filter, getApplicationContext());
                recyclerView.setAdapter(adapter);
                return false;
            }
        });*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.random_poke:
                Pokedex pokedex = new Pokedex();
                pkmn_list_after_filter = pokedex.getPokemon();
                Collections.shuffle(pkmn_list_after_filter);
                pkmn_list_after_filter.subList(20, pkmn_list_after_filter.size()).clear();
                adapter = new PokemonAdapter(pkmn_list_after_filter, getApplicationContext(), true);
                recyclerView.setAdapter(adapter);
                break;
            case R.id.type_poke:
                Intent go_to_type = new Intent (getApplicationContext(), TypeMenu.class);
                startActivity(go_to_type);
                break;
            case R.id.def_poke:
                defBox.show();
                break;
            case R.id.attack_poke:
                atkBox.show();
                break;
            case R.id.hp_poke:
                hpBox.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void filtered_list(String min, String category) {
        ArrayList<Pokedex.Pokemon> toReturn = new ArrayList<>();
        switch (category) {
            case "Attack":
                for (int i = 0; i < pkmn_full_list.size(); i++) {
                    if (Integer.valueOf(pkmn_full_list.get(i).attack) >= Integer.valueOf(min))
                        toReturn.add(pkmn_full_list.get(i));
                }
                pkmn_list_after_filter=toReturn;
                adapter = new PokemonAdapter(pkmn_list_after_filter, getApplicationContext(), true);
                recyclerView.setAdapter(adapter);
                break;
            case "Defense":
                for (int i = 0; i < pkmn_full_list.size(); i++) {
                    if (Integer.valueOf(pkmn_full_list.get(i).defense) >= Integer.valueOf(min))
                        toReturn.add(pkmn_full_list.get(i));
                }
                pkmn_list_after_filter=toReturn;
                adapter = new PokemonAdapter(pkmn_list_after_filter, getApplicationContext(), true);
                recyclerView.setAdapter(adapter);
                break;
            case "HP":
                for (Pokedex.Pokemon p: pkmn_full_list) {
                    if (Integer.valueOf(p.hp) >= Integer.valueOf(min))
                        toReturn.add(p);
                }
                pkmn_list_after_filter=toReturn;
                adapter = new PokemonAdapter(pkmn_list_after_filter, getApplicationContext(), true);
                recyclerView.setAdapter(adapter);
                break;
        }
    }

// this shit wont fucking work idk y fuck my life


 /*   //SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener(){
    public boolean onQueryTextChange(String newText) {
            changeSearch(newText);
            return true;
        }
    public boolean onQueryTextSubmit(String query) {
            return false;
        }
    //};

    public void changeSearch(String newText){
        newText = newText.toLowerCase();
        ArrayList<Pokedex.Pokemon> newList = new ArrayList<>();
        for(Pokedex.Pokemon pokemon: pkmn_list_after_filter){
            String name = pokemon.name.toLowerCase();
            if(name.contains(newText));
            newList.add(pokemon);
        }
        pkmn_list_after_filter = newList;
        adapter = new PokemonAdapter(pkmn_list_after_filter, getApplicationContext());
        recyclerView.setAdapter(adapter);
    } */
}