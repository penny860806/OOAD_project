package com.example.oo_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class NewGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //等於main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        GameMap gameMap  = new GameMap(5, this, )


    }
}
