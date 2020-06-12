package com.example.oo_project;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class CustomTimer {
    CountDownTimer countDownTimer;
    long maxTime = 10000;
    long timeLeftMS = 10000;
    boolean timerRunning = false;
    TextView timer;
    Game game;
    PutChess putChess;
    final public static int putChessState=0  , playChessState=1;
    int state=0;
    public CustomTimer(View v , Game game , PutChess putChess, int state){
        timer = (TextView) v;
        this.game = game;
        this.state = state;
        this.putChess = putChess;
    }

    public void setTimer(TextView timer) {
        this.timer = timer;
    }

    public void toggleTimer(){
        if(timerRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }
    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftMS,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMS = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                stopTimer();
                if(state == putChessState){
                    if( putChess.changeRound()){
                        stopTimer();
                    }
                    else {
                        game.changeRound();
                        timeLeftMS = maxTime;
                        start();

                    }
                }

                else if(state == playChessState){
                    game.changeRound();

                }

        }
        }.start();
        timerRunning = true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
    }
    private void updateTimer(){
        int seconds = (int) timeLeftMS / 1000;
        timer.setText(String.valueOf(seconds));
    }
}
