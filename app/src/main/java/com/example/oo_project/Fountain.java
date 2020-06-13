package com.example.oo_project;

import android.content.Context;

public class Fountain extends Block {
    public Fountain(Context context) {
        super(context);
        this.setImageResource(R.drawable.fountain);
    }
    public boolean checkCanEnter(Chess chess){
        if(chess.chessName=="rock"||chess.chessName=="spy"){
            return false;
        }else{
            return true;
        }
    }

}
