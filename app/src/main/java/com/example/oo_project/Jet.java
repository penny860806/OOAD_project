package com.example.oo_project;


import android.content.Context;
import android.os.SystemClock;

public class Jet extends Chess {
    public boolean canBePush = true;
    protected int deathNum = 3;

    String chessName = "氣場";

    Jet(Context context, String name, Player team, Block positionBlock) {
        super(context, "氣場", 1, team, positionBlock, true);
    }

    @Override
    public int skill(Block targetBlock) {
        boolean temp = moveChess(targetBlock);
        if(temp == true){
            return 3;
        }
        return 0;

    }
    public int skill( ) {
        for(int dir=0 ; dir<6 ; dir++){
            if(positionBlock.getNeighbor(dir).chess != null && positionBlock.getNeighbor(dir).chess.canBePush==true){
                positionBlock.getNeighbor(dir).chess.moveChess(dir);
            }
        }
        return 2;
    }

}