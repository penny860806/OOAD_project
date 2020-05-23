package com.example.oo_project;

public class GameController {
    /**
     * state:
     *  0: initial
     *  1: chess menu
     *  2: move state
     *  3: skill state
     */
    public final static int initial = 0 , chessMenu = 1 , moveState = 2 , skillState = 3;
    private static int state = initial;
    static Block clickBlock = null;
    public static void setClickedBlock(Block block){
        clickBlock = block;
    }

    public static Block getClickBlock() {
        Block temp = clickBlock ;
        clickBlock = null;

        return temp;
    }

    public static int getState (){
        return state;
    }
    public static void changeState (int choice){
        state = choice;
    }
}
