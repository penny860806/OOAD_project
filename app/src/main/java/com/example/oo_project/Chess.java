package com.example.oo_project;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class Chess extends androidx.appcompat.widget.AppCompatImageView {
	public int  moveRange;
	public String chessName="";
	public Player team;
	protected int deathNum;//死亡條件值
	public boolean deathTeam;//死亡條件隊伍 true:同隊導致死亡 false:敵對導致死亡
	public boolean ImDead=false;
	private boolean clickAvail = true;
	Block positionBlock;
	public boolean canBePush;
	static Game game;
	final static int reError = 0 , reChessClick = 1 , reBlockClick = 2 , reInitial = 3/*with skill undone*/ , reSuccess = 4/*also back to initial*/;


	public static void setRequirement(Game game){
		Chess.game = game;
	}
	public Chess(Context context,String name, int moveRange, Player team, Block positionBlock  ,boolean canBePush){
		super(context);
		this.chessName = name;
		this.moveRange = moveRange;
		this.team = team;
		this.positionBlock = positionBlock;
		positionBlock.chess = this;
		positionBlock.player = this.team;
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("Chess clicked");
//				if(clickAvail)
					GameController.setClickChess((Chess) v);
			}
		});
		this.canBePush = canBePush;

	}
	public Chess(Context context,String name, int moveRange, Player team, Block positionBlock  ){
		this( context, name,  moveRange,  team,  positionBlock  , true);
	}
	public Chess(Context context,String name, Player team, Block positionBlock) {
		this( context, name, 1,  team,  positionBlock );
	}




	public void Death(Block theBlock) {
		theBlock.chess=null;
		theBlock.player=null;
	}

	public boolean moveChess(Block targetBlock){
		System.out.println("moveChess" );
		/**
		 * 缺: 地圖亮起來
		 */


		/**
		 *  傳signal 給 GameController
		 */
		boolean avail = checkCanMove(targetBlock);
		if(avail == false){

			return false;
		}

		positionBlock.moveChess(targetBlock);
		//更改圖片顯示位置
		GameView.moveChess_View(positionBlock,targetBlock,this);
		//更改Chess當前Block
		positionBlock = targetBlock;
		return true;
	}
	public boolean moveChess(int dir){
		return moveChess(this.positionBlock.getNeighbor(dir));
	}

	boolean checkCanMove (Block targetBlock){ //some subclass have to change this method
		System.out.println("checkCanMove");
		boolean avail = false;
		if(targetBlock == null){
			System.out.println("null target block in checkCanMove");
			return false;
		}
		int i;
		for( i=0 ; i<6 ; i++){
			if(positionBlock == targetBlock.getNeighbor(i) && targetBlock.isEmpty()){

				avail= true;
			}
		}
		System.out.println("Can move?"+avail);
		return avail;
	}

	public void setClickAvail(boolean input){
        clickAvail = input;
	}
	public int skill(Block targetBlock){
		return 0;
	}
	public int skill(Chess targetChess){
		return 0;
	}
	public int skill(){
		return 0;
	}
//	public void swap(Chess targetChess){
//
//	}
}