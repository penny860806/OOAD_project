package com.example.oo_project;


import android.content.Context;

public class horse extends Chess {
	public boolean canBePush=true;
	protected int deathNum=3;
	String chessName="馬";
	horse(Context context,String name,int moveRange,Player team,Block positionBlock){
		super(context,name,moveRange,team,positionBlock);
	}
	public boolean Death(int x , int y, String team) {
		
	if (myTeam == team) {
		if (deathNum<=x) {
		return true	;
		}
		else return false;
		}
		else {
		if(deathNum<=y)
			 return true;
		else return false;
		}
	}
	public void setPosition (Block block,String setTeam){
		positionBlock=block;
		myTeam=setTeam;
	}
	
	
}