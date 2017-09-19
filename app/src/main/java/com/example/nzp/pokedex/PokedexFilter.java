package com.example.nzp.pokedex;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by nzp on 9/19/17.
 *
 * Filters the Pokedex based on a CharSequence.
 */

public class PokedexFilter extends Filter {

    private PokedexAdapter adapter;
    private ArrayList<Pokedex.Pokemon> all;
    private ArrayList<Pokedex.Pokemon> filtered;

    public PokedexFilter(PokedexAdapter adapter, ArrayList<Pokedex.Pokemon> all) {
        this.adapter = adapter;
        this.all = all;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        String charString = charSequence.toString();
        if (charString.isEmpty()) {
            filtered = all;
        } else {
            //First pass gets most relevant pokemon
            ArrayList<Pokedex.Pokemon> filteredList = new ArrayList<>();
            for (Pokedex.Pokemon pokemon : all) {
                //Add if number or name is similar
                if (pokemon.name.toLowerCase().startsWith(charString.toLowerCase()) || pokemon.number.startsWith(charString)) {
                    filteredList.add(pokemon);
                }
            }
            filtered = filteredList;
        }

        FilterResults filterResults = new FilterResults();
        filterResults.values = filtered;
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        filtered = (ArrayList<Pokedex.Pokemon>) filterResults.values;
        adapter.setData(filtered);
    }
}
