package com.example.nzp.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_COLUMNS = 3;

    private ArrayList<Pokedex.Pokemon> pokemonArrayList;
    private RecyclerView recyclerView;

    private PokedexAdapter adapter;

    private DisplayStyle displayStyle = DisplayStyle.LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokemonArrayList = new Pokedex().getPokemon();

        changeDisplayStyle(displayStyle);

    }

    private void changeDisplayStyle(DisplayStyle style) {
        if (style == DisplayStyle.LIST) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        } else if (style == DisplayStyle.GRID){
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), GRID_COLUMNS));
        } else {
            throw new IllegalArgumentException("Invalid style.");
        }
        if (adapter == null) {
            adapter = new PokedexAdapter(this, pokemonArrayList, pokemonArrayList, style);
        } else {
            adapter = new PokedexAdapter(this, pokemonArrayList, adapter.getFilteredPokemon(), style);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.searchButton).getActionView();
        search(searchView);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.displayMenuButton).setTitle(String.format("%s %s", getString(R.string.display_as), displayStyle));
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.displayMenuButton:
                displayStyle = DisplayStyle.other(displayStyle);
                changeDisplayStyle(displayStyle);
                return true;
            case R.id.searchButton:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}
