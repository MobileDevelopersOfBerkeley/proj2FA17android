package com.example.nzp.pokedex;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nzp on 9/19/17.
 *
 * The Pokemon class. Modified from the original from Pokedex.java.
 */

public class Pokemon {

    public enum Type {
        BUG, DARK, DRAGON, ELECTRIC, FAIRY, FIGHTING, FIRE, FLYING, GHOST, GRASS,
        GROUND, ICE, NORMAL, POISON, PSYCHIC, ROCK, STEEL, WATER
    }

    String name;
    String number;
    String attack;
    String defense;
    String hp;
    String species;
    Type[] type;

    public Pokemon(String name, JSONObject jsonData) {
        try {
            this.name = name;
            number = jsonData.getString("#").trim();
            attack = jsonData.getString("Attack").trim();
            defense = jsonData.getString("Defense").trim();
            hp = jsonData.getString("HP").trim();
            species = jsonData.getString("Species").trim();

            //Additional code to support types
            String types = jsonData.getString("Type").trim();
            String[] typeString = types.substring(1, types.length() - 1).split(","); //split into array of types
            type = new Type[typeString.length];
            for (int i = 0; i < typeString.length; i += 1) {
                typeString[i] = typeString[i].substring(1, typeString[i].length() - 1); //remove "" around each type
                type[i] = Type.valueOf(typeString[i].toUpperCase());
            }

        } catch (JSONException e) {
            Log.i("JSON error", "error parsing json data");
        }
    }

}