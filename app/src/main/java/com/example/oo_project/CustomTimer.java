package com.example.oo_project;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CustomTimer {
    CountDownTimer countDownTimer;
    final long maxTime = 10000;
    long timeLeftMS = 10000;
    boolean timerRunning = false;
    TextView timerView;
    static Game game;
    PutChess putChess;
    public CustomTimer(View v){
        timerView = (TextView) v;
    }
    public void toggleTimer(){
        if(timerRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }
    public void startTimer(){
//        countDownTimer = new CountDownTimer(timeLeftMS,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                timeLeftMS = millisUntilFinished;
//                updateTimer();
//            }
//
//            @Override
//            public void onFinish() {
//                //reset
//                stopTimer();
//                timeLeftMS = 10000;
//                //change round
//
//
//                if(state == putChessState){
//                    Log.i("timer","putChessTimer end");
//
//                    stopTimer();
//                    if( !putChess.changeRound()){
//
//                        start();
//                    }
//                    else {
//                        Log.i("timer","cancel");
//                        cancel();
//                    }
//
//                }
//
//                else {
//                    Log.i("timer","playChessTimer end");
//                    stopTimer();
//                    game.changeRound();
//                    start();
//                }
//
//
//
//            }
//
//        }.start();
//        timerRunning = true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
    }
    public void updateTimer(){
        int seconds = (int) timeLeftMS / 1000;
        timerView.setText(String.valueOf(seconds));
    }


    public void resetTimer(){
        stopTimer();
        timeLeftMS = maxTime;
        startTimer();
    }
}

class PutChessTimer extends CustomTimer{

    public PutChessTimer(View v) {
        super(v);
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
                timeLeftMS = 10000;
                //change round
                Log.i("timer","putChessTimer end");
                stopTimer();
                if( !putChess.changeRound()){
                    start();
                }
                else {
                    Log.i("timer","cancel");
                    cancel();
                }
            }
        }.start();
        timerRunning = true;
    }
}
class PlayChessTimer extends CustomTimer{

    public PlayChessTimer(View v) {
        super(v);
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

                //change round
                Log.i("timer","putChessTimer end");

                game.changeRound();

            }
        }.start();
        timerRunning = true;
    }
}

