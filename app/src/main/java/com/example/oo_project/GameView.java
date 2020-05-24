package com.example.oo_project;

import android.widget.FrameLayout;

public class GameView {

    public static void moveChess_View(Block preBlock,Block targetBlock,Chess chess){
        FrameLayout FM = (FrameLayout) preBlock.getParent();
        FM.removeView(chess);
        FM = (FrameLayout)targetBlock.getParent();
        chess.setLayoutParams(targetBlock.getLayoutParams());
        FM.addView(chess);
    }
}
