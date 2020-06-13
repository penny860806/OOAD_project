package com.example.oo_project;


import android.content.Context;
import android.os.SystemClock;

public class Rock extends Chess {
    protected int deathNum = 3;

    String chessName = "巨石";

    Rock(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 1, team, positionBlock, false);
        super.deathNum = 4;
    }

    Rock(Context context, Player team, Block positionBlock) {
        this(context, "rock",  team, positionBlock );
    }


    @Override
    public int skill() {
        return 3;
    }
}