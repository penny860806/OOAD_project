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
    public static void changeChess_View(Chess chess_1,Chess chess_2){
        FrameLayout FM1 = (FrameLayout) chess_1.getParent();
        FrameLayout FM2 = (FrameLayout) chess_2.getParent();
        FrameLayout.LayoutParams params1 = (FrameLayout.LayoutParams)chess_1.getLayoutParams();
        FrameLayout.LayoutParams params2 = (FrameLayout.LayoutParams)chess_2.getLayoutParams();
        FM1.removeView(chess_1);
        FM2.removeView(chess_2);
        chess_1.setLayoutParams(params2);
        chess_2.setLayoutParams(params1);
        FM1.addView(chess_2);
        FM2.addView(chess_1);
    }
}
