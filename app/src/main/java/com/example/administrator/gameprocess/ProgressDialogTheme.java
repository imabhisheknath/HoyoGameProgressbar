package com.example.administrator.gameprocess;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.Log;


public class ProgressDialogTheme {
    private static String LOG_TITLE = ProgressDialogTheme.class.getSimpleName();


    private Context mContext;
    private Progress progressDialog;
    private CountDownTimer countDownTimer;
    private long counttime = 3L;
    private long startCount = 4L;
    private boolean is_execute = false;
    private boolean timed_out = false;
    private boolean call_auto = false;
    receiveSecounds receiveSecounds;
    private boolean is_locationActive = false;
    private boolean counttext = false;


    private onProgressDialogTimeoutListner progressListner;

    public ProgressDialogTheme(receiveSecounds receiveSecounds) {

        this.receiveSecounds = receiveSecounds;
    }


    public void receivetime(receiveSecounds receiveSecounds) {
        this.receiveSecounds = receiveSecounds;
    }


    public void setTimeOutTime(int time) {
        this.counttime = time;
    }


    public void callCountDownAutoMatically() {
        this.call_auto = true;
    }


    public void startCountDown() {
        if (is_locationActive) {
            enableCount();
        } else {
            is_execute = true;
            progressDialog.Dissmiss();
            progressListner.onInvalidCountdownStartdueToLocation();
        }


    }


    public void setCountDownTime(long time) {
        long realtime = time + 1L;
        this.startCount = realtime;
    }


    public interface onProgressDialogTimeoutListner {
        void onTimeOut();

        void onCancelbyLocationFailed();

        void onAllSet();

        void secoundRemainngforCountDown(long sec);

        void onInvalidCountdownStartdueToLocation();
    }


    public ProgressDialogTheme(@NonNull Context context) {
        mContext = context;
        progressDialog = new Progress(mContext);
        mContext.registerReceiver(mNotificationReceiver, new IntentFilter("ActionA"));

    }


    public ProgressDialogTheme setResponseListener(onProgressDialogTimeoutListner responseListener) {
        this.progressListner = responseListener;
        return this;
    }


    public void LocationSucess() {
        if (!timed_out) {
            progressDialog.Dissmiss();
            is_locationActive = true;
            is_execute = true;
            if (call_auto) {
                enableCount();
            } else {
                progressListner.onAllSet();
            }


        }


    }

    private void enableCount() {


        Intent intent = new Intent(mContext, CountDownActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("time", String.valueOf(startCount));
        mContext.startActivity(intent);

    }


    public void LocationFailed() {
        if (!timed_out) {
            progressDialog.Dissmiss();
            is_execute = true;
            progressListner.onCancelbyLocationFailed();
        }


    }


    public void start() {
        progressDialog.Show();
        this.countDownTimer = new CountDownTimer(counttime, 1000L) {
            public void onTick(long l) {


                if(!is_execute){
                    progressListner.secoundRemainngforCountDown(l / 1000);

                    Intent intent = new Intent();

                    if (counttext) {
                        intent.putExtra("status", "yes");
                    } else {
                        intent.putExtra("status", "no");
                    }
                    intent.putExtra("tym", l / 1000 + "");
                    intent.setAction("ActionB");
                    mContext.sendBroadcast(intent);
                }




            }

            public void onFinish() {
                if (!is_execute) {
                    timed_out = true;
                    progressDialog.Dissmiss();
                    progressListner.onTimeOut();
                }


            }
        };
        this.countDownTimer.start();


    }


    private BroadcastReceiver mNotificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            progressListner.onAllSet();
        }
    };


    public void enableCountText() {
        counttext = true;

    }


}

