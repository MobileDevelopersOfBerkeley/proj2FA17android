package com.example.nzp.pokedex;

/**
 * Created by nzp on 9/19/17.
 *
 * An enum representing the possible styles to display the list of Pokemon, LIST and GRID.
 */

public enum DisplayStyle {

    LIST,
    GRID;

    /* Gets the other style. */
    public static DisplayStyle other(DisplayStyle thisStyle) {
        if (thisStyle == LIST) {
            return GRID;
        }
        return LIST;
    }


}
