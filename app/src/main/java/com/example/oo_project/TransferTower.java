package com.example.oo_project;

import android.content.Context;

public class TransferTower extends Chess {

    TransferTower(Context context, String name, int moveRange, Player team, Block positionBlock) {
        super(context, name, moveRange, team, positionBlock);
    }

    @Override
    public boolean skill(Chess targetChess) {
        if (targetChess.team != this.team || targetChess == this) {
            return false;
        } else {
            positionBlock.swap(targetChess.positionBlock);
            GameView.changeChess_View(this, targetChess);
            Block temp = targetChess.positionBlock;
            targetChess.positionBlock = positionBlock;
            positionBlock = temp;
            return true;
        }
    }
}
