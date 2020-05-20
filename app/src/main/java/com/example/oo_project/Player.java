package com.example.oo_project;

public class player {
	boolean myRound=false;
	boolean surrender=false;
	String ID="請輸入角色名稱";
	short movePoint=1;
	short skillPoint=3;
	public void countMove(short move) {
		this.movePoint = move ;
	}
	public void countSkill(short spring) {
		this.movePoint = spring  ;
	}
	public void toSurrender() {
		surrender=true;
		
	}
}
