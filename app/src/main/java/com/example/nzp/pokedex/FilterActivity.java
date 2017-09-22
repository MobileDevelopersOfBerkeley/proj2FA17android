package com.example.nzp.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class FilterActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    public static final int HIGHEST_ATK = 180;
    public static final int HIGHEST_DEF = 230;
    public static final int HIGHEST_HP = 255;

    SeekBar seekBarAtk, seekBarDef, seekBarHP;
    TextView minAtkText, minDefText, minHPText;
    CheckBox bugCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        minAtkText = (TextView) (findViewById(R.id.minAtkText));
        minDefText = (TextView) (findViewById(R.id.minDefText));
        minHPText = (TextView) (findViewById(R.id.minHPText));
        seekBarAtk = (SeekBar) (findViewById(R.id.seekBarAtk));
        seekBarDef = (SeekBar) (findViewById(R.id.seekBarDef));
        seekBarHP = (SeekBar) (findViewById(R.id.seekBarHP));
        bugCheckBox = (CheckBox) (findViewById(R.id.bugCheckBox));
        seekBarAtk.setMax(HIGHEST_ATK);
        seekBarDef.setMax(HIGHEST_DEF);
        seekBarHP.setMax(HIGHEST_HP);
        seekBarAtk.setOnSeekBarChangeListener(this);
        seekBarDef.setOnSeekBarChangeListener(this);
        seekBarHP.setOnSeekBarChangeListener(this);
        bugCheckBox.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        save();
    }

    public void save() {
        Intent intent = new Intent();
        intent.putExtra("atk", seekBarAtk.getProgress());
        intent.putExtra("def", seekBarDef.getProgress());
        intent.putExtra("hp", seekBarHP.getProgress());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.seekBarAtk:
                minAtkText.setText(String.format(getString(R.string.min_atk), i));
                break;
            case R.id.seekBarDef:
                minDefText.setText(String.format(getString(R.string.min_def), i));
                break;
            case R.id.seekBarHP:
                minHPText.setText(String.format(getString(R.string.min_hp), i));
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bugCheckBox:
                break;
        }
    }
}
