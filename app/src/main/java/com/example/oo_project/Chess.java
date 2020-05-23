package com.example.oo_project;

import android.content.Context;

public class Chess extends androidx.appcompat.widget.AppCompatImageView {
	public int  moveRange;
	public String chessName="";
	public Player team;
	protected int deathNum;//死亡條件值
	protected String myTeam;//死亡條件隊伍
	Block position;
	public boolean canBePush;
	Chess(Context context,String name, int moveRange, Player team){
		super(context);
		this.chessName = name;
		this.moveRange = moveRange;
		this.team = team;
	}
	public boolean Death(int x , int y, String team) {
		return true;
	}
}