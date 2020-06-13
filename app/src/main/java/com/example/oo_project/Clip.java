package com.example.oo_project;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

public class Clip extends Chess {
    public boolean canBePush = true;
    protected int deathNum = 4;
    String chessName = "夾子";

    Clip(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 2, team, positionBlock, true);
        super.deathNum = 4;

    }

    Clip(Context context, Player team, Block positionBlock) {
        this(context, "clip",  team, positionBlock);
    }

    @Override
    public int skill(Chess targetChess) {
        int dir = 0;
        boolean flag = false;
        if (targetChess.canBePush == false || targetChess.team == this.team) {
            Text.PlayChess.messageBlock.setText(Text.PlayChess.notAvailTarget+"27");
            return 0;
        }
        for (dir = 0; dir < 6; dir++) {
            if(targetChess.positionBlock == null){
                Log.i("clip","error34");
            }
            if (targetChess.positionBlock.getNeighbor(dir) == this.positionBlock) {
                flag = true;
                break;
            }
        }
        if (flag != true) {
            Text.PlayChess.messageBlock.setText(Text.PlayChess.notAvailTarget+"47");
            return reError;
        }

        while (this.positionBlock.getNeighbor(dir).isEmpty()) {
            moveChess(dir);
            System.out.println("--------------------------------------" + this.positionBlock.x + "\n" + this.positionBlock.y);
            if(targetChess.ImDead == false) {
                targetChess.moveChess(dir);
            }
            if(ImDead == true ){
                break;
            }
        }


        return reInitial;
    }

    public int skill() {
        Text.PlayChess.messageBlock.setText(Text.PlayChess.clickChessAround+"70");
        return reChessClick;
    }
}


