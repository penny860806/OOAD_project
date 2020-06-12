package com.example.oo_project;

import android.widget.TextView;

public class Text {
    public static class PutChess {
        static TextView chessNameBlock , messageBlock;
        static TextView timer;
        static final String TransferTower = "PutChess TransferTower";
        static final String Jet = "PutChess Jet";
        static final String Rhino = "PutChess Rhino";
        static final String Rock = "PutChess Rock";
        static final String Clip = "PutChess Clip";
        static final String Hercules = "PutChess Hercules";
        static final String Horse = "PutChess Horse";
        static final String Spy = "PutChess Spy";
        static final String runOutChess = "你所能放置的這顆棋子已用完";
        static final String noSelectedChess = "請先選取棋子";
        static final String notAvailableBlock_red = "紅方只能放置棋子於上半部(間諜除外)" ,notAvailableBlock_blue = "藍方只能放置棋子於下半部(間諜除外)";
        static final String notAvailableBlockOnFountain = "不可放置棋子於泉上";
        static final String notAvailableBlockSpy = "間諜不可放置棋子於中間線上";
    }
    public static class PlayChess {
        static TextView messageBlock , chessNameBlock;
        static TextView skillPoint_red , movePoint_red , skillPoint_blue , movePoint_blue;
        static TextView timer;
        static final String TransferTower = "ChessInfo TransferTower";
        static final String Jet = "ChessInfo Jet";
        static final String Rhino = "ChessInfo Rhino";
        static final String Rock = "ChessInfo Rock";
        static final String Clip = "ChessInfo Clip";
        static final String Hercules = "ChessInfo Hercules";
        static final String Horse = "ChessInfo Horse";
        static final String Spy = "ChessInfo Spy";
        static final String clickBlock = "請點選一顆格子";
        static final String clickChess = "請點選一顆棋子";
        static final String clickChessAround = "請點選一顆該棋子周圍的棋子";
        static final String clickBlockAround = "請點選一顆該格子周圍的棋子";
        static final String notClickRock = "巨石不能被選為技能對象，請選擇另一顆有效棋子";
        static final String notClickSameTeam = "屬於你的棋子不能被選為技能對象，請選擇另一顆有效棋子";
        static final String notClickEnemyTeam = "對手的棋子不能被選為技能對象，請選擇另一顆有效棋子";
        static final String notClickChessOuter = "不屬於周圍的棋子不能被選為技能對象，請選擇另一顆有效棋子";
        static final String notClickBlockOuter = "不屬於周圍的格子不能被選為技能對象，請選擇另一顆有效棋子";
        static void chessInfo(Chess chess){
            if(chess instanceof TransferTower){
                chessNameBlock.setText("傳送塔");
                messageBlock.setText(TransferTower);
            }
            else if(chess instanceof Jet){
                chessNameBlock.setText("氣場");
                messageBlock.setText(Jet);
            }
            else if(chess instanceof Rhino){
                chessNameBlock.setText("犀牛");
                messageBlock.setText(Rhino);
            }
            else if(chess instanceof Rock){
                chessNameBlock.setText("巨石");
                messageBlock.setText(Rock);
            }
            else if(chess instanceof Clip){
                chessNameBlock.setText("夾子");
                messageBlock.setText(Clip);
            }
            else if(chess instanceof Hercules){
                chessNameBlock.setText("力士");
                messageBlock.setText(Hercules);
            }
            else if(chess instanceof Horse){
                chessNameBlock.setText("馬");
                messageBlock.setText(Horse);
            }
            else if(chess instanceof Spy){
                chessNameBlock.setText("間諜");
                messageBlock.setText(Spy);
            }
        }

    }



}
