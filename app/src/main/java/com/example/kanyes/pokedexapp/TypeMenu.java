package com.example.kanyes.pokedexapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckedTextView;

/**
 * Created by Kanyes on 9/19/2017.
 */

public class TypeMenu extends AppCompatActivity implements View.OnClickListener{
    public CheckedTextView normal, fire, fighting, water, psychic, ground, rock, fairy, dragon, poison, electric, grass, bug, flying, ice, dark, ghost, steel;
    public CheckedTextView[] types = new CheckedTextView[] {normal, fire, fighting, water, psychic, ground, rock, fairy, dragon, poison, electric, grass, bug, flying, ice, dark, ghost, steel};
    public int[] ids = new int[]{R.id.normal, R.id.fire, R.id.fighting, R.id.water, R.id.psychic, R.id.ground, R.id.rock, R.id.fairy, R.id.dragon, R.id.poison, R.id.electric, R.id.grass, R.id.bug, R.id.flying, R.id.ice, R.id.dark, R.id.ghost, R.id.steel};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_menu);

        for (int i=0; i<types.length;i++){
            types[i] = (CheckedTextView) findViewById(ids[i]);
            types[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        for (int i=0;i<ids.length;i++) {
            if (v.getId() == ids[i]) {
                types[i].setChecked(!types[i].isChecked());
                break;
            }
        }
    }
}
