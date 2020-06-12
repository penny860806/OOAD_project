package com.example.oo_project;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class CustomTimer {
    CountDownTimer countDownTimer;
    long timeLeftMS = 30000;
    boolean timerRunning = false;
    TextView timer;
    public CustomTimer(View v){
        timer = (TextView) v;
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
                //reset
                stopTimer();
                timeLeftMS = 30000;
                //change round
                GameController.setClickButton(GameController.endRoundButton);
            }
        }.start();
        timerRunning = true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
    }
    public void updateTimer(){
        int seconds = (int) timeLeftMS / 1000;
        timer.setText(String.valueOf(seconds));
    }
}
