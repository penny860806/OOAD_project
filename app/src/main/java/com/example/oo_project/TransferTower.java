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
        if (targetChess.team != this.team || targetChess == this || targetChess instanceof Rock) {
            if(targetChess.team != this.team) {
                Text.PlayChess.messageBlock.setText(Text.PlayChess.notClickEnemy);
            }
            else if(targetChess == this){
                Text.PlayChess.messageBlock.setText("傳送塔不能以自己為技能目標，請選擇另一個有效的目標");
            }
            else if(targetChess instanceof Rock){
                Text.PlayChess.messageBlock.setText("傳送塔不能以巨石為技能目標，請選擇另一個有效的目標");
            }
            return reChessClick;
        }
        else {

            positionBlock.swap(targetChess.positionBlock);
            GameView.changeChess_View(this, targetChess);
            Block temp = targetChess.positionBlock;
            targetChess.positionBlock = positionBlock;
            positionBlock = temp;

            game.checkAllDeath();

            team.skillPoint--;
            return reInitial;
        }
    }

    public int skill() {
        Text.PlayChess.messageBlock.setText("發動傳送塔技能，請點選一名友軍");
        return reChessClick;
    }
}
