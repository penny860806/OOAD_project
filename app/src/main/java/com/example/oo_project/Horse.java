package com.example.oo_project;


import android.content.Context;
import android.os.SystemClock;

public class Horse extends Chess {

    String chessName = "馬";

    Horse(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 1, team, positionBlock, true);
        deathNum = 3;
    }

    Horse(Context context, Player team, Block positionBlock) {
        this(context, "horse",  team, positionBlock);
    }


    public void setPosition(Block block, Player setTeam) {
        positionBlock = block;
        team = setTeam;
    }

    public int skill(Chess targetChess) {
        if (targetChess == this || targetChess instanceof Rock) {
            Text.PlayChess.messageBlock.setText(Text.PlayChess.notAvailTarget);
            return reChessClick;
        } else if (targetChess.team != this.team) {
            positionBlock.swap(targetChess.positionBlock);
            GameView.changeChess_View(this, targetChess);
            Block temp = targetChess.positionBlock;
            targetChess.positionBlock = positionBlock;
            positionBlock = temp;
            team.skillPoint--;
            return reInitial;
        } else {
            Block[] dest = new Block[2];
            Block temp;
            int dir = isNeighbor(targetChess.positionBlock);
            if (dir != -1) {
                temp = targetChess.positionBlock.getNeighbor(dir);
                if (temp != null) {
                    if (temp.chess == null) {
                        dest[0] = temp;
                        temp = temp.getNeighbor(dir);
                        if (temp != null) {
                            if (temp.chess == null) {
                                dest[1] = temp;
                            }
                        }
                    } else {
                        Text.PlayChess.messageBlock.setText(Text.PlayChess.notAvailTarget);


                        return reChessClick;
                    }
                } else {
                    Text.PlayChess.messageBlock.setText(Text.PlayChess.notAvailTarget);

                    return reChessClick;
                }

                for (int i = 0; i < 2; i++) {
                    if (dest[i] != null) {
                        targetChess.positionBlock.swap(dest[i]);
                        GameView.moveChess_View(targetChess.positionBlock, dest[i], targetChess);
                        targetChess.positionBlock = dest[i];
                    }
                }
                team.skillPoint--;
                return reInitial;
            } else {
                return 0;
            }

        }
    }

    public int skill() {
        Text.PlayChess.messageBlock.setText(Text.PlayChess.clickChessAround);
        return reChessClick;
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