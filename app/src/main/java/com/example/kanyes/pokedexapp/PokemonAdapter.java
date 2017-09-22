package com.example.kanyes.pokedexapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.kanyes.pokedexapp.R.id.image;
import static com.example.kanyes.pokedexapp.R.id.pokeView;

/**
 * Created by Kanyes on 9/21/2017.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.CustomViewHolder> {
    public Context context;
    public static Pokedex.Pokemon pokemon1;
    public boolean list;

    ArrayList<Pokedex.Pokemon> pokemon = new ArrayList<>();
    public PokemonAdapter(ArrayList<Pokedex.Pokemon> pokemon, Context context, boolean list){
        this.pokemon = new ArrayList<>(pokemon);
        this.context = context;
        this.list = list;
    }

    @Override
    public PokemonAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (list) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_layout, parent, false);
            return new CustomViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
            return new CustomViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(PokemonAdapter.CustomViewHolder holder, int position) {
        holder.nameView.setText(pokemon.get(position).name);
        holder.numberView.setText(pokemon.get(position).number);
        // this shit wont fucking work idk y fuck my life
        Glide.with(context).load("http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + pokemon.get(position).number + ".png").into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder{
        public TextView nameView, numberView;
        public ImageView imageView;

        public CustomViewHolder(View view){
            super(view);
            if (list){
                nameView = (TextView) view.findViewById(R.id.nameView);
                numberView = (TextView) view.findViewById(R.id.numberView);
                imageView = (ImageView) view.findViewById(R.id.pokeView);
            }
            else{
                nameView = (TextView) view.findViewById(R.id.nameView2);
                numberView = (TextView) view.findViewById(R.id.numberView2);
                imageView = (ImageView) view.findViewById(R.id.pokePic2);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pokemon1 = pokemon.get(getAdapterPosition());
                    Intent intent = new Intent(context, PokeProfile.class);
                    context.startActivity(intent);
                }
            });
        }

    }
}
