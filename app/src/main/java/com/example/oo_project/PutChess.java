package com.example.oo_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PutChess extends AppCompatActivity {
    static Game game;
    static NewGame newGame;
    static int selectedChessId = -1;
    static TextView[] chessText ;
    static int chessRemain = 1, round = 1;
    static Player playerNow;
    final static int lastRound = 2;
    private static int[][] chessAmount;

    public PutChess(Game game, NewGame newGame) {
        this.game = game;
        this.newGame = newGame;
        playerNow = game.player1;
        chessAmount = new int[2][8];
        chessText = new TextView[8];
        for (int i = 0; i < chessAmount.length; i++) {
            for (int j = 0; j < chessAmount[i].length; j++) {
                chessAmount[i][j] = 4;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putchess);


    }

    /**
     * return 0 successfully
     * return 1 no chess selected yet
     * return -1 error
     * return 2 finish
     *
     * @param block
     * @return
     */
    public static int putChess(Block block) {
        Chess chess;
        if (block == null) {
            Log.i("putChess 113", " block");
            return -1;
        }
        if (selectedChessId == -1) {
            Log.i("putChess 117", "no chess selected yet");
            Text.PutChess.messageBlock.setText(Text.PutChess.noSelectedChess);
            return 1;
        }

        //check the selected block is illegal
        {
            if(selectedChessId == Game.SpyId  ){
                if(block.y == 5) {
                    Text.PutChess.messageBlock.setText(Text.PutChess.notAvailableBlockSpy);
                    return 0;
                }
            }
            else if (playerNow == game.player1 && block.y <= 5){
                Text.PutChess.messageBlock.setText(Text.PutChess.notAvailableBlock_blue);
                return 0;
            }
            else if(playerNow == game.player2 && block.y>=5 ) {
                Text.PutChess.messageBlock.setText(Text.PutChess.notAvailableBlock_red);
                return 0;
            }
            else if(block instanceof Fountain){
                Text.PutChess.messageBlock.setText(Text.PutChess.notAvailableBlockOnFountain);
                return 0;
            }
        }
        // put the chess
        if ((playerNow == game.player1 && chessAmount[0][selectedChessId] > 0)
                || (playerNow == game.player2 && chessAmount[1][selectedChessId] > 0)) {
            if (selectedChessId == game.TransferTowerId) {
                chess = new TransferTower(newGame, playerNow, block);
                GameView.chessView_TransferTower(chess);
                chessRemain--;
            } else if (selectedChessId == game.RhinoId) {
                chess = new Rhino(newGame, playerNow, block);
                GameView.chessView_Rhino(chess);
                chessRemain--;
            } else if (selectedChessId == game.RockId) {
                chess = new Rock(newGame, playerNow, block);
                GameView.chessView_Rock(chess);
                chessRemain--;
            } else if (selectedChessId == game.ClipId) {
                chess = new Clip(newGame, playerNow, block);
                GameView.chessView_Clip(chess);
                chessRemain--;
            } else if (selectedChessId == game.HerculesId) {
                chess = new Hercules(newGame, playerNow, block);
                GameView.chessView_Hercules(chess);
                chessRemain--;
            } else if (selectedChessId == game.JetId) {
                chess = new Jet(newGame, playerNow, block);
                GameView.chessView_Jet(chess);
                chessRemain--;
            } else if (selectedChessId == game.HorseId) {
                chess = new Horse(newGame, playerNow, block);
                GameView.chessView_Horse(chess);
                chessRemain--;
            } else if (selectedChessId == game.SpyId) {
                chess = new Spy(newGame, playerNow, block);
                GameView.chessView_Spy(chess);
                chessRemain--;
            }

            if(playerNow == game.player1 ){
                chessAmount[0][selectedChessId]--;
            }
            else {
                chessAmount[1][selectedChessId]--;
            }
        }
        else{
            Text.PutChess.messageBlock.setText(Text.PutChess.runOutChess);
            Text.PutChess.chessNameBlock.setText("");
            return 0;
        }

        selectedChessId = -1;

        return checkChangeRound();
    }


    private static int checkChangeRound() {
        int player = 0;
        if (chessRemain <= 0) {
            if (playerNow == game.player1) {
                playerNow = game.player2;

            } else if (playerNow == game.player2) {
                playerNow = game.player1;
            } else {
                Log.i("checkChangeRound", "error 213");
                return -1;
            }
            System.out.println(game.whoseRound().ID);
            round++;
            chessRemain = 2;
            if (round == lastRound) {
                chessRemain = 1;
            }
            if(round > lastRound){
                return 2; //finish
            }
        }
        if(playerNow == game.player1){
            player = 0;
        }
        else {
            player = 1;
        }
        for(int i=0 ; i<chessText.length ; i++){
            String stringValue = Integer.toString(chessAmount[player][i]);
            chessText[i].setText(stringValue);
        }
        Text.PutChess.messageBlock.setText("");
        Text.PutChess.chessNameBlock.setText("");
        if(playerNow == game.player1) {
            game.GM.lowestLayout.setBackgroundColor(Color.parseColor("#ff33b5e5")); //blue player backgound (holo_blue_light)

        }
        if(playerNow == game.player2) {
            game.GM.lowestLayout.setBackgroundColor(Color.parseColor("#ffff4444")
                    //blue player backgound (holo_blue_light)
            );
        }

        return 0;
    }

}

