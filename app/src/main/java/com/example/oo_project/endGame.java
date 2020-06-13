package com.example.oo_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class endGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        LinearLayout BG = (LinearLayout) findViewById(R.id.wins);

        Bundle extras = getIntent().getExtras();
        int who=extras.getInt("who");
        if(who==2){
            BG.setBackgroundResource(R.drawable.winner_red_bg);
        }
        else if(who==1){
            BG.setBackgroundResource(R.drawable.winner_blue_bg);
        }
        else{
            BG.setBackgroundResource(R.drawable.winner_tie_bg);
        }


        Button GoBack = (Button) findViewById(R.id.finish);
        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //restart app
                Intent restartIntent = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(restartIntent);
            }
        });
    }
}
