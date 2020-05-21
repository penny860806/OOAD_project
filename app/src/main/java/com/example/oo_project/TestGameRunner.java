package com.example.oo_project;

public class TestGameRunner {
       public static void main(String[] args ){
        Player player1=new Player();
        player1.ID="玩家1";
        player1.myRound=true;//讓玩家1先手
        Player player2=new Player();
        player1.ID="玩家2";
        player2.myRound=false;//玩家2後手

       }
	
}
