package com.example.nzp.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int GRID_COLUMNS = 3;
    private static final int NUM_RANDOM_POKEMON = 20;

    //RecyclerView variables
    private RecyclerView recyclerView;
    private PokedexAdapter adapter;
    private DisplayStyle displayStyle = DisplayStyle.LIST;

    //Filter view variables
    private View filterInfoView;
    private ImageView removeFilterImage;
    private TextView filterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        changeDisplayStyle(displayStyle);

        //Filtering stuff
        filterInfoView = findViewById(R.id.filterInfoView);
        removeFilterImage = (ImageView) findViewById(R.id.removeFilterImage);
        filterText = (TextView) findViewById(R.id.filterText);
        filterInfoView.setVisibility(View.GONE);
        removeFilterImage.setOnClickListener(this);

    }

    private void changeDisplayStyle(DisplayStyle style) {
        //Create and set the layout manager
        if (style == DisplayStyle.LIST) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        } else if (style == DisplayStyle.GRID){
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), GRID_COLUMNS));
        } else {
            throw new IllegalArgumentException("Invalid style.");
        }

        //Create and set the adapter
        if (adapter == null) {
            adapter = new PokedexAdapter(this, null, null, style);
        } else {
            adapter = new PokedexAdapter(this, adapter.getFilteredPokemon(), adapter.getTruePokemon(), style);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        search((SearchView) menu.findItem(R.id.searchButton).getActionView());
        return true;
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
            case R.id.randomButton:
                adapter.getRandomPokemon(NUM_RANDOM_POKEMON);
                break;
            case R.id.filterButton:
                //TODO: Filter screen that properly calls adapter.filterPokemon and updates text.
                HashSet<Pokemon.Type> testSet = new HashSet<>();
                testSet.add(Pokemon.Type.WATER);
                testSet.add(Pokemon.Type.GRASS);
                PokedexFilter filter = new PokedexFilter(testSet, 0, 0, 0);
                adapter.filterPokemon(filter);
                filterText.setText(filter.toString());
                filterInfoView.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.removeFilterImage:
                PokedexFilter allFilter = new PokedexFilter();
                adapter.filterPokemon(allFilter);
                filterText.setText(allFilter.toString());
                filterInfoView.setVisibility(View.GONE);
                break;
        }
    }
}
