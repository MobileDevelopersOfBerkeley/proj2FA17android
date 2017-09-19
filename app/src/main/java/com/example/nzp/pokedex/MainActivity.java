package com.example.nzp.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

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
        adapter = new PokedexAdapter(this, pokemonArrayList, style);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.displayMenuButton:
                displayStyle = DisplayStyle.other(displayStyle);
                changeDisplayStyle(displayStyle);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
