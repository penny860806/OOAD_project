package com.example.oo_project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.tv.TvContentRating;
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

import java.nio.ReadOnlyBufferException;
import java.util.concurrent.ConcurrentLinkedDeque;

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
    public LinearLayout NewGame_back;
    //全域Timer
    public static CustomTimer customTimer;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        NewGame_back = (LinearLayout) findViewById(R.id.Map);
        NewGame_back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!mVisible) {
                    show();
                    if (AUTO_HIDE) delayedHide(AUTO_HIDE_DELAY_MILLIS);
                }
                return false;
            }
        });


        //new map
        GameMap GM = new GameMap(6, this, NewGame_back);
        Game test = new Game(GM);//測試地圖
        GameView gameView = new GameView(test);
        PutChess putChess = new PutChess(test, this);
        GameController gameController = new GameController(test);
        Chess.setRequirement(test);


        //控制切換右側畫面
        LayoutInflater inflater = getLayoutInflater();

        //展開主視窗
        View activity_main = inflater.inflate(R.layout.activity_new_game, null);

        //取得主視窗中右方的空白 LinearLayout
        GameView.masterView = (LinearLayout) findViewById(R.id.Game_Function);

        //下棋
        View main1 = inflater.inflate(R.layout.activity_playchess, null);//展開第1個子畫面視窗

        //找出第1個視窗中的內容版面
        GameView.View1 = (LinearLayout) main1.findViewById(R.id.playChess);

        //放棋
        // 展開第2個子畫面視窗
        View main2 = inflater.inflate(R.layout.activity_putchess, null);
        GameView.View2 = (LinearLayout) main2.findViewById(R.id.putChess);//找出第2個視窗中的內容版面

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        GameView.masterView.addView(GameView.View2);
        //下面兩行可以控制切換右側畫面
        //masterView.removeView(View2);
        //masterView.addView(View1);


        //下棋 角色說明文字部分可以上下滾動
        Text.PlayChess.chessNameBlock = (TextView) main1.findViewById(R.id.chess_name);
        Text.PlayChess.messageBlock = (TextView) main1.findViewById(R.id.chess_info);
        Text.PlayChess.messageBlock.setMovementMethod(ScrollingMovementMethod.getInstance());
        Text.PlayChess.movePoint_blue = (TextView) main1.findViewById(R.id.movePoint_blue);
        Text.PlayChess.movePoint_red = (TextView) main1.findViewById(R.id.movePoint_red);
        Text.PlayChess.skillPoint_blue = (TextView) main1.findViewById(R.id.player_skillPoint_blue);
        Text.PlayChess.skillPoint_red = (TextView) main1.findViewById(R.id.skillPoint_red);
        Text.PlayChess.timer = (TextView) main1.findViewById(R.id.game_timer);

        //放棋
        Text.PutChess.chessNameBlock = (TextView) main2.findViewById(R.id.chess_name);
        Text.PutChess.messageBlock = (TextView) main2.findViewById(R.id.message_block);
        Text.PutChess.messageBlock.setMovementMethod(ScrollingMovementMethod.getInstance());
        Text.PutChess.timer = (TextView) main2.findViewById(R.id.put_timer);

        customTimer = new CustomTimer(Text.PlayChess.timer);

        //所有會用到右側功能面板的，都要寫在這邊，而要呼叫findviewById都要先call他的XML名稱
        //putchess是main2, playchess是main1

        /* set button */
        {
            Button skillButton = (Button) main1.findViewById(R.id.use_Skill);
            skillButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("skillButton clicked");

                    GameController.setClickButton(GameController.skillButton);
                }
            });

            Button moveButton = (Button) main1.findViewById(R.id.move_chess);
            moveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("moveButton clicked");

                    GameController.setClickButton(GameController.moveButton);
                }
            });
            Button cancelButton = (Button) main1.findViewById(R.id.Cancel_click);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("cancelButton clicked");

                    GameController.setClickButton(GameController.cancelButton);

                }
            });
            Button endRoundButton = (Button) main1.findViewById(R.id.end_round_button);
            endRoundButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("endRoundButton clicked");

                    GameController.setClickButton(GameController.endRoundButton);

                }
            });


        }
        /*putChess*/
        {
            /* 綁每個text block */

            final TextView Jet_num = (TextView) main2.findViewById(R.id.Jet_num);
            Button Jet_button = (Button) main2.findViewById(R.id.Jet_button);
            PutChess.chessText[Game.JetId] = Jet_num;

            Jet_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("putChess", "Jet_button clicked");
                    PutChess.selectedChessId = Game.JetId;
                    Text.PutChess.chessNameBlock.setText("氣場");
                    Text.PutChess.messageBlock.setText(Text.PutChess.Jet);
                }
            });

            Button TransferTower_button = (Button) main2.findViewById(R.id.TransferTower_button);
            final TextView TransferTower_num = (TextView) main2.findViewById(R.id.TransferTower_num);
            PutChess.chessText[Game.TransferTowerId] = TransferTower_num;
            TransferTower_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("putChess", "TransferTower_button clicked");
                    PutChess.selectedChessId = Game.TransferTowerId;
                    Text.PutChess.chessNameBlock.setText("傳送塔");
                    Text.PutChess.messageBlock.setText(Text.PutChess.TransferTower);
                }
            });

            Button Rhino_button = (Button) main2.findViewById(R.id.Rhino_button);
            final TextView Rhino_num = (TextView) main2.findViewById(R.id.Rhino_num);
            PutChess.chessText[Game.RhinoId] = Rhino_num;
            Rhino_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("putChess", "Rhino_button clicked");
                    PutChess.selectedChessId = Game.RhinoId;
                    Text.PutChess.chessNameBlock.setText("犀牛");
                    Text.PutChess.messageBlock.setText(Text.PutChess.Rhino);

                }
            });

            Button Rock_button = (Button) main2.findViewById(R.id.Rock_button);
            final TextView Rock_num = (TextView) main2.findViewById(R.id.Rock_num);
            PutChess.chessText[Game.RockId] = Rock_num;

            Rock_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("putChess", "Rock_button clicked");
                    PutChess.selectedChessId = Game.RockId;
                    Text.PutChess.chessNameBlock.setText("巨石");
                    Text.PutChess.messageBlock.setText(Text.PutChess.Rock);

                }
            });

            Button Clip_button = (Button) main2.findViewById(R.id.Clip_button);
            final TextView Clip_num = (TextView) main2.findViewById(R.id.Clip_num);
            PutChess.chessText[Game.ClipId] = Clip_num;
            Clip_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("putChess", "Clip_button clicked");
                    PutChess.selectedChessId = Game.ClipId;
                    Text.PutChess.chessNameBlock.setText("夾子");
                    Text.PutChess.messageBlock.setText(Text.PutChess.Clip);

                }
            });

            Button Hercules_button = (Button) main2.findViewById(R.id.Hercules_button);
            final TextView Hercules_num = (TextView) main2.findViewById(R.id.Hercules_num);
            PutChess.chessText[Game.HerculesId] = Hercules_num;
            Hercules_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("putChess", "Herculus_button clicked");
                    PutChess.selectedChessId = Game.HerculesId;
                    Text.PutChess.chessNameBlock.setText("力士");
                    Text.PutChess.messageBlock.setText(Text.PutChess.Hercules);


                }
            });

            Button Horse_button = (Button) main2.findViewById(R.id.Horse_button);
            final TextView Horse_num = (TextView) main2.findViewById(R.id.Horse_num);
            PutChess.chessText[Game.HorseId] = Horse_num;
            Horse_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("putChess", "Horse_button clicked");
                    PutChess.selectedChessId = Game.HorseId;
                    Text.PutChess.chessNameBlock.setText("馬");
                    Text.PutChess.messageBlock.setText(Text.PutChess.Horse);

                }
            });

            Button Spy_button = (Button) main2.findViewById(R.id.Spy_button);
            final TextView Spy_num = (TextView) main2.findViewById(R.id.Spy_num);
            PutChess.chessText[Game.SpyId] = Spy_num;

            Spy_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("putChess", "Spy_button clicked");
                    PutChess.selectedChessId = Game.SpyId;
                    Text.PutChess.chessNameBlock.setText("間諜");
                    Text.PutChess.messageBlock.setText(Text.PutChess.Spy);

                }
            });

        }
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
