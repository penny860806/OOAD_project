package com.example.oo_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class putchess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putchess);

        //角色說明文字部分可以上下滾動
        TextView chess_des = (TextView) findViewById(R.id.chess_description);
        chess_des.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
