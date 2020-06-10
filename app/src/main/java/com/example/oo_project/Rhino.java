package com.example.oo_project;


import android.content.Context;
import android.os.SystemClock;

public class Rhino extends Chess {
    public boolean canBePush = true;
    protected int deathNum = 3;

    String chessName = "犀牛";

    Rhino(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 1, team, positionBlock, true);
    }

    Rhino(Context context, Player team, Block positionBlock) {
        super(context, "rhino", 1, team, positionBlock, true);
    }

    @Override
    public int skill(Chess targetChess) {
        int dir = 0;
        if (targetChess.canBePush == false || targetChess.team == this.team) {
            return 0;
        }
        for (dir = 0; dir < 6; dir++) {
            if (targetChess == this.positionBlock.getNeighbor(dir).chess) {
                break;
            }
        }

        bePush(dir, targetChess);
        while (bePush(dir, targetChess)) {
            moveChess(dir);
        }
        moveChess(dir);
        return 3;
    }

    public int skill() {
        return reChessClick;
    }

    private boolean bePush(int dir, Chess chess) {
        boolean re;
        if (chess.canBePush == false) {
            return false;
        }
        if (!chess.positionBlock.getNeighbor(dir).isEmpty()) {
            re = bePush(dir, chess.positionBlock.getNeighbor(dir).chess);
            if (re == false)
                return false;
        }

        return chess.moveChess(dir);
    }
}