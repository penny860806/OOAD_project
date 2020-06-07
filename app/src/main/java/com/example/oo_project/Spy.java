package com.example.oo_project;
import android.content.Context;
import android.os.SystemClock;
public class Spy extends Chess {
    public boolean canBePush = true;
    protected int deathNum = 2;
    String chessName = "間碟";

    Spy(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 4, team, positionBlock, true);
    }
    @Override
    public int skill(Block targetBlock){
        if (moveChess(targetBlock)){return 3;}
        return 0;
        }
    public int skill(){
        return reBlockClick;
    }
    }

