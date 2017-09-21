package com.example.nzp.pokedex;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PokemonDisplay extends AppCompatActivity {

    TextView name;
    TextView pokemonID;
    TextView species;
    TextView HP;
    TextView attack;
    TextView defense;
    TextView specialAttack;
    TextView specialDefense;
    TextView speed;
    TextView total;
    TextView type;
    ImageView image;
    Button searchButton;

    Pokedex mPokedex;
    ArrayList<Pokedex.Pokemon> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_display);

        name = (TextView) findViewById(R.id.name);
        pokemonID = (TextView) findViewById(R.id.pokemonID);
        species = (TextView) findViewById(R.id.species);
        HP = (TextView) findViewById(R.id.HP);
        attack = (TextView) findViewById(R.id.attack);
        defense = (TextView) findViewById(R.id.defense);
        specialAttack = (TextView) findViewById(R.id.specialAttack);
        specialDefense = (TextView) findViewById(R.id.specialDefense);
        speed = (TextView) findViewById(R.id.speed);
        total = (TextView) findViewById(R.id.total);
        type = (TextView) findViewById(R.id.type);
        image = (ImageView) findViewById(R.id.pokemonPic);
        searchButton = (Button)findViewById(R.id.searchButton);

        mPokedex = new Pokedex();
        list = mPokedex.getPokemon();


        final int i = 180;
        name.setText(list.get(i).name);
        species.setText(list.get(i).species);
        HP.setText(list.get(i).hp);
        //name.setText(list.get(i).hp.toString());
        //HP.setText(list.get(i).hp);
        attack.setText(list.get(i).attack);
        defense.setText(list.get(i).defense);
        pokemonID.setText(list.get(i).number);
        specialAttack.setText(list.get(i).specialAttack);
        specialDefense.setText(list.get(i).specialDefense);
        speed.setText(list.get(i).speed);
        total.setText(list.get(i).total);
        type.setText(list.get(i).type);

        //display the image of the pokemon
        String filename = "http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + list.get(i).number + ".png";
        Picasso.with(getApplicationContext()).load(filename).into(image);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.google.com/#q="+list.get(i).name);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
}
