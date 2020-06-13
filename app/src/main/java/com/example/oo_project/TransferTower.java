package com.example.oo_project;

import android.content.Context;

public class TransferTower extends Chess {

    TransferTower(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 1, team, positionBlock, true);
    }

    TransferTower(Context context, Player team, Block positionBlock) {
        super(context, "transfertower", 1, team, positionBlock, true);
    }


    @Override
    public int skill(Chess targetChess) {
        if (targetChess.team != this.team || targetChess == this) {
            Text.PlayChess.messageBlock.setText(Text.PlayChess.notClickEnemy);
            return reChessClick;
        } else {
            team.skillPoint--;
            positionBlock.swap(targetChess.positionBlock);
            GameView.changeChess_View(this, targetChess);
            Block temp = targetChess.positionBlock;
            targetChess.positionBlock = positionBlock;
            positionBlock = temp;

            game.checkAllDeath();

            return reInitial;
        }
    }

    public int skill() {
        return reChessClick;
    }
}
