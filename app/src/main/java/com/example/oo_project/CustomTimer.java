package com.example.oo_project;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CustomTimer {
    CountDownTimer countDownTimer;
    long maxTime = 30000;
    long timeLeftMS = 30000;
    boolean timerRunning = false;
    TextView timer;
    Game game;
    PutChess putChess;
    final public static int putChessState=0  , playChessState=1;
    int state=0;
    public CustomTimer(View v){
        timer = (TextView) v;
    }

//    public void setTimer(TextView timer) {
//        this.timer = timer;
//    }

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
                //reset
                stopTimer();
                timeLeftMS = 30000;
                //change round


                if(state == putChessState){
                    if( putChess.changeRound()){
                        stopTimer();
                    }
                    else if(game == null){
                        Log.i("timer","game is null");
                    }
                    else {
                        game.changeRound();
                        timeLeftMS = 30000;
                        start();

                    }
                }

//                else if(state == playChessState){
//                    game.changeRound();
//
//                }



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
