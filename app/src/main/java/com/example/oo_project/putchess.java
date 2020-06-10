package com.example.oo_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PutChess extends AppCompatActivity {
    static Game game;
    static NewGame newGame;
    static  int selectedChessId = -1;
    static  int chessRemain = 1 , round = 1;
    static  Player playerNow;
    final static  int lastRound = 16;
    public PutChess(Game game , NewGame newGame){
        this.game = game;
        this.newGame = newGame;
        playerNow = game.player1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putchess);

        //角色說明文字部分可以上下滾動
        TextView chess_des = (TextView) findViewById(R.id.chess_description);
        chess_des.setMovementMethod(ScrollingMovementMethod.getInstance());

        Button Jet_button = (Button) findViewById(R.id.Jet_button) ;
        Jet_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("putChess","Jet_button clicked");
                selectedChessId = game.JetId;
            }
        });;
        Button TransferTower_button = (Button) findViewById(R.id.TransferTower_button) ;
        TransferTower_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("putChess","TransferTower_button clicked");
                selectedChessId = game.TransferTowerId;
            }
        });;
        Button Rhino_button = (Button) findViewById(R.id.Rhino_button) ;
        Rhino_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("putChess","Rhino_button clicked");
                selectedChessId = game.RhinoId;
            }
        });;
        Button Rock_button = (Button) findViewById(R.id.Rock_button) ;
        Rock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("putChess","Rock_button clicked");
                selectedChessId = game.RockId;
            }
        });;
        Button Clip_button = (Button) findViewById(R.id.Clip_button) ;
        Clip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("putChess","Clip_button clicked");
                selectedChessId = game.ClipId;
            }
        });;
        Button Herculus_button = (Button) findViewById(R.id.Herculus_button) ;
        Herculus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("putChess","Herculus_button clicked");
                selectedChessId = game.HerculusId;
            }
        });;
        Button Horse_button = (Button) findViewById(R.id.Horse_button) ;
        Horse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("putChess","Horse_button clicked");
                selectedChessId = game.HorseId;
            }
        });;
        Button Spy_button = (Button) findViewById(R.id.Spy_button) ;
        Spy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("putChess","Spy_button clicked");
                selectedChessId = game.SpyId;
            }
        });;

//        TextView Jet_num = (TextView) findViewById(R.id.Jet_num);
//        Jet_num.setText("3");
    }
    /**
     * return 0 successfully
     * return 1 no chess selected yet
     * return -1 error
     * return 2 finish
     * @param block
     * @return
     */
    public static int putChess(Block block){
        Chess chess;
        if(block == null){
            Log.i("putChess 113"," block");
            return -1;
        }
        else if(selectedChessId ==-1){
            Log.i("putChess 117","no chess selected yet");
            return 1;
        }
        // put the chess
        else  {
            if (selectedChessId == game.TransferTowerId) {
                if((playerNow == game.player1 && TransferTower.chessRemainP1>0)
                        || (playerNow == game.player2 && TransferTower.chessRemainP2>0)) {
                    chess = new TransferTower(newGame, "transferTower", playerNow, block);
                    GameView.chessView_TransferTower(chess);
                    chess.chessRemainP1--;
                }
                else {
                    Log.i("putChess","the Chess is run out");
                }
            }
            else if (selectedChessId == game.RhinoId) {
                if((playerNow == game.player1 && Rhino.chessRemainP1>0)
                        || (playerNow == game.player2 && Rhino.chessRemainP2>0)) {

                    chess = new Rhino(newGame, "rhino", playerNow, block);
                    GameView.chessView_Rhino(chess);
                }
                else {
                    Log.i("putChess","the Chess is run out");
                }

            }
            else if (selectedChessId == game.RockId) {
                if((playerNow == game.player1 && Rock.chessRemainP1>0)
                        || (playerNow == game.player2 && Rock.chessRemainP2>0)) {

                    chess = new Rock(newGame, "rock", playerNow, block);
                    GameView.chessView_Rock(chess);
                }
                else {
                    Log.i("putChess","the Chess is run out");
                }

            }
            else if (selectedChessId == game.ClipId) {
//                chess = new Rhino(newGame, "Rhino", playerNow, block);
//                GameView.chessView_Rhino(chess);
            }
            else if (selectedChessId == game.HerculusId) {
//                chess = new Rhino(newGame, "Rhino", playerNow, block);
//                GameView.chessView_Rhino(chess);
            }
            else if (selectedChessId == game.JetId) {
                if((playerNow == game.player1 && Jet.chessRemainP1>0)
                        || (playerNow == game.player2 && Jet.chessRemainP2>0)) {

                    chess = new Jet(newGame, "jet", playerNow, block);
                    GameView.chessView_Jet(chess);
                }
                else {
                    Log.i("putChess","the Chess is run out");
                }

            }
            else if (selectedChessId == game.HorseId) {
                if((playerNow == game.player1 && Horse.chessRemainP1>0)
                        || (playerNow == game.player2 && Horse.chessRemainP2>0)) {

                    chess = new Horse(newGame, "horse", playerNow, block);
                    GameView.chessView_Horse(chess);
                }
                else {
                    Log.i("putChess","the Chess is run out");
                }

            }
            else if (selectedChessId == game.SpyId) {
//                chess = new Rhino(newGame, "Rhino", playerNow, block);
//                GameView.chessView_Rhino(chess);

            }
            else {
                Log.i("putChess 146","illegal chess Id");
                return -1;
            }
        }
        checkChangeRound();



        return 0;
    }


    private static void checkChangeRound(){
        if(chessRemain<=0) {
            if (playerNow == game.player1) {
                playerNow = game.player2;
            }
            else if(playerNow == game.player2){
                playerNow = game.player1;
            }
            else{
                Log.i("checkChangeRound","error 213");
            }
            round++;
            chessRemain = 2;
            if(round == lastRound){
                chessRemain=1;
            }
        }
    }
}

