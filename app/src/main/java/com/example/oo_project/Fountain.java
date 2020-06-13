package com.example.oo_project;

import android.content.Context;

public class Fountain extends Block {
    public Fountain(Context context) {
        super(context);
        this.setImageResource(R.drawable.fountain);
    }
    public static boolean checkCanEnter(Chess chess){
        if(chess instanceof Rock||chess instanceof Spy){
            return false;
        }else{
            return true;
        }
    }

}
