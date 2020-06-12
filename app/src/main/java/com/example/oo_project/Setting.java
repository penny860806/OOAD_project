package com.example.oo_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;
import com.example.oo_project.Game;

import static com.example.oo_project.Game.rec;

public class Setting extends AppCompatActivity {
    private Player player1,player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MediaPlayer mysong;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.onoff);
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

        mysong=MediaPlayer.create(Setting.this,R.raw.background);
        mysong.start();

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()){
                    toggleButton.setBackgroundColor(Color.GREEN);
                    mysong.pause();
                    mysong.seekTo(0);

                }
                else{
                    toggleButton.setBackgroundColor(Color.RED);
                    mysong.setLooping(true);
                    mysong.start();
                }
            }
        });
        Button set=(Button) findViewById(R.id.backgame);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("state",2);
//                Intent intent = new Intent();
//                intent.putExtras(bundle);
//                intent.setClass(Setting.this, NewGame.class);
//                startActivity(intent);
                finish();
            }
        });
        Button surrender=(Button) findViewById(R.id.surrender);
        surrender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(GameController.game.whoseRound().ID);
                if (GameController.game.whoseRound().ID=="blue"){
                    Intent intent = new Intent(Setting.this, red_win.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Setting.this, blue_win.class);
                    startActivity(intent);
                }


            }
        });
    }
}
