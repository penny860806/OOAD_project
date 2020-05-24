package com.example.oo_project;


public class Game {

    int step = 1;
     Player player1 = new Player(true, "玩家一");//讓玩家1先手
     Player player2 = new Player(false, "玩家二");//玩家2後手

     public void ChangeRound(Player a){//交換回合，待新增泉與城後做修正
             player1.myRound = !player1.myRound;
             player2.myRound = !player2.myRound;
             a.movePoint=1;

     }
     public Player showPlayer(Game game){//找出當回合玩家的方法
         if(game.player1.myRound==true)
             return game.player1;
         else
             return game.player2;

     }
 }