package com.example.oo_project;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class GameView {
    static Game game;
    public static LinearLayout View1, View2 ;
    public static LinearLayout masterView;

    public GameView(Game game) {
        this.game = game;
    }

    public static void moveChess_View(Block preBlock, Block targetBlock, Chess chess) {
        FrameLayout FM = (FrameLayout) preBlock.getParent();
        FM.removeView(chess);
        FM = (FrameLayout) targetBlock.getParent();
        chess.setLayoutParams(targetBlock.getLayoutParams());
        FM.addView(chess);
    }

    public static void changeChess_View(Chess chess_1, Chess chess_2) {
        FrameLayout FM1 = (FrameLayout) chess_1.getParent();
        FrameLayout FM2 = (FrameLayout) chess_2.getParent();
        FrameLayout.LayoutParams params1 = (FrameLayout.LayoutParams) chess_1.getLayoutParams();
        FrameLayout.LayoutParams params2 = (FrameLayout.LayoutParams) chess_2.getLayoutParams();
        FM1.removeView(chess_1);
        FM2.removeView(chess_2);
        chess_1.setLayoutParams(params2);
        chess_2.setLayoutParams(params1);
        FM1.addView(chess_2);
        FM2.addView(chess_1);
    }
    public static void removeChess_View(Chess chess){
        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        layout.removeView(chess);
    }
    public static void chessView_Red(Chess chess) {
        FrameLayout.LayoutParams chessParams = (FrameLayout.LayoutParams) chess.positionBlock.getLayoutParams();
        chess.setLayoutParams(chessParams);
        chess.setImageResource(R.drawable.chess_red);
        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        chess.positionBlock.chess = chess;
        layout.addView(chess);
    }

    public static void chessView_Blue(Chess chess) {
        FrameLayout.LayoutParams chessParams = (FrameLayout.LayoutParams) chess.positionBlock.getLayoutParams();
        chess.setLayoutParams(chessParams);
        chess.setImageResource(R.drawable.chess_blue);
        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        chess.positionBlock.chess = chess;
        layout.addView(chess);
    }

    public static void chessView_TransferTower(Chess chess) {
        FrameLayout.LayoutParams chessParams = (FrameLayout.LayoutParams) chess.positionBlock.getLayoutParams();
        chess.setLayoutParams(chessParams);
        if (chess.team == game.player1) {//check player
            chess.setImageResource(R.drawable.tower_blue);
        } else if (chess.team == game.player2) {
            chess.setImageResource(R.drawable.tower_red);
        } else {
            Log.i("chessView_TransferTower", "illegal player input.");
        }

        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        chess.positionBlock.chess = chess;
        layout.addView(chess);
    }

    public static void chessView_Rhino(Chess chess) {
        FrameLayout.LayoutParams chessParams = (FrameLayout.LayoutParams) chess.positionBlock.getLayoutParams();
        chess.setLayoutParams(chessParams);
        if (chess.team == game.player1) {//check player
            chess.setImageResource(R.drawable.rhinoceros_blue);
        } else if (chess.team == game.player2) {
            chess.setImageResource(R.drawable.rhinoceros_red);
        } else {
            Log.i("chessView_Rhino", "illegal player input.");
        }

        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        chess.positionBlock.chess = chess;
        layout.addView(chess);
    }

    public static void chessView_Rock(Chess chess) {
        FrameLayout.LayoutParams chessParams = (FrameLayout.LayoutParams) chess.positionBlock.getLayoutParams();
        chess.setLayoutParams(chessParams);
        if (chess.team == game.player1) {//check player
            chess.setImageResource(R.drawable.rock_blue);
        } else if (chess.team == game.player2) {
            chess.setImageResource(R.drawable.rock_red);
        } else {
            Log.i("chessView_Rock", "illegal player input.");
        }

        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        chess.positionBlock.chess = chess;
        layout.addView(chess);
    }

    public static void chessView_Clip(Chess chess) {
        FrameLayout.LayoutParams chessParams = (FrameLayout.LayoutParams) chess.positionBlock.getLayoutParams();
        chess.setLayoutParams(chessParams);
        if (chess.team == game.player1) {//check player
            chess.setImageResource(R.drawable.clip_blue);
        } else if (chess.team == game.player2) {
            chess.setImageResource(R.drawable.clip_red);
        } else {
            Log.i("chessView_Rock", "illegal player input.");
        }

        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        chess.positionBlock.chess = chess;
        layout.addView(chess);
    }

    public static void chessView_Hercules(Chess chess) {
        FrameLayout.LayoutParams chessParams = (FrameLayout.LayoutParams) chess.positionBlock.getLayoutParams();
        chess.setLayoutParams(chessParams);
        if (chess.team == game.player1) {//check player
            chess.setImageResource(R.drawable.hercules_blue);
        } else if (chess.team == game.player2) {
            chess.setImageResource(R.drawable.hercules_red);
        } else {
            Log.i("chessView_Hercules", "illegal player input.");
        }

        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        chess.positionBlock.chess = chess;
        layout.addView(chess);
    }

    public static void chessView_Jet(Chess chess) {
        FrameLayout.LayoutParams chessParams = (FrameLayout.LayoutParams) chess.positionBlock.getLayoutParams();
        chess.setLayoutParams(chessParams);
        if (chess.team == game.player1) {//check player
            chess.setImageResource(R.drawable.auro_blue);
        } else if (chess.team == game.player2) {
            chess.setImageResource(R.drawable.auro_red);
        } else {
            Log.i("chessView_Jet", "illegal player input.");
        }

        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        chess.positionBlock.chess = chess;
        layout.addView(chess);
    }

    public static void chessView_Horse(Chess chess) {
        FrameLayout.LayoutParams chessParams = (FrameLayout.LayoutParams) chess.positionBlock.getLayoutParams();
        chess.setLayoutParams(chessParams);
        if (chess.team == game.player1) {//check player
            chess.setImageResource(R.drawable.horse_blue);
        } else if (chess.team == game.player2) {
            chess.setImageResource(R.drawable.horse_red);
        } else {
            Log.i("chessView_Horse", "illegal player input.");
        }

        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        chess.positionBlock.chess = chess;
        layout.addView(chess);
    }

    public static void chessView_Spy(Chess chess) {
        FrameLayout.LayoutParams chessParams = (FrameLayout.LayoutParams) chess.positionBlock.getLayoutParams();
        chess.setLayoutParams(chessParams);
        if (chess.team == game.player1) {//check player
            chess.setImageResource(R.drawable.spy_blue);
        } else if (chess.team == game.player2) {
            chess.setImageResource(R.drawable.spy_red);
        } else {
            Log.i("chessView_Spy", "illegal player input.");
        }

        FrameLayout layout = (FrameLayout) chess.positionBlock.getParent();
        chess.positionBlock.chess = chess;
        layout.addView(chess);
    }


    public static void changePage(){
        masterView.removeView(View2);
        masterView.addView(View1);
//        Text.timer.setTimer(Text.PlayChess.timer);
//        Text.timer.startTimer();
        //start timer
        NewGame.customTimer.startTimer();
    }
}
