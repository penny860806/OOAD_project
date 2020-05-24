package com.example.oo_project;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public class Chess extends androidx.appcompat.widget.AppCompatImageView {
	public int  moveRange;
	public String chessName="";
	public Player team;
	protected int deathNum;//死亡條件值
	protected String myTeam;//死亡條件隊伍
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
					if(GameController.getState() == GameController.initial) {
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
		Block targetBlock = positionBlock.getNeighbor(Block.NW);

		/*
		do{
			targetBlock = GameController.getClickBlock();
			// 判斷能否移動到此

		}while (targetBlock == null);

		 */
		positionBlock.moveChess(targetBlock);
		//更改圖片顯示位置
		GameView.moveChess_View(positionBlock,targetBlock,this);
		//更改Chess當前Block
		positionBlock = targetBlock;
	}

	boolean checkCanMove (Block block){
		boolean avail = false;
		for(int i=0 ; i<6 ; i++){
			if(block == block.getNeighbor(i) && block.getNeighbor(i).isEmpty()){
				avail= true;
			}
		}
		
		return avail;
	}
}