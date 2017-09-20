package com.example.joey.pokedex;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class TypeFilter extends AppCompatActivity {

    private ArrayList<Button> typeButtons = new ArrayList<Button>();
    private View.OnClickListener typeButtonListener;
    private int accentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_filter);
        accentColor = getThemeAccentColor(getApplicationContext());

        ArrayList<View> allviews = new ArrayList<View>(((ConstraintLayout) findViewById(R.id.typeButtonBin)).getTouchables());
        for(View item : allviews){
            if(((Button) item) instanceof Button){
                typeButtons.add( (Button) item);
            }
        }

        typeButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button clickedButton = (Button) view;
                int currentColorId = ((ColorDrawable) clickedButton.getBackground()).getColor();
                if(currentColorId == accentColor){
                    clickedButton.setBackgroundColor(Color.BLACK);
                }else{
                    clickedButton.setBackgroundColor(accentColor);
                }
            }
        };

        //set listeners to all buttons
        for(Button button : typeButtons){
            button.setBackgroundColor(accentColor);
            button.setOnClickListener(typeButtonListener);
        }

    }

    public static int getThemeAccentColor (final Context context) {
        final TypedValue value = new TypedValue ();
        context.getTheme ().resolveAttribute (R.attr.colorAccent, value, true);
        return value.data;
    }








}
