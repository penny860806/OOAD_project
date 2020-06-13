package com.example.oo_project;

import android.content.Context;
import android.os.SystemClock;

public class Spy extends Chess {
    public boolean canBePush = true;
    protected int deathNum = 2;
    private static int step = 4;
    String chessName = "間碟";

    Spy(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 4, team, positionBlock, true);
        this.deathTeam = true;
        this.deathNum = 2;
    }

    Spy(Context context, Player team, Block positionBlock) {
        this(context, "spy",  team, positionBlock);

    }

    @Override
    public int skill(Block targetBlock) {
        if (moveChess(targetBlock)) {
            step -= 1;
            if (step == 0) {
                step = 4;
                return 3;
            }
            return reBlockClick;
        } else {
            return 0;
        }
    }

    public int skill() {
        return reBlockClick;
    }
}

