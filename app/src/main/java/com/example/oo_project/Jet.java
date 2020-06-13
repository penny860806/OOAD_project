package com.example.oo_project;


import android.content.Context;
import android.os.SystemClock;

public class Jet extends Chess {
    public boolean canBePush = true;


    Jet(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 1, team, positionBlock, true);
        deathNum = 3;
    }

    Jet(Context context, Player team, Block positionBlock) {
        super(context, "jet", 1, team, positionBlock, true);
        super.deathNum = 3;

    }

    @Override
    public int skill(Block targetBlock) {
        boolean temp = moveChess(targetBlock);
        if (temp == true) {

            game.checkAllDeath();

            return Chess.reInitial;
        }
        Text.PlayChess.messageBlock.setText(Text.PlayChess.notClickBlockOuter);

        return Chess.reBlockClick;

    }

    public int skill() {
        team.skillPoint--;
        for (int dir = 0; dir < 6; dir++) {
            if (positionBlock.getNeighbor(dir).chess != null && positionBlock.getNeighbor(dir).chess.canBePush == true) {
                positionBlock.getNeighbor(dir).chess.moveChess(dir);
            }
            game.checkAllDeath();
        }
        Text.PlayChess.messageBlock.setText(Text.PlayChess.clickBlockAround);


        return Chess.reBlockClick;
    }

}