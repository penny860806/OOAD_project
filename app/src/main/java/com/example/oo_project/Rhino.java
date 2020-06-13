package com.example.oo_project;


import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

public class Rhino extends Chess {
    public boolean canBePush = true;
    protected int deathNum = 3;

    String chessName = "犀牛";

    Rhino(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 1, team, positionBlock, true);
        super.deathNum = 5;
    }

    Rhino(Context context, Player team, Block positionBlock) {
        this(context, "rhino", team, positionBlock);
    }

    @Override
    public int skill(Chess targetChess) {
        int dir = 0;
        boolean avail = false;
        if (targetChess.canBePush == false || targetChess.team == this.team) {
            if(targetChess.canBePush == false){
                Text.PlayChess.messageBlock.setText(Text.PlayChess.notClickRock);
            }
            else {
                Text.PlayChess.messageBlock.setText(Text.PlayChess.notClickSameTeam);
            }
            return Chess.reChessClick;
        }
        for (dir = 0; dir < 6; dir++) {
            if (targetChess == this.positionBlock.getNeighbor(dir).chess) {
                avail = true;
                break;
            }
        }
        if(avail == false){
            Log.i("Rhino","42");
            Text.PlayChess.messageBlock.setText(Text.PlayChess.notClickChessOuter);
            return Chess.reChessClick;

        }

        boolean temp = true , A , B;
        while (temp) { // if no push can be apply and no chess dead, than break
            A = bePush(dir, this);
            B = game.checkAllDeath();
            temp = A || B;
            if(this.ImDead == true){
                break;
            }
        }
        return Chess.reInitial;
    }

    public int skill() {
        Text.PlayChess.messageBlock.setText(Text.PlayChess.clickChessAround);

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