package com.example.nzp.pokedex;

import java.util.EnumSet;
import java.util.Set;

import static com.example.nzp.pokedex.Pokemon.Type;

/**
 * Created by nzp on 9/19/17.
 *
 * Holds information about what the user whats to filter (whether or not they want each type, plus
 * minimum ATK, DEF, and HP values.
 * Also provides a 30 character (or less) description of the filter to display.
 */

public class PokedexFilter {

    public static final Set<Pokemon.Type> ALL_TYPES = EnumSet.allOf(Type.class);

    private Set<Pokemon.Type> allowedTypes;
    private int minAtk, minDef, minHP;

    /* Empty constructor filters nothing. */
    public PokedexFilter() {
        this(ALL_TYPES, -1, -1, -1);
    }

    public PokedexFilter(Set<Pokemon.Type> allowedTypes, int minAtk, int minDef, int minHP) {
        this.allowedTypes = allowedTypes;
        this.minAtk = minAtk;
        this.minDef = minDef;
        this.minHP = minHP;
    }

    public int getMinAtk() {
        return minAtk;
    }

    public int getMinDef() {
        return minDef;
    }

    public int getMinHP() {
        return minHP;
    }

    public Set<Pokemon.Type> getAllowedTypes() {
        return allowedTypes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean atkFiltered = getMinAtk() > -1;
        boolean defFiltered = getMinDef() > -1;
        boolean hpFiltered = getMinHP() > -1;
        int numTypes = getAllowedTypes().size();

        //Type filters
        if (numTypes == 0) {
            builder.append("Showing no types");
        } else if (numTypes == 1) {
            String type = getAllowedTypes().iterator().next().name();
            type = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
            builder.append("Showing ").append(type).append(" types");
        } else if (numTypes == ALL_TYPES.size()) {
            builder.append("Showing all types");
        } else {
            builder.append("Showing ").append(numTypes).append(" types");
        }

        //ATK, DEF, and HP filters
        if (atkFiltered) {
            builder.append(", ATK > ").append(getMinAtk());
        }
        if (defFiltered) {
            builder.append(", DEF > ").append(getMinDef());
        }
        if (hpFiltered) {
            builder.append(", HP > ").append(getMinHP());
        }

        builder.append(".");
        return builder.toString();
    }
}
