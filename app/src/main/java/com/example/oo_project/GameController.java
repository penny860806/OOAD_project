package com.example.oo_project;

import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameController {
    /**
     * state:
     * 0: initial
     * 1: chess menu
     * 2: move state(have Chess, require targetBlock, manipulate move by call moveChess, )
     * 3: skill state
     */

    final static int initial = 0, chessMenu = 1, moveState = 2, skillState = 3 , putChessState = 4;
    final static int noButton = 0,skillButton = 1, moveButton = 2, cancelButton = 3, endRoundButton = 4;
    private final static int nothingL=0, buttonL = 1,blockL = 2, chessL = 3 ,chess2L = 4;
    private static int state = putChessState;
    private static int clickButton = noButton;
    private static int listenFor = blockL;
//    private static LinearLayout masterView;
//    private static LinearLayout View1, View2;

    private static Game game = null;
    static Block clickBlock = null;
    static Chess clickChess = null , clickChess2 = null;
//    static TextView chessName ,

    GameController(Game game) {
        this.game = game;
    }

    public static boolean setClickedBlock(Block block) {
        if(listenFor == blockL){
            clickBlock = block;
            System.out.println("set block");
            commonHandler();
            return true;
        }
        else {
            System.out.println("not listen to block"+ "listen to " + listenFor);
            return false;
        }
    }

    public static Block getClickBlock() {
        Block temp = clickBlock;
        clickBlock = null;
        return temp;
    }

    public static boolean setClickChess(Chess chess) {

        if(listenFor == chessL){
            Text.PlayChess.chessInfo(chess);
            clickChess = chess;
            System.out.println("set chess");
            commonHandler();
            return true;
        }
        else if(listenFor == chess2L){
            clickChess2 = chess;
            System.out.println("set second chess");
            commonHandler();
            return true;

        }
        System.out.println("not listen to chess"+ "listen to " + listenFor);

        return false;

    }
    public static boolean setClickButton(int b){
        if(b == endRoundButton){

            Log.i("setButton","endRound");
            game.changeRound();
            changeState(initial);
        }

        else if(b == cancelButton){
            changeState(initial);
        }
        else if(listenFor == buttonL){
            clickButton = b;
            System.out.println("click button set");

            commonHandler();

        }
        else {
            System.out.println("not listen to button"+ "listen to " + listenFor);

            return false;
        }
        return true;
    }
    public static int getState() {
        return state;
    }

    public static void changeState(int choice) {
        state = choice;
        if(state == moveState){
            listenFor= blockL;

            System.out.println("change to moveState");
        }
        else if(state == initial){
            listenFor= chessL;
            clickChess = null;
            clickChess2 = null;
            clickBlock = null;
            System.out.println("change to initial");
            Text.PlayChess.messageBlock.setText(Text.PlayChess.clickChess);
        }
        else if(state == skillState){
            System.out.println("change to skillState");
        }
        else if(state == chessMenu){
            listenFor= buttonL;
            System.out.println("change to chessMenu State");
        }
    }

    static int request = 0;
    public static void commonHandler() {
        Log.i("GameController", "commonHandler");

        if(state == putChessState){
            int temp = PutChess.putChess(clickBlock);
            clickBlock = null;
            if(temp == 2){
                changeState(initial);
                GameView.changePage();
                System.out.println(game.whoseRound().ID);
            }
            else if(temp != 0) {
                Log.i("commonHandler","putChessState error 136");
            }
        }
        else if(state == initial) {
            Log.i("commonHandler", "state initial");
            if (clickChess != null) {
                changeState(chessMenu);
            }
            clickButton = noButton;

        }

        else if(state == chessMenu) {
            System.out.println("state chessMenu");
            if (clickButton == skillButton) {
                if(game.whoseRound().skillPoint <= 0){
                    changeState(initial);
                }
                changeState(skillState);
                commonHandler();
            } else if (clickButton == moveButton) {
                if(game.whoseRound().movePoint <= 0){
                    changeState(initial);
                }
                else {
                    changeState(moveState);
                }
            }
        }

/** *****************************************************/
        else if (state == skillState) {
            System.out.println("state skillState");
            int returnOfSkill = 0;
            if(clickChess == null){
                System.out.println("204 error  (no skill chess)");
            }
            else if(request == 0){//no last request
                Log.i("skillState","Log in request");
                returnOfSkill = clickChess.skill();
            }
            else if(request == 1){//last request is chess
                returnOfSkill = clickChess.skill(clickChess2);
            }
            else if(request == 2){//last request is block
                returnOfSkill = clickChess.skill(clickBlock);
            }
            else{
                System.out.println("222 error in skillHandler (invalid  value of last request)");
            }

            clickChess2 = null;
            clickBlock = null;

            if(returnOfSkill == 0){
                Log.i("skillHandler ","228 error in skillHandler (skill() error)");
            }
            else if(returnOfSkill == 1) {// require chess
                Log.i("skillHandler ", "waiting for chessClick");
                request = 1;
                listenFor = chess2L;
            }
            else if(returnOfSkill == 2){// require block
                Log.i("skillHandler ", "waiting for blockClick");
                request = 2;
                listenFor = blockL;
            }
            else if(returnOfSkill == Chess.reInitial){// finish and go to initial
                Log.i("skillHandler ", "skill properly");
                request = 0;

                changeState(initial);
            }

        }

        else if(state== moveState) {
            System.out.println("state moveState");

            if (clickChess != null && clickBlock != null) {
                moveHandler();
            } else {
                System.out.println("Error in moveState");

            }
            clickChess = null;
            clickBlock = null;
            changeState(initial);
        }

        movementFinish();

    }//handle every click on blocks, chess button and chess

    private static boolean moveHandler() {

       Log.i("moveHandler","moveHandler");

        boolean ret = clickChess.moveChess(clickBlock);
        game.checkAllDeath();
        if (ret) {
            game.whoseRound().movePointDec();
        }
        return ret;
    }



//    private static void checkIfChangeRound (){
//        if (game.whoseRound().getMovePoint() == 0 && game.whoseRound().skillPoint == 0) {
//            game.ChangeRound();
//        }
//    }

    /**
     * check textView of skillPoint and movePoint
     * check every chess death
     */
    static void movementFinish(){
        Log.i("movementFinish" , "inside");
        if(game.castle.chess != game.castle.chessLast){
            game.castle.setOccupiedRound(0);
            game.castle.chessLast = game.castle.chess;
        }

        Text.PlayChess.skillPoint_blue.setText(Integer.toString(game.player1.skillPoint));
        Text.PlayChess.movePoint_blue.setText(Integer.toString(game.player1.movePoint));
        Text.PlayChess.skillPoint_red.setText(Integer.toString(game.player2.skillPoint));
        Text.PlayChess.movePoint_red.setText(Integer.toString(game.player2.movePoint));
    }
}
