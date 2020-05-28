package com.example.oo_project;


import android.content.Context;
import android.os.SystemClock;

public class Horse extends Chess {
    public boolean canBePush = true;
    protected int deathNum = 3;
    String chessName = "馬";

    Horse(Context context, String name, int moveRange, Player team, Block positionBlock) {
        super(context, name, moveRange, team, positionBlock);
    }

    public boolean Death(int x, int y, String team) {

        if (myTeam == team) {
            if (deathNum <= x) {
                return true;
            } else return false;
        } else {
            if (deathNum <= y)
                return true;
            else return false;
        }
    }

    public void setPosition(Block block, String setTeam) {
        positionBlock = block;
        myTeam = setTeam;
    }

    public boolean skill(Chess targetChess) {
        if (targetChess == this) {
            return false;
        } else if (targetChess.team != this.team) {
            positionBlock.swap(targetChess.positionBlock);
            GameView.changeChess_View(this, targetChess);
            Block temp = targetChess.positionBlock;
            targetChess.positionBlock = positionBlock;
            positionBlock = temp;
            return true;
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
                    } else return false;
                } else return false;
                for (int i = 0; i < 2; i++) {
                    if (dest[i] != null) {
                        targetChess.positionBlock.swap(dest[i]);
                        GameView.moveChess_View(targetChess.positionBlock, dest[i], targetChess);
                        targetChess.positionBlock = dest[i];
                    }
                }
                return true;
            } else return false;

        }
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