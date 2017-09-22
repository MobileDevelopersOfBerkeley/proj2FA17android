package com.example.kanyes.pokedexapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Kanyes on 9/19/2017.
 */

public class CategorySearch extends AppCompatActivity implements View.OnClickListener{
    public Button type_button, atk_button, def_button, hp_button;
    public AlertDialog atkBox, defBox, hpBox;
    public com.example.kanyes.pokedexapp.Pokedex pokedex;
    public static ArrayList<Pokedex.Pokemon> list, fil_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_search);

        type_button = (Button) findViewById(R.id.type_button);
        atk_button = (Button) findViewById(R.id.atk_button);
        def_button = (Button) findViewById(R.id.def_button);
        hp_button = (Button) findViewById(R.id.hp_button);
        pokedex = new Pokedex();
        list = pokedex.getPokemon();
        fil_list = new ArrayList<>();



        type_button.setOnClickListener(this);
        atk_button.setOnClickListener(this);
        def_button.setOnClickListener(this);
        hp_button.setOnClickListener(this);

        AlertDialog.Builder atk = new AlertDialog.Builder(CategorySearch.this);
        atk.setTitle("Search by Attack");
        atk.setMessage("Enter minimum attack");
        final EditText min_atk = new EditText(CategorySearch.this);
        atk.setView(min_atk);
        atk.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String answer = min_atk.getText().toString();
                fil_list = filtered_list(answer, "Attack");
            }
        });
        atk.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        atkBox = atk.create();


        AlertDialog.Builder def = new AlertDialog.Builder(CategorySearch.this);
        def.setTitle("Search by Defense");
        def.setMessage("Enter minimum defense");
        final EditText min_def = new EditText(CategorySearch.this);
        def.setView(min_def);
        def.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String answer = min_def.getText().toString();
                fil_list = filtered_list(answer, "Defense");
            }
        });
        def.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        defBox = def.create();


        AlertDialog.Builder hp = new AlertDialog.Builder(CategorySearch.this);
        hp.setTitle("Search by HP");
        hp.setMessage("Enter minimum HP");
        final EditText min_hp = new EditText(CategorySearch.this);
        hp.setView(min_hp);
        hp.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String answer = min_hp.getText().toString();
                fil_list = filtered_list(answer, "HP");
            }
        });
        hp.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        hpBox = hp.create();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.atk_button:
                atkBox.show();
                break;
            case R.id.def_button:
                defBox.show();
                break;
            case R.id.hp_button:
                hpBox.show();
                break;
            case R.id.type_button:
                Intent go_to_type = new Intent (getApplicationContext(), TypeMenu.class);
                startActivity(go_to_type);
                break;
        }
    }


    public ArrayList<Pokedex.Pokemon> filtered_list(String min, String category) {
        ArrayList<Pokedex.Pokemon> toReturn = new ArrayList<>();
        switch (category) {
            case "Attack":
                for (int i = 0; i < list.size(); i++) {
                    if (Integer.valueOf(list.get(i).attack) <= Integer.valueOf(min)) ;
                    toReturn.add(list.get(i));
                }
                break;
            case "Defense":
                for (int i = 0; i < list.size(); i++) {
                    if (Integer.valueOf(list.get(i).attack) <= Integer.valueOf(min)) ;
                    toReturn.add(list.get(i));
                }
                break;
            case "HP":
                for (int i = 0; i < list.size(); i++) {
                    if (Integer.valueOf(list.get(i).attack) <= Integer.valueOf(min)) ;
                    toReturn.add(list.get(i));
                }
                break;
        }
        return toReturn;
    }
}

