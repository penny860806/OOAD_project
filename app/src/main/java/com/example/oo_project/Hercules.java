package com.example.oo_project;


import android.content.Context;
import android.os.SystemClock;

public class Hercules extends Chess {
    public boolean canBePush = true;

    protected int deathNum = 4;
    private Chess targetChess;
    private Block targetBlock;
    String chessName = "力士";

    Hercules(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 1, team, positionBlock, true);
        targetBlock = null;
        targetChess = null;
        super.deathNum=4;


    }

    Hercules(Context context, Player team, Block positionBlock) {
        this(context, "hercules",  team, positionBlock);
    }

    public boolean Death(int x, int y, Player team) {
/*
        if (myTeam == team) {
            if (deathNum <= x) {
                return true;
            } else return false;
        } else {
            if (deathNum <= y)
                return true;
            else return false;
        }*/
        return true;
    }


    /**
     * 0: error
     * 1: 要求chess
     * 2: 要求Block
     * 3: back to initial
     *
     * @param targetChess
     * @param targetBlock
     * @return
     */
    public int skillrunner(Chess targetChess, Block targetBlock) {
        targetChess.positionBlock.swap(targetBlock);
        return 3;
    }

    public int skill() {
        if (targetChess == null) {
            return 1;
        } else return 3;
    }

    public int skill(Chess targetChess) {
        if (targetBlock == null && targetChess != null && targetChess.team != this.team &&
                this.isNeighbor(targetChess.positionBlock) >= 0) {
            this.targetChess = targetChess;
            return 2;
        } else {
            return 1
                    ;
        }
    }

    public int skill(Block targetBlock) {
        if (this.isNeighbor(targetBlock) >= 0 && targetBlock.player == null) {
            this.skillrunner(targetChess, targetBlock);
        }
        this.targetChess = null;
        return 3;
    }

    public int isNeighbor(Block block) {
        for (int i = 0; i < 6; i++) {
            Block t = this.positionBlock.getNeighbor(i);
            if (t != null) {
                if (t == block) return i;
            }
        }
        return -1;
    }
}


