package com.example.oo_project;

public class Player {
    boolean myRound = false;
    boolean surrender = false;
    String ID = "請輸入角色名稱";
    short movePoint = baseMovePoint;
    short skillPoint = baseSkillPoint;
    static final short baseMovePoint = 1;
    static final short baseSkillPoint = 1;
    int chessNum = 0;

//    public void countMove(short move) {
//        this.movePoint = move;
//    }
//
//    public void countSkill(short spring) {
//        this.movePoint = spring;
//    }

    public void toSurrender() {
        surrender = true;

    }

    public Player(boolean myRound, String str) {
        this.ID = str;
        this.myRound = myRound;
    }

    public void movePointDec() {
        movePoint--;
    }

//    public int getMovePoint() {
//        return movePoint;
//    }


}
