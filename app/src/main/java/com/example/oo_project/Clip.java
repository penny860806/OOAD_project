package com.example.oo_project;

import android.content.Context;
import android.os.SystemClock;

public class Clip extends Chess {
    public boolean canBePush = true;
    protected int deathNum = 4;
    String chessName = "夾子";

    Clip(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 2, team, positionBlock, true);
    }

    Clip(Context context, Player team, Block positionBlock) {
        super(context, "clip", 2, team, positionBlock, true);
    }

    @Override
    public int skill(Chess targetChess) {
        int dir = 0;
        boolean flag = false;
        if (targetChess.canBePush == false || targetChess.team == this.team) {
            return 0;
        }

        for (dir = 0; dir < 6; dir++) {
            if (targetChess.positionBlock.getNeighbor(dir) == this.positionBlock) {
                flag = true;
                break;
            }
        }
        //System.out.println("----------------------------------------dir---------------------------------------------------------------------------"+dir);
        //System.out.println(this.positionBlock.x+"\n"+this.positionBlock.y);
        //Chess tmp=this;
        if (flag != true) {
            return 0;
        }

        while (this.positionBlock.getNeighbor(dir).isEmpty()) {
            moveChess(dir);
            System.out.println("--------------------------------------" + this.positionBlock.x + "\n" + this.positionBlock.y);
            targetChess.moveChess(dir);
        }
//        System.out.println("----------------------------------"+tmp.positionBlock.getNeighbor(1).isEmpty());
//        moveChess(dir);
//        targetChess.moveChess(dir);
//        System.out.println("*********************************************"+this.positionBlock.x+"\n"+this.positionBlock.y);


        return 3;
    }

    public int skill() {
        return reChessClick;
    }
}


