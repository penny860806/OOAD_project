package com.example.oo_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //投降 按鍵
        Button surrender = (Button) findViewById(R.id.surrender);
        surrender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting.this, red_win.class);
                startActivity(intent);
            }
        });

        //返回遊戲 按鍵
        Button backgame = (Button) findViewById(R.id.backgame);
        backgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //開始計時
                NewGame.customTimer.toggleTimer();
                //結束設定畫面，返回遊戲畫面
                finish();
            }
        });

        //音樂開關
        final ToggleButton bgmusic = (ToggleButton) findViewById(R.id.bgmusic);
        bgmusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    FullscreenActivity.BGMusic.pause();
                    bgmusic.setBackgroundColor(Color.GREEN);
                } else {
                    // The toggle is disabled
                    FullscreenActivity.BGMusic.start();
                    bgmusic.setBackgroundColor(Color.RED);
                }
            }
        });
    }

}
