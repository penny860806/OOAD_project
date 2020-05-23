package com.example.oo_project;


public class horse extends Chess {
	public boolean canBePush=true;
	protected int deathNum=3;
	String chessName="馬";
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
		position=block;
		myTeam=setTeam;
	}
	
	
}