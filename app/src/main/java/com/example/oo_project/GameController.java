package com.example.oo_project;

import android.util.Log;

public class GameController {
    /**
     * state:
     * 0: initial
     * 1: chess menu
     * 2: move state(have Chess, require targetBlock, manipulate move by call moveChess, )
     * 3: skill state
     */

    public final static int initial = 0, chessMenu = 1, moveState = 2, skillState = 3;
    private static int state = initial;
    static Game game = null;
    static Block clickBlock = null;
    static Chess clickChess = null;

    GameController(Game game) {
        this.game = game;
    }

    public static void setClickedBlock(Block block) {
        clickBlock = block;
    }

    public static Block getClickBlock() {
        Block temp = clickBlock;
        clickBlock = null;

        return temp;
    }

    public static void setClickChess(Chess chess) {
        clickChess = chess;
    }

    public static int getState() {
        return state;
    }

    public static void changeState(int choice) {
        state = choice;
    }

    public static void commonHandler() {
        Log.i("my", "commonHandler");
        if (clickChess != null && clickBlock != null && state == moveState) {
            moveHandler();
            clickChess = null;
            clickBlock = null;
            state = initial;
            /** 換回合 **/
            if (game.whoseRound().getMovePoint() == 0) {
                game.ChangeRound();
            }
        }
    }

    private static boolean moveHandler() {
        System.out.println("moveHandler");

        boolean ret = clickChess.moveChess(clickBlock);
        if (ret) {
            game.whoseRound().movePointDec();
        }
        return ret;
    }

}
