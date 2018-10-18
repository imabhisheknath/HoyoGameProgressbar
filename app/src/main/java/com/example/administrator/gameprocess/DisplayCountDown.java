package com.example.administrator.gameprocess;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;


public class DisplayCountDown {
    private static String LOG_TITLE = ProgressDialogTheme.class.getSimpleName();


    private Context mContext;
    private CountDownTimer countDownTimer;
    private int counttime = 5000;
    private String GO = "GO!";


    private onCountDownListner countListner;


    public interface onCountDownListner {

        void onCurrentStatus(String secounds);

        void onAllSet();
    }


    public DisplayCountDown(@NonNull Context context) {
        mContext = context;


    }


    public DisplayCountDown setResponseListener(onCountDownListner responseListener) {
        this.countListner = responseListener;
        return this;
    }


    public void start() {

        this.countDownTimer = new CountDownTimer(counttime, 1000L) {
            public void onTick(long l) {
                long sec = l / 1000;
                long _sec = sec - 1;

                if (_sec == 0) {
                    countListner.onCurrentStatus(GO);
                } else {
                    countListner.onCurrentStatus(_sec + "");
                }


            }

            public void onFinish() {
                countListner.onAllSet();


            }
        };
        this.countDownTimer.start();


    }


}

