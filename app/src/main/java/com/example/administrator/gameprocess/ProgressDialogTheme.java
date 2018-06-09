package com.example.administrator.gameprocess;

import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;


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
    private boolean is_forces = true;


    private onProgressDialogTimeoutListner progressListner;


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
            is_forces = true;
            is_execute = true;
            progressDialog.Dissmiss();
            progressListner.onForcingCountdownstart();
        }


    }


    public void setCountDownTime(long time) {
        long realtime = time + 1L;
        this.startCount = realtime;
    }


    public interface onProgressDialogTimeoutListner {
        void onTimeOut();

        void onCancel();

        void onSucess();

        void secoundsForTimeout(long sec);

        void onForcingCountdownstart();
    }


    public ProgressDialogTheme(@NonNull Context context) {
        mContext = context;
        progressDialog = new Progress(mContext);

        this.receiveSecounds = progressDialog.receiveSecounds();

    }


    public ProgressDialogTheme setResponseListener(onProgressDialogTimeoutListner responseListener) {
        this.progressListner = responseListener;
        return this;
    }


    public void preparaationDone() {
        if (!timed_out) {
            progressDialog.Dissmiss();
            is_locationActive = true;
            is_execute = true;
            if (call_auto) {
                enableCount();
            } else {
                if(!is_forces)
                progressListner.onSucess();
            }


        }


    }

    private void enableCount() {


        final Dialog dialog = new Dialog(mContext, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.progress_dialog);

        TextView tvTitle = dialog.findViewById(R.id.tvTitle);


        CountDownAnimation countDownAnimation = new CountDownAnimation(tvTitle, startCount);
        countDownAnimation.start();
        // Use scale animation
        Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        countDownAnimation.setAnimation(scaleAnimation);

        countDownAnimation.setCountDownListener(new CountDownAnimation.CountDownListener() {
            @Override
            public void onCountDownEnd(CountDownAnimation animation) {


                dialog.dismiss();
                progressListner.onSucess();

            }


        });

        dialog.show();
    }


    public void preparationFailed() {
        if (!timed_out) {
            progressDialog.Dissmiss();
            is_execute = true;
            progressListner.onCancel();
        }


    }


    public void startProgressDialog() {
        progressDialog.Show();

        this.countDownTimer = new CountDownTimer(counttime, 1000L) {
            public void onTick(long l) {

                if (counttext && !is_execute) {
                    receiveSecounds.receiveData(l / 1000);
                    progressListner.secoundsForTimeout(l / 1000);
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


    public void enableCountText() {
        counttext = true;

    }


}

