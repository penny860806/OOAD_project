package com.example.oo_project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewGame extends AppCompatActivity {

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            NewGame_back.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    LinearLayout NewGame_back;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        NewGame_back = (LinearLayout)findViewById(R.id.Map);
        NewGame_back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!mVisible) {
                    show();
                    if(AUTO_HIDE) delayedHide(AUTO_HIDE_DELAY_MILLIS);
                }
                return false;
            }
        });


        //new map
        GameMap GM = new GameMap(6,this,NewGame_back);
        Game test = new Game(GM);//測試地圖
        final GameController gameController= new GameController(test);






        Chess redChess = new Rhino(this,"r",test.player1,GM.map[4][1]);
        GameView.chessView_Red(redChess);
        Chess blueChess = new Chess((Context) this,"b",test.player2,GM.map[5][2]);
        GameView.chessView_Blue(blueChess);
//        Chess blueChess2 = new Chess((Context) this,"b2",test.player1,GM.map[6][3]);
//        GameView.chessView_Blue(blueChess2);
        Chess blueChess3= new Rock((Context) this,"b3",test.player1,GM.map[7][4]);
        GameView.chessView_Blue(blueChess3);



        //控制切換右側畫面
        LayoutInflater inflater =  getLayoutInflater();
        View activity_main = inflater.inflate(R.layout.activity_new_game, null);//展開主視窗
        LinearLayout masterView = (LinearLayout) findViewById(R.id.Game_Function); //取得主視窗中右方的空白LinearLayout
        //下棋
        View main1 = inflater.inflate(R.layout.activity_playchess, null);//展開第1個子畫面視窗
        LinearLayout View1 = (LinearLayout) main1.findViewById(R.id.playChess);//找出第1個視窗中的內容版面
        //放棋
        View main2 = inflater.inflate(R.layout.activity_putchess, null);//展開第2個子畫面視窗
        LinearLayout View2 = (LinearLayout) main2.findViewById(R.id.putChess);//找出第2個視窗中的內容版面

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        masterView.addView(View2);
        //下面兩行可以控制切換右側畫面
        //masterView.removeView(Vlew2);
        //masterView.addView(View1);

        //下棋 角色說明文字部分可以上下滾動
        TextView chess_info = (TextView) main1.findViewById(R.id.chess_info);
        chess_info.setMovementMethod(ScrollingMovementMethod.getInstance());
        //放棋
        TextView chess_des = (TextView) main2.findViewById(R.id.chess_description);
        chess_des.setMovementMethod(ScrollingMovementMethod.getInstance());


        //所有會用到右側功能面板的，都要寫在這邊，而要呼叫findviewById都要先call他的XML名稱
        //putchess是main2, playchess是main1
        Button skillButton = (Button) main1.findViewById(R.id.use_Skill) ;
        skillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("skillButton clicked");

                GameController.setClickButton(GameController.skillButton);
            }
        });

        Button moveButton = (Button) main1.findViewById(R.id.move_chess) ;
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("moveButton clicked");

                GameController.setClickButton(GameController.moveButton);
            }
        });;
        Button cancelButton = (Button) main1.findViewById(R.id.Cancel_click) ;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("cancelButton clicked");

                GameController.setClickButton(GameController.cancelButton);

            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        NewGame_back.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
    }
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
