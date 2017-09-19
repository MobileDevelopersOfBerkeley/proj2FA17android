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

    public PokedexAdapter(Context context, ArrayList<Pokedex.Pokemon> pokemonArrayList) {
        this.context = context;
        this.pokemonArrayList = pokemonArrayList;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final Pokedex.Pokemon pokemon = pokemonArrayList.get(position);
        String filename = "http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + pokemon.number + ".png";
        Picasso.with(context).load(filename).into(holder.rowViewImage);
        holder.rowViewText.setText(pokemon.name);
    }

    @Override
    public int getItemCount() {
        return pokemonArrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView rowViewImage;
        TextView rowViewText;

        public CustomViewHolder(View itemView) {
            super(itemView);
            rowViewImage = itemView.findViewById(R.id.rowViewImage);
            rowViewText = itemView.findViewById(R.id.rowViewText);

        }
    }
}
