package com.example.oo_project;

import android.content.Context;

public class Castle extends Block {
    private int occupied_round = 0;
    Chess chessLast;

    public Castle(Context context) {
        super(context);
        this.setImageResource(R.drawable.castle);
    }
    public boolean checkCanEnter(Chess chess){
        if(chess.chessName=="rock"||chess.chessName=="spy"){
            return false;
        }else{
            return true;
        }
    }
    public int getOccupiedRound(){
        return occupied_round;
    }
    public void setOccupiedRound(int round){
        occupied_round = round;
    }

}
