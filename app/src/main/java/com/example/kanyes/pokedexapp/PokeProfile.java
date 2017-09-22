package com.example.kanyes.pokedexapp;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Gupta on 9/21/2017.
 */

public class PokeProfile extends AppCompatActivity implements View.OnClickListener{
    public Button web_search;
    public TextView name, number, attack, defense, hp, species;
    public Pokedex.Pokemon Pokemon;
    public ImageView pic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poke_profile);
        Pokemon = PokemonAdapter.pokemon1;
        web_search = (Button) findViewById(R.id.web_search);
        web_search.setOnClickListener(this);
        name = (TextView) findViewById(R.id.name);
        number = (TextView) findViewById(R.id.number);
        attack = (TextView) findViewById(R.id.attack);
        defense = (TextView) findViewById(R.id.defense);
        hp = (TextView) findViewById(R.id.hp);
        species = (TextView) findViewById(R.id.species);
        pic = (ImageView) findViewById(R.id.pokePic);

        name.setText(Pokemon.name);
        number.setText(Pokemon.number);
        // this shit wont fucking work idk y fuck my life
        Glide.with(this).load("http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + number.getText() + ".png").into(pic);
        attack.setText("Attack: " + Pokemon.attack);
        defense.setText("Defense: " + Pokemon.defense);
        hp.setText("HP: " + Pokemon.hp);
        species.setText("Species: " + Pokemon.species);
    }

    @Override
    public void onClick(View v) {
        searchWeb(Pokemon.name);
    }

    public void searchWeb(String query) {
        Uri uri=Uri.parse("http://www.google.com/search?q=" + name.getText());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
