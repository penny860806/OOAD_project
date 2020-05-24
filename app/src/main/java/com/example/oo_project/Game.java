package com.example.oo_project;


public class Game {

     int step = 1;
     Player player1 = new Player(true, "玩家 blue");//讓玩家1先手
     Player player2 = new Player(false, "玩家 red");//玩家2後手

    GameMap GM;
    Game(GameMap map){
        GM = map;
    }
    public void ChangeRound(){//交換回合，待新增泉與城後做修正
        System.out.println("ChangeRound");
        player1.myRound = !player1.myRound;
        player2.myRound = !player2.myRound;
        whoseRound().movePoint=1;
        for (int i=1 ; i<GM.map.length ;i++){
            for(int j=1 ; j<GM.map.length ; j++){

                if(GM.map[i][j]!=null && GM.map[i][j].chess!=null) {
                    GM.map[i][j].chess.setClickAvail(GM.map[i][j].chess.team.myRound);
                }
            }
        }
        System.out.println("Change Round complete. whose round:"+whoseRound().ID);


    }
    public Player whoseRound (){//找出當回合玩家的方法
         if(this.player1.myRound==true)
             return this.player1;
         else
             return this.player2;

     }


 }