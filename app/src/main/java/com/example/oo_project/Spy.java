package com.example.oo_project;

import android.content.Context;
import android.os.SystemClock;

public class Spy extends Chess {
    public boolean canBePush = true;
    private static int step = 4;
    String chessName = "間碟";

    Spy(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 4, team, positionBlock, true);
        this.deathTeam = true;
        deathNum = 2;
    }

    Spy(Context context, Player team, Block positionBlock) {
        this(context, "spy",  team, positionBlock);

    }

    @Override
    public int skill(Block targetBlock) {
        if(targetBlock instanceof Fountain && !Fountain.checkCanEnter(this)){
            Text.PlayChess.messageBlock.setText("這個棋子不能進入泉，請選擇另一個格子");
            return reBlockClick;
        }
        if(targetBlock instanceof Castle && !Castle.checkCanEnter(this)){
            Text.PlayChess.messageBlock.setText("這個棋子不能進入城，請選擇另一個格子");
            return reBlockClick;
        }
        if (moveChess(targetBlock)) {
            if(step == 4){
                team.skillPoint-=2;
            }

            step --;
            if (step == 0) {
                step = 4;

                return reInitial;
            }
            Text.PlayChess.messageBlock.setText("間諜技能:剩餘"+Integer.toString(step)+"步(按取消選取終止移動)");


            return reBlockClick;
        } else {
            Text.PlayChess.messageBlock.setText(Text.PlayChess.notAvailTarget);

            return reBlockClick;
        }
    }

    public int skill() {
        Text.PlayChess.messageBlock.setText(Text.PlayChess.clickBlockAround);
        step = 4;
        return reBlockClick;
    }
}

