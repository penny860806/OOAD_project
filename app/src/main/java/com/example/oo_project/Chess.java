package com.example.oo_project;

public class Chess {
	public int  moveRange=1;
	public String chessName="chess";
	protected int deathNum=4;//死亡條件值
	protected String myTeam;//死亡條件隊伍
	Block position;
	public boolean canBePush;
	public boolean Death(int x , int y, String team) {
		return true;
	}
}