package com.example.oo_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        LinearLayout move_function = (LinearLayout) findViewById(R.id.moveFunction);
        //放棋階段不能按投降，不能儲存
        if(GameController.getState()==GameController.putChessState){
            move_function.setVisibility(View.INVISIBLE);
        }
        else{
            move_function.setVisibility(View.VISIBLE);
        }

        //投降 按鍵
        Button surrender = (Button) findViewById(R.id.surrender);
        surrender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stop music
                FullscreenActivity.BGMusic.stop();
                //blue wins
                if (Game.whoseRound() == Game.player1) {
                    Intent intent = new Intent(setting.this, red_win.class);
                    startActivity(intent);
                } else if (Game.whoseRound() == Game.player2) {
                    Intent intent = new Intent(setting.this, blue_win.class);
                    startActivity(intent);
                }
            }
        });

        //儲存遊戲 按鍵
        Button SaveGame = (Button) findViewById(R.id.saveGame);
        SaveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //呼叫儲存遊戲

            }
        });


        //返回遊戲 按鍵
        Button backgame = (Button) findViewById(R.id.backgame);
        backgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //開始計時
                NewGame.playChessTimer.toggleTimer();
                NewGame.putChessTimer.toggleTimer();
                //結束設定畫面，返回遊戲畫面
                finish();
            }
        });

        //音樂開關
        final ToggleButton bgmusic = (ToggleButton) findViewById(R.id.bgmusic);
        if(FullscreenActivity.isPlaying==true){
            bgmusic.setChecked(false);
            bgmusic.setBackgroundColor(Color.RED);
        }
        else{
            bgmusic.setChecked(true);
            bgmusic.setBackgroundColor(Color.GREEN);
        }

        bgmusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    FullscreenActivity.BGMusic.pause();
                    FullscreenActivity.isPlaying=false;
                    bgmusic.setBackgroundColor(Color.GREEN);
                } else {
                    // The toggle is disabled
                    FullscreenActivity.BGMusic.start();
                    FullscreenActivity.isPlaying=true;
                    bgmusic.setBackgroundColor(Color.RED);
                }
            }
        });
    }

}
