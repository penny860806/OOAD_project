package com.example.oo_project;

import android.content.Context;

public class Fountain extends Block {
    static Fountain[] list = new Fountain[6];
    public Fountain(Context context) {
        super(context);
        this.setImageResource(R.drawable.fountain);
    }

    static void applySkillPoint_MovePoint(){

    }
}
