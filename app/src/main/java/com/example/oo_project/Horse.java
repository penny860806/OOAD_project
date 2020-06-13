package com.example.oo_project;


import android.content.Context;
import android.os.SystemClock;

public class Horse extends Chess {
    public boolean canBePush = true;
    protected int deathNum = 3;

    String chessName = "馬";

    Horse(Context context, String name, Player team, Block positionBlock) {
        super(context, name, 1, team, positionBlock, true);
        super.deathNum = 3;
    }

    Horse(Context context, Player team, Block positionBlock) {
        this(context, "horse",  team, positionBlock);
    }

    public boolean Death(int x, int y, String team) {

        return false;
    }

    public void setPosition(Block block, Player setTeam) {
        positionBlock = block;
        team = setTeam;
    }

    public int skill(Chess targetChess) {
        if (targetChess == this) {
            return 0;
        } else if (targetChess.team != this.team) {
            positionBlock.swap(targetChess.positionBlock);
            GameView.changeChess_View(this, targetChess);
            Block temp = targetChess.positionBlock;
            targetChess.positionBlock = positionBlock;
            positionBlock = temp;
            return 3;
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
                    } else return 0;
                } else return 0;
                for (int i = 0; i < 2; i++) {
                    if (dest[i] != null) {
                        targetChess.positionBlock.swap(dest[i]);
                        GameView.moveChess_View(targetChess.positionBlock, dest[i], targetChess);
                        targetChess.positionBlock = dest[i];
                    }
                }
                return 3;
            } else return 0;

        }
    }

    public int skill() {
        return 1;
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