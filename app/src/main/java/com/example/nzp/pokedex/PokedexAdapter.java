package com.example.nzp.pokedex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by nzp on 9/19/17.
 */

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<Pokedex.Pokemon> pokemonArrayList;
    private DisplayStyle displayStyle;

    public PokedexAdapter(Context context, ArrayList<Pokedex.Pokemon> pokemonArrayList, DisplayStyle displayStyle) {
        this.context = context;
        this.pokemonArrayList = pokemonArrayList;
        this.displayStyle = displayStyle;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = (displayStyle == DisplayStyle.LIST) ? R.layout.row_view : R.layout.grid_view;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final Pokedex.Pokemon pokemon = pokemonArrayList.get(position);
        String filename = "http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + pokemon.number + ".png";
        Picasso.with(context).load(filename).into(holder.listImageView);
        holder.listTextView.setText(pokemon.name);
    }

    @Override
    public int getItemCount() {
        return pokemonArrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView listImageView;
        TextView listTextView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            listImageView = itemView.findViewById(R.id.listImageView);
            listTextView = itemView.findViewById(R.id.listTextView);

        }
    }
}
