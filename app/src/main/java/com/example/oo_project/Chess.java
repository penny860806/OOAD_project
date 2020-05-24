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
	protected String myTeam;//死亡條件隊伍
	private boolean clickAvail = true;
	Block positionBlock;
	public boolean canBePush;
	Chess(Context context,String name, int moveRange, Player team, Block positionBlock){
		super(context);
		this.chessName = name;
		this.moveRange = moveRange;
		this.team = team;
		this.positionBlock = positionBlock;
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					Log.i("my","Chess is clicked");
					Log.i("my",String.valueOf( GameController.getState()));
					if(GameController.getState() == GameController.initial && clickAvail) {
						Log.i("my","inside if");
                        GameController.changeState(GameController.moveState);
						GameController.setClickChess((Chess) v);
						GameController.commonHandler();
					}
				}
		});

	}
	public boolean Death(int x , int y, String team) {
		return true;
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

	boolean checkCanMove (Block targetBlock){ //some subclass have to change this method
		System.out.println("checkCanMove");
		boolean avail = false;
		for(int i=0 ; i<6 ; i++){
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
}