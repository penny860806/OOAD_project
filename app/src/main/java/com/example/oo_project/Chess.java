package com.example.oo_project;

import android.content.Context;
import android.view.View;

public class Chess extends androidx.appcompat.widget.AppCompatImageView {
	public int  moveRange;
	public String chessName="";
	public Player team;
	protected int deathNum;//死亡條件值
	protected String myTeam;//死亡條件隊伍
	Block positionBlock;
	public boolean canBePush;
	Chess(Context context,String name, int moveRange, Player team){
		super(context);
		this.chessName = name;
		this.moveRange = moveRange;
		this.team = team;
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					if(GameController.getState() == GameController.initial){
						GameController.changeState(GameController.moveState);
						moveChess();
						GameController.changeState(GameController.initial);
					}
				}
		});

	}
	public boolean Death(int x , int y, String team) {
		return true;
	}

	private void moveChess(){
		/**
		 * 缺: 地圖亮起來
		 */
		Block targetBlock = null;
		do{
			targetBlock = GameController.getClickBlock();
		}while (targetBlock == null);
		positionBlock.moveChess(targetBlock);
	}
}