package com.example.oo_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class blue_win extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_win);
        Button B_GoBack = (Button) findViewById(R.id.blue_finish);
        B_GoBack.setOnClickListener(new View.OnClickListener() {
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
