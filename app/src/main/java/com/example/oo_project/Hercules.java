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
        targetBlock = null;
        targetChess = null;
        super.deathNum=4;
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
        GameView.moveChess_View(targetChess.positionBlock,targetBlock,targetChess);
        targetChess.positionBlock = targetBlock;
        return 3;
    }

    public int skill() {
        targetChess = null;
        targetBlock = null;
        if (targetChess == null) {
            Text.PlayChess.messageBlock.setText(Text.PlayChess.clickChessAround);
            return reChessClick;
        } else return reError;
    }

    public int skill(Chess targetChess) {
        if (targetBlock == null && targetChess != null && targetChess.team != this.team &&
            this.isNeighbor(targetChess.positionBlock) >= 0) {
            this.targetChess = targetChess;
            Text.PlayChess.messageBlock.setText(Text.PlayChess.clickBlockAround);

            return reBlockClick;
        } else {
            Text.PlayChess.messageBlock.setText(Text.PlayChess.notAvailTarget);
            return reChessClick;
        }
    }

    public int skill(Block targetBlock) {
        if (isNeighbor(targetBlock) >= 0 && targetBlock.chess == null) {
            skillrunner(targetChess, targetBlock);
            team.skillPoint--;
            targetChess = null;

        }
        else {
            Text.PlayChess.messageBlock.setText(Text.PlayChess.notAvailTarget);
            return reBlockClick;
        }
        game.checkAllDeath();
        return reInitial;
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


