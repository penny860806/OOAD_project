package com.example.oo_project;

import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameController {
    /**
     * state:
     * 0: initial
     * 1: chess menu
     * 2: move state(have Chess, require targetBlock, manipulate move by call moveChess, )
     * 3: skill state
     */

    private final static int initial = 0, chessMenu = 1, moveState = 2, skillState = 3 , putChessState = 4;
    final static int noButton = 0,skillButton = 1, moveButton = 2, cancelButton = 3 ;
    private final static int nothingL=0, buttonL = 1,blockL = 2, chessL = 3 ,chess2L = 4;
    private static int state = putChessState;
    private static int clickButton = noButton;
    private static int listenFor = blockL;
    private static LinearLayout masterView;
    private static LinearLayout View1, View2;

    private static Game game = null;
    static Block clickBlock = null;
    static Chess clickChess = null , clickChess2 = null;


    GameController(Game game , LinearLayout masterView, LinearLayout View1, LinearLayout View2) {
        this.game = game;
        this.masterView = masterView;
        this.View1 = View1;
        this.View2 = View2;
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
        if(listenFor == buttonL){
            clickButton = b;
            System.out.println("click button set");

            commonHandler();
            return true;
        }
        else {
            System.out.println("not listen to button"+ "listen to " + listenFor);

            return false;
        }

    }
    public static int getState() {
        return state;
    }

    private static void changeState(int choice) {
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
        }
        else if(state == skillState){
            System.out.println("change to skillState");
        }
        else if(state == chessMenu){
            listenFor= buttonL;
            System.out.println("change to chessMenu State");
        }
    }

//    public static void putChessHandler()
//    {
//
//    }
    static int request = 0;
    public static void commonHandler() {
        Log.i("GameController", "commonHandler");

        if(state == putChessState){
            int temp = PutChess.putChess(clickBlock);
            clickBlock = null;
            if(temp == 2){
                changeState(initial);
                masterView.removeView(View2);
                masterView.addView(View1);

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
                changeState(skillState);
                commonHandler();
            } else if (clickButton == moveButton) {
                changeState(moveState);
            } else if (clickButton == cancelButton) {
                changeState(initial);
            }
        }

/*******************************************************/
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
            else if(returnOfSkill == 3){// finish and go to initial
                Log.i("skillHandler ", "skill properly");
                request = 0;
                checkIfChangeRound();
                changeState(initial);
            }

        }

        else if(state== moveState) {
            System.out.println("state moveState");

            if (clickChess != null && clickBlock != null) {
                moveHandler();
                clickChess = null;
                clickBlock = null;
                changeState(initial);
                /** 換回合 **/
                checkIfChangeRound();
            } else {
                System.out.println("Error in moveState");
                clickChess = null;
                clickBlock = null;
                changeState(initial);

            }
        }

    }//handle every click on blocks, chess button and chess

    private static boolean moveHandler() {

       Log.i("moveHandler","moveHandler");

        boolean ret = clickChess.moveChess(clickBlock);
        if (ret) {
            game.whoseRound().movePointDec();
        }
        return ret;
    }

    /** skillHandler() return value
     * 0: error
     * 1: waiting for second chessClick
     * 2: waiting for blockClick
     * 3: finish successfully and back to initial
     * 4: not available input
     */

    /**
     * skill() return value
     * 0: error
     * 1: request a chess click
     * 2: request a block click
     * 3: back to initial
     */

    private static int skillHandler(){
        int returnOfSkill = 0;
        System.out.println("in skillHandler");
        if(clickChess == null){
            System.out.println("204 error in skillHandler (no skill chess)");
            return 0;
        }
        else if(request == 0){
            Log.i("skillHandler","Log in request");
            returnOfSkill = clickChess.skill();

        }
        else if(request == 1){
            returnOfSkill = clickChess.skill(clickChess2);
        }
        else if(request == 2){
            returnOfSkill = clickChess.skill(clickBlock);
        }
        else{
            System.out.println("222 error in skillHandler (invalid  value of request)");
            return 0;
        }

        clickChess2 = null;
        clickBlock = null;

        if(returnOfSkill == 0){
            Log.i("skillHandler ","228 error in skillHandler (skill() error)");
            return  0;
        }

        else if(returnOfSkill == 1){
            Log.i("skillHandler ", "waiting for blockClick");
            request = 2;
            listenFor = chess2L;
            return 1;
        }

        else if(returnOfSkill == 2){
            Log.i("skillHandler ", "waiting for blockClick");
            request = 2;
            listenFor = blockL;

            return 2;
        }

        else if(returnOfSkill == 3){
            Log.i("skillHandler ", "skill properly");
            request = 0;

            return 3;
        }
        return 0;
    }

    private static void checkIfChangeRound (){
        if (game.whoseRound().getMovePoint() == 0 && game.whoseRound().skillPoint == 0) {
            game.ChangeRound();
        }
    }

    public static boolean onListening(Chess chess){
        if(listenFor == chessL){
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean onListening(Block block){
        if(listenFor == blockL){
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean onListening(Button button){
        if(listenFor == blockL){
            return true;
        }
        else {
            return false;
        }
    }
}
